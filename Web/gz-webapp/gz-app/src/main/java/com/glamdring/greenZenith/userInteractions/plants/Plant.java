package com.glamdring.greenZenith.userInteractions.plants;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.application.plant.InvalidPlantException;
import com.glamdring.greenZenith.exceptions.application.plant.constants.PlantExceptions;
import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.GZDBConnector;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.handlers.formats.GZFormatter;
import com.glamdring.greenZenith.userInteractions.Attributable;
import com.glamdring.greenZenith.userInteractions.Interactable;
import com.glamdring.greenZenith.userInteractions.Killable;
import com.glamdring.greenZenith.userInteractions.plants.constants.PlantConfirmations;
import com.glamdring.greenZenith.userInteractions.users.User;

/**
 * Represents a plant or plant set owned by a User, with it's respective
 * attributes, allowing the creation, modification and deletion of it.
 *
 * @author Glamdring (Σxz)
 * @version 1.1.0
 * @since 0.1
 */
public class Plant implements Killable, Interactable, Attributable, Serializable {

    /**
     * The unique identifier for this plant.
     */
    private final int id;
    /**
     * A unique name for easier indetification of a plant.
     */
    private String name;
    /**
     * A concise description of a plant.
     */
    private String description;
    /**
     * The amount of equal plants of this singular type that a user posseses.
     */
    private int quantity;
    /**
     * The date a plant was planted on.
     */
    private Date plantingDate;
    /**
     * A user provided image of the Plant.
     */
    private BufferedImage plantPicture;
    /**
     * The schedule of a plant's water time.
     */
    private ArrayList<Time> schedule = new ArrayList<>();
    /**
     * The owner of this plant.
     */
    private final User owner;

    /**
     * Retrieves information form the database utilizing only the ID and a
     * connection to the database, and assigns the results to each correspondant
     * field.
     *
     * @param id The unique identifier that corresponds to a certain plant
     * registry on the database.
     * @param gzdbc A connection to the database.
     * @throws InvalidPlantException If the owner does not provide a connection
     * or the ID cannot be resolved.
     */
    public Plant(int id, GZDBConnector gzdbc) throws InvalidPlantException {
        if (id < 1) {
            throw new InvalidPlantException(PlantExceptions.INEXISTANT);
        }
        LinkedHashMap<String, Object> plantMap = new LinkedHashMap<>();
        plantMap.put("ID", id);
        try {
            ArrayList<LinkedHashMap<String, Object>> resultList = gzdbc.select(GZDBTables.PLANT, plantMap);
            if (resultList.size() != 1) {
                throw new InvalidPlantException(PlantExceptions.INEXISTANT);
            }
            LinkedHashMap<String, Object> resultMap = resultList.get(0);
            this.id = id;
            this.name = (String) resultMap.get("Name");
            this.description = (String) resultMap.get("Description");
            this.quantity = (int) resultMap.get("Quantity");
            this.plantingDate = (Date) resultMap.get("PlantingDate");
            this.owner = new User((int) resultMap.get("PUser_ID"));
            this.plantPicture = owner.getPictureHandler().convertBlobToBufferedImage((Blob) resultMap.get("Picture"));
            plantMap.clear();
            plantMap.put("Plant_ID", id);
            resultList = gzdbc.select(GZDBTables.PLANTSCHEDULE, plantMap);
            for (LinkedHashMap<String, Object> timeEntry : resultList) {
                schedule.add((Time) timeEntry.get("WaterTime"));
            }
        } catch (InvalidUserException | GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }
    }

    /**
     * Retrieves information form the database utilizing only the ID and owner,
     * and assigns the results to each correspondant field.
     *
     * @param id The unique identifier that corresponds to a certain plant
     * registry on the database.
     * @param owner The user that owns this specific plant.
     * @throws InvalidPlantException If the user does not provide a valid
     * connection or the ID cannot be resolved.
     */
    public Plant(int id, User owner) throws InvalidPlantException {
        if (id < 1) {
            throw new InvalidPlantException(PlantExceptions.INEXISTANT);
        }
        this.id = id;
        this.owner = owner;
        LinkedHashMap<String, Object> plantMap = new LinkedHashMap<>();
        plantMap.put("ID", id);
        try {
            ArrayList<LinkedHashMap<String, Object>> resultList = owner.gzdbc.select(GZDBTables.PLANT, plantMap);
            if (resultList.size() != 1) {
                throw new InvalidPlantException(PlantExceptions.INEXISTANT);
            }
            LinkedHashMap<String, Object> resultMap = resultList.get(0);
            this.name = (String) resultMap.get("Name");
            this.plantingDate = (Date) resultMap.get("PlantingDate");
            this.description = (String) resultMap.get("Description");
            this.quantity = (int) resultMap.get("Quantity");
            this.plantPicture = owner.getPictureHandler().convertBlobToBufferedImage((Blob) resultMap.get("Picture"));
            plantMap.clear();
            plantMap.put("Plant_ID", id);
            resultList = owner.gzdbc.select(GZDBTables.PLANTSCHEDULE, plantMap);
            for (LinkedHashMap<String, Object> timeEntry : resultList) {
                schedule.add((Time) timeEntry.get("WaterTime"));
            }
        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }

        /* Picture Handler required
         */
    }

    /**
     * Creates a new Plant object with the assigned parameters, which then is
     * stored into the database.
     *
     * @param name The name identifier of this plant.
     * @param plantingDate The date it was planted on.
     * @param description The description it will display for a better
     * understanding of this plant.
     * @param quantity The amount of singular plants of this type of plant.
     * @param schedule The times at which it needs to be watered.
     * @param plantPicture The real life picture representation of this plant.
     * @param owner The user who owns this plant.
     * @throws InvalidPlantException If the user does not provide a valid
     * connection, the name or description exceed the allowed length, if the
     * quantity is not a valid number.
     */
    public Plant(String name, String description, int quantity, LocalDate plantingDate, ArrayList<LocalTime> schedule,
            BufferedImage plantPicture, User owner) throws InvalidPlantException {
        if (!GZFormatter.isValidMaxLength(name, 25)) {
            throw new InvalidPlantException(PlantExceptions.LENGTH_NAME);
        }
        if (!GZFormatter.isValidMaxLength(description, 500)) {
            throw new InvalidPlantException(PlantExceptions.LENGTH_DESCRIPTION);
        }
        if (quantity < 1) {
            throw new InvalidPlantException(PlantExceptions.QUANTITY);
        }
        if (plantingDate == null || plantingDate.isBefore(LocalDate.of(1900, 1, 1))) {
            throw new InvalidPlantException(PlantExceptions.DATE);
        }
        if (schedule == null || schedule.isEmpty()) {
            throw new InvalidPlantException(PlantExceptions.SCHEDULE);
        }
        if (owner == null) {
            throw new InvalidPlantException(PlantExceptions.OWNER);
        }
        try {
            LinkedHashMap<String, Object> plantMap = new LinkedHashMap<>();
            plantMap.put("Name", name);
            plantMap.put("Description", description);
            plantMap.put("Quantity", quantity);
            plantMap.put("PlantingDate", Date.valueOf(plantingDate));
            if (plantPicture != null) {
                plantMap.put("Picture", owner.getPictureHandler().convertBufferedImageToBlob(plantPicture, owner.getConnection()));
            } else {
                plantMap.put("Picture", owner.getPictureHandler().convertBufferedImageToBlob(owner.getPictureHandler().getDEFAULT_PLANT(), owner.getConnection()));
            }
            plantMap.put("PUser_ID", owner.getId());
            owner.gzdbc.insert(GZDBTables.PLANT, plantMap);
            this.id = (int) owner.gzdbc.select(GZDBTables.PLANT, plantMap).get(0).get("ID");
            for (LocalTime waterTime : schedule) {
                plantMap.clear();
                plantMap.put("WaterTime", Time.valueOf(waterTime));
                plantMap.put("Plant_ID", id);
                owner.gzdbc.insert(GZDBTables.PLANTSCHEDULE, plantMap);
            }
            this.name = name;
            this.description = description;
            this.quantity = quantity;
            this.plantingDate = Date.valueOf(plantingDate);
            for (LocalTime waterTime : schedule) {
                this.schedule.add(Time.valueOf(waterTime));
            }
            if (plantPicture != null) {
                this.plantPicture = plantPicture;
            } else {
                this.plantPicture = owner.getPictureHandler().getDEFAULT_PLANT();
            }
            this.owner = owner;

        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }

    }

    /**
     * {@inheritDoc} The ID of this plant.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * The owner that this plant belongs to, it can only belong to one User.
     *
     * @return The User object that has ownership of this plant.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * {@inheritDoc} The custom name of this plant.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc} The custom description of this plant.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc} The quantity of this type of plant.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * The date this plant was planted on.
     *
     * @return A date that provides said information from the database
     */
    public Date getPlantingDate() {
        return plantingDate;
    }

    /**
     * The different times at which this plant has to be watered.
     *
     * @return A list providing the different water times.
     */
    public ArrayList<Time> getSchedule() {
        return schedule;
    }

    /**
     * {@inheritDoc}. The respective image of this plant.
     */
    @Override
    public BufferedImage getPicture() {
        return plantPicture;
    }

    /**
     * The respective image of this plant in a Base64 encoding for easier web
     * application manipulation, handling and loading.
     *
     * @return A string on Base64 with the picture information.
     */
    public String getPictureBase64() {
        return owner.getPictureHandler().convertBufferedImageToBase64(plantPicture);
    }

    /**
     * Updates all the plants's data in a batch, if any given parameter is null
     * it get's omitted.
     *
     * @param newName The new name of this plant.
     * @param newDescription The new description of this plant.
     * @param newQuantity The new quantity of this type.
     * @param newPlantingDate The new date of this plant.
     * @param newSchedule A new list of times to be watered on.
     * @param newPlantPicture The new picture of this plant.
     * @return A long message indicating the state of the batch updates, which
     * ones failed and why, which one suscceded or if there was no updates at
     * all.
     */
    public String updatePlantBatch(String newName, String newDescription, int newQuantity, LocalDate newPlantingDate, ArrayList<LocalTime> newSchedule,
            BufferedImage newPlantPicture) {
        int updateCount = 0;
        StringBuilder messageBuilder = new StringBuilder();
        try {
            owner.appendUpdateMessage(messageBuilder, setName(newName));
            updateCount++;
        } catch (InvalidPlantException e) {
            owner.appendUpdateMessage(messageBuilder, e.getMessage());
            messageBuilder.append(e.getMessage());
        }
        try {
            owner.appendUpdateMessage(messageBuilder, setDescription(newDescription));
            updateCount++;
        } catch (InvalidPlantException e) {
            owner.appendUpdateMessage(messageBuilder, e.getMessage());
            messageBuilder.append(e.getMessage());
        }
        try {
            owner.appendUpdateMessage(messageBuilder, setQuantity(newQuantity));
            updateCount++;
        } catch (InvalidPlantException e) {
            owner.appendUpdateMessage(messageBuilder, e.getMessage());
            messageBuilder.append(e.getMessage());
        }
        try {
            owner.appendUpdateMessage(messageBuilder, setPlantingDate(newPlantingDate));
            updateCount++;
        } catch (InvalidPlantException e) {
            owner.appendUpdateMessage(messageBuilder, e.getMessage());
        }
        try {
            owner.appendUpdateMessage(messageBuilder, setPlantPicture(newPlantPicture));
            updateCount++;
        } catch (InvalidPlantException e) {
            owner.appendUpdateMessage(messageBuilder, e.getMessage());
        }
        try {
            owner.appendUpdateMessage(messageBuilder, setSchedule(newSchedule));
            updateCount++;
        } catch (InvalidPlantException e) {
            owner.appendUpdateMessage(messageBuilder, e.getMessage());
        }
        if (updateCount == 0) {
            return PlantConfirmations.NOCHANGES.getConfirmationMessage();
        }
        return messageBuilder.toString();
    }

    /**
     * Sets the name this plant will be identified with by common users.
     *
     * @param newName The new name of this plant.
     * @return A successful confirmation message.
     * @throws InvalidPlantException The plant's name exceeded the accepted
     * length or the user does not provide a database connection.
     */
    private String setName(String newName) throws InvalidPlantException {
        if (!GZFormatter.isValidLength(newName, 3, 25)) {
            throw new InvalidPlantException(PlantExceptions.LENGTH_NAME);
        }
        try {
            owner.resetMaps();
            owner.insertMap.put("Name", newName);
            owner.restrictionMap.put("ID", id);
            owner.gzdbc.update(GZDBTables.PLANT, owner.insertMap, owner.restrictionMap);
            this.name = newName;
            return PlantConfirmations.NAME_UPDATE.getConfirmationMessage();
        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }
    }

    /**
     * Sets the description this plant will provide.
     *
     * @param newDescription The new description of this plant.
     * @return A successful confirmation message.
     * @throws InvalidPlantException The plant's description exceeded the
     * accepted length or the user does not provide a database connection.
     */
    private String setDescription(String newDescription) throws InvalidPlantException {
        if (!GZFormatter.isValidMaxLength(newDescription, 500)) {
            throw new InvalidPlantException(PlantExceptions.LENGTH_DESCRIPTION);
        }
        try {
            owner.resetMaps();
            owner.insertMap.put("Description", newDescription);
            owner.restrictionMap.put("ID", id);
            owner.gzdbc.update(GZDBTables.PLANT, owner.insertMap, owner.restrictionMap);
            this.description = newDescription;
            return PlantConfirmations.DESCRIPTION_UPDATE.getConfirmationMessage();
        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }
    }

    /**
     * Sets he quantity there exists of this type of plant.
     *
     * @param newQuantity The new quantity of this type.
     * @return A successful confirmation message.
     * @throws InvalidPlantException The plant's quantity is not a valid number
     * or the user does not provide a database connection.
     */
    private String setQuantity(int newQuantity) throws InvalidPlantException {
        if (newQuantity < 1) {
            throw new InvalidPlantException(PlantExceptions.QUANTITY);
        }
        try {
            owner.resetMaps();
            owner.insertMap.put("Quantity", newQuantity);
            owner.restrictionMap.put("ID", id);
            owner.gzdbc.update(GZDBTables.PLANT, owner.insertMap, owner.restrictionMap);
            this.quantity = newQuantity;
            return PlantConfirmations.QUANTITY_UPDATE.getConfirmationMessage();
        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }
    }

    /**
     * Sets he date this plant was planted on.
     *
     * @param newPlantingDate The new date of this plant.
     * @return A successful confirmation message.
     * @throws InvalidPlantException The user does not provide a database
     * connection.
     */
    private String setPlantingDate(LocalDate newPlantingDate) throws InvalidPlantException {
        try {
            owner.resetMaps();
            owner.insertMap.put("PlantingDate", Date.valueOf(newPlantingDate));
            owner.restrictionMap.put("ID", id);
            owner.gzdbc.update(GZDBTables.PLANT, owner.insertMap, owner.restrictionMap);
            this.plantingDate = Date.valueOf(newPlantingDate);
            return PlantConfirmations.PLANTINGDATE_UPDATE.getConfirmationMessage();
        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }
    }

    /**
     * Sets the schedule that this plant specifies to be watered on.
     *
     * @param newSchedule A new list of times to be watered on.
     * @return A successful confirmation message.
     * @throws InvalidPlantException The user does not provide a database
     * connection.
     */
    private String setSchedule(ArrayList<LocalTime> newSchedule) throws InvalidPlantException {
        try {
            owner.resetMaps();
            owner.restrictionMap.put("Plant_ID", id);
            for (LocalTime waterTime : newSchedule) {
                owner.insertMap.put("WaterTime", Time.valueOf(waterTime));
                owner.gzdbc.update(GZDBTables.PLANTSCHEDULE, owner.insertMap, owner.restrictionMap);
                this.schedule.add(Time.valueOf(waterTime));
            }
            return PlantConfirmations.SCHEDULE_UPDATE.getConfirmationMessage();
        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }
    }

    /**
     * Sets a representative picture of this plant.
     *
     * @param newPlantPicture The new picture of this plant.
     * @return A successful confirmation message.
     * @throws InvalidPlantException The user does not provide a database
     * connection.
     */
    private String setPlantPicture(BufferedImage newPlantPicture) throws InvalidPlantException {
        try {
            boolean defaultFlag = false;
            owner.resetMaps();
            if (newPlantPicture != null) {
                owner.insertMap.put("Picture", owner.getPictureHandler().convertBufferedImageToBlob(newPlantPicture, owner.getConnection()));
            } else {
                owner.insertMap.put("Picture", owner.getPictureHandler().convertBufferedImageToBlob(owner.getPictureHandler().getDEFAULT_PLANT(), owner.getConnection()));
                defaultFlag = true;
            }
            owner.restrictionMap.put("ID", id);
            owner.gzdbc.update(GZDBTables.USER, owner.insertMap, owner.restrictionMap);
            if (!defaultFlag) {
                this.plantPicture = newPlantPicture;
                return PlantConfirmations.PICTURE_UPDATE.getConfirmationMessage();
            } else {
                this.plantPicture = owner.getPictureHandler().getDEFAULT_PLANT();
                return PlantConfirmations.PICTURE_DEFAULT.getConfirmationMessage();
            }
        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }
    }

    /**
     * Increases the total quantity of this type of plant by one unit.
     *
     * @throws InvalidPlantException The owner does not provide a valid
     * connection.
     */
    public void increaseQuantityBy1() throws InvalidPlantException {
        try {
            owner.resetMaps();
            owner.insertMap.put("Quantity", quantity + 1);
            owner.restrictionMap.put("ID", id);
            this.quantity = quantity + 1;
            owner.gzdbc.update(GZDBTables.PLANT, owner.insertMap, owner.restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }
    }

    /**
     * Decreases the total quantity of this type of plant by one unit.
     *
     * @throws InvalidPlantException The plant's quantity went below one or the
     * owner does not provide a valid connection.
     */
    public void decreaseQuantityBy1() throws InvalidPlantException {
        if (quantity == 1) {
            throw new InvalidPlantException(PlantExceptions.QUANTITY_DECREASEBY1);
        }
        try {
            owner.resetMaps();
            owner.insertMap.put("Quantity", quantity - 1);
            owner.restrictionMap.put("ID", id);
            this.quantity = quantity - 1;
            owner.gzdbc.update(GZDBTables.PLANT, owner.insertMap, owner.restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }
    }

    /**
     * {@inheritDoc}. The deletion of this plant registry completely.
     */
    @Override
    public void delete() {
        owner.resetMaps();
        owner.insertMap.put("ID", id);
        try {
            owner.gzdbc.delete(GZDBTables.PLANT, owner.insertMap);
        } catch (GZDBResultException e) {
        }
    }

}
