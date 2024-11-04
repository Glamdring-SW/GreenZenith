package com.glamdring.greenZenith.userInteractions.plants;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.application.plant.InvalidPlantException;
import com.glamdring.greenZenith.exceptions.application.plant.constants.PlantExceptions;
import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.application.user.constants.UserExceptions;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.handlers.formats.GZFormatter;
import com.glamdring.greenZenith.userInteractions.Attributable;
import com.glamdring.greenZenith.userInteractions.Interactable;
import com.glamdring.greenZenith.userInteractions.Killable;
import com.glamdring.greenZenith.userInteractions.users.User;

/**
 * Represents a plant or plant set owned by a User, with it's respective
 * attributes, allowing the creation, modification and deletion of it.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.1
 */
public class Plant implements Killable, Interactable, Attributable, Serializable {

    /**
     * The unique identificator for each database registry.
     */
    private final int id;
    /**
     * A unique name for easier indetification of a plant.
     */
    private String name;
    /**
     * The date a plant was planted on.
     */
    private Date plantingDate;
    /**
     * A concise description of a plant.
     */
    private String description;
    /**
     * The amount of equal plants of this singular type that a user posseses.
     */
    private int quantity;
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
    private User owner;

    /**
     * Retrieves information form the database utilizing only the ID and owner,
     * and assigns the results to each correspondant field.
     *
     * @param id The unique identifier that corresponds to a certain User
     * registry on the database.
     * @param owner The user that owns this specific plant.
     * @throws GZDBResultException If the user does not provide a valid
     * connection.
     */
    public Plant(int id, User owner) throws GZDBResultException {
        this.id = id;
        this.owner = owner;

        LinkedHashMap<String, Object> plantMap = new LinkedHashMap<>();
        plantMap.put("ID", id);
        LinkedHashMap<String, Object> resultMap = owner.gzdbc.select(GZDBTables.PLANT, plantMap).get(0);

        this.name = (String) resultMap.get("Name");
        this.plantingDate = (Date) resultMap.get("PlantingDate");
        this.description = (String) resultMap.get("Description");
        this.quantity = (int) resultMap.get("Quantity");

        plantMap.clear();
        plantMap.put("Plant_ID", id);
        ArrayList<LinkedHashMap<String, Object>> resultList = owner.gzdbc.select(GZDBTables.PLANTSCHEDULE, plantMap);
        for (LinkedHashMap<String, Object> timeEntry : resultList) {
            schedule.add((Time) timeEntry.get("WaterTime"));
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
     * @param owner The user who owns this plant.
     * @throws GZDBResultException If the user does not provide a valid
     * connection.
     */
    public Plant(String name, Date plantingDate, String description, int quantity, ArrayList<Time> schedule,
            /*BufferedImage plantPicture*/ User owner) throws GZDBResultException {
        this.name = name;
        this.plantingDate = plantingDate;
        this.description = description;
        this.quantity = quantity;
        this.schedule = schedule;
        this.owner = owner;

        LinkedHashMap<String, Object> plantMap = new LinkedHashMap<>();
        plantMap.put("Name", name);
        plantMap.put("PlantingDate", plantingDate);
        plantMap.put("Description", description);
        plantMap.put("Quantity", quantity);
        plantMap.put("PUser_ID", owner.getId());
        owner.gzdbc.insert(GZDBTables.PLANT, plantMap);
        this.id = (int) owner.gzdbc.select(GZDBTables.PLANT, plantMap).get(0).get("ID");

        plantMap.clear();
        plantMap.put("Plant_ID", id);
        for (Time waterTime : schedule) {
            plantMap.put("WaterTime", waterTime);
            owner.gzdbc.insert(GZDBTables.PLANTSCHEDULE, plantMap);
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
     * Sets the name this plant will be identified with by common users.
     *
     * @param name The new name of this plant.
     * @throws InvalidPlantException The plant's name exceeded the accepted
     * length.
     * @throws InvalidUserException The user does not provide a database
     * connection.
     */
    public void setName(String name) throws InvalidPlantException, InvalidUserException {
        if (!GZFormatter.isValidLength(name, 3, 25)) {
            throw new InvalidPlantException(PlantExceptions.LENGTH_NAME);
        }
        this.name = name;
        owner.resetMaps();
        owner.insertMap.put("Name", name);
        owner.restrictionMap.put("ID", id);
        try {
            owner.gzdbc.update(GZDBTables.PLANT, owner.insertMap, owner.restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
        }
    }

    /**
     * Sets the description this plant will provide.
     *
     * @param description The new description of this plant.
     * @throws InvalidPlantException The plant's description exceeded the
     * accepted length.
     * @throws InvalidUserException The user does not provide a database
     * connection.
     */
    public void setDescription(String description) throws InvalidPlantException, InvalidUserException {
        if (!GZFormatter.isValidMaxLength(description, 500)) {
            throw new InvalidPlantException(PlantExceptions.LENGTH_DESCRIPTION);
        }
        this.description = description;
        owner.resetMaps();
        owner.insertMap.put("Description", description);
        owner.restrictionMap.put("ID", id);
        try {
            owner.gzdbc.update(GZDBTables.PLANT, owner.insertMap, owner.restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
        }
    }

    /**
     * Sets he quantity there exists of this type of plant.
     *
     * @param quantity The new quantity of this type.
     * @throws InvalidPlantException The plant's quantity is not a valid number.
     * @throws InvalidUserException The user does not provide a database
     * connection.
     */
    public void setQuantity(int quantity) throws InvalidPlantException, InvalidUserException {
        if (quantity < 1) {
            throw new InvalidPlantException(PlantExceptions.QUANTITY);
        }
        this.quantity = quantity;
        owner.resetMaps();
        owner.insertMap.put("Quantity", quantity);
        owner.restrictionMap.put("ID", id);
        try {
            owner.gzdbc.update(GZDBTables.PLANT, owner.insertMap, owner.restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
        }
    }

    /**
     * Sets he date this plant was planted on.
     *
     * @param plantingDate The new date of this plant.
     * @throws InvalidUserException The user does not provide a database
     * connection.
     */
    public void setPlantingDate(Date plantingDate) throws InvalidUserException {
        this.plantingDate = plantingDate;
        owner.resetMaps();
        owner.insertMap.put("PlantingDate", plantingDate);
        owner.restrictionMap.put("ID", id);
        try {
            owner.gzdbc.update(GZDBTables.PLANT, owner.insertMap, owner.restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
        }
    }

    /**
     * Sets the schedule that this plant specifies to be watered on.
     *
     * @param schedule A new list of times to be watered on.
     * @throws InvalidUserException The user does not provide a database
     * connection.
     */
    public void setSchedule(ArrayList<Time> schedule) throws InvalidUserException {
        this.schedule = schedule;
        owner.resetMaps();
        owner.restrictionMap.put("Plant_ID", id);
        for (Time waterTime : schedule) {
            owner.insertMap.put("WaterTime", waterTime);
            try {
                owner.gzdbc.update(GZDBTables.PLANTSCHEDULE, owner.insertMap, owner.restrictionMap);
            } catch (GZDBResultException e) {
                throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
            }
        }

    }

    /**
     * Sets a representative picture of this plant.
     *
     * @param plantPicture The new picture of this plant.
     */
    public void setPlantPicture(BufferedImage plantPicture) {
        this.plantPicture = plantPicture;
        /*
         Work in progress
         */
    }

    /**
     * Decreases the total quantity of this type of plant by one unit.
     *
     * @throws InvalidPlantException The plant's quantity went below one.
     * @throws InvalidUserException The user does not provide a database
     * connection.
     */
    public void decreaseQuantityBy1() throws InvalidPlantException, InvalidUserException {
        if (quantity == 1) {
            throw new InvalidPlantException(PlantExceptions.QUANTITY);
        }
        this.quantity = quantity - 1;
        owner.resetMaps();
        owner.insertMap.put("Quantity", quantity);
        owner.restrictionMap.put("ID", id);
        try {
            owner.gzdbc.update(GZDBTables.PLANT, owner.insertMap, owner.restrictionMap);
        } catch (GZDBResultException e) {
            throw new InvalidUserException(UserExceptions.GZDBCONNECTION);
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
