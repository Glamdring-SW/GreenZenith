package com.glamdring.greenZenith.userInteractions.plants;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.application.plant.InvalidPlantException;
import com.glamdring.greenZenith.exceptions.application.plant.constants.PlantExceptions;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.GZDBConnector;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.userInteractions.users.User;

/**
 * Provides a list that contains all the different plants that a user posseses.
 *
 * @author Glamdring (Σxz)
 * @version 1.1.0
 * @since 0.1
 */
public class PlantList {

    /**
     * A list containing all the specified plants.
     */
    ArrayList<Plant> plantList = new ArrayList<>();
    /**
     * A list containing all the specified plants by using their IDs as keys.
     */
    LinkedHashMap<Integer, Plant> plantMap = new LinkedHashMap<>();

    /**
     * Initializes all the plants from the database. in a lit and map.
     *
     * @param gzdbc A connection to the database to retrieve data correctly.
     * @throws InvalidPlantException The connection to the database is not
     * valid.
     */
    public PlantList(GZDBConnector gzdbc) throws InvalidPlantException {
        try {
            ArrayList<LinkedHashMap<String, Object>> plantListDB = gzdbc.selectAll(GZDBTables.PLANT);
            if (!plantListDB.isEmpty()) {
                for (LinkedHashMap<String, Object> plant : plantListDB) {
                    int id = (int) plant.get("ID");
                    plantList.add(new Plant(id, gzdbc));
                    plantMap.put(id, new Plant(id, gzdbc));
                }
            }
        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.DATABASE, e);
        }
    }

    /**
     * Initializes the list and map of this user's plants.
     *
     * @param owner The user to get all the plants from.
     * @throws InvalidPlantException If the user does not provide a valid
     * connection.
     */
    public PlantList(User owner) throws InvalidPlantException {
        super();
        owner.resetMaps();
        owner.insertMap.put("PUser_ID", owner.getId());
        try {
            ArrayList<LinkedHashMap<String, Object>> plantListDB = owner.gzdbc.select(GZDBTables.PLANT, owner.insertMap);
            if (!plantListDB.isEmpty()) {
                for (LinkedHashMap<String, Object> plant : plantListDB) {
                    int id = (int) plant.get("ID");
                    plantList.add(new Plant(id, owner));
                    plantMap.put(id, new Plant(id, owner));
                }
            }
        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }
    }

    /**
     * The list with all the defined plants, either from the total or from a
     * User.
     *
     * @return A listh with the data of each plant.
     */
    public ArrayList<Plant> getPlantList() {
        return plantList;
    }

    /**
     * The map with all the defined plants, either from the total or from a
     * User.
     *
     * @return A map with the data of each plant and their IDs as keys.
     */
    public LinkedHashMap<Integer, Plant> getPlantMap() {
        return plantMap;
    }

    /**
     * Gets the plant data from the map that contains all the plants.
     *
     * @param id The ID of the plant to look for.
     * @return The plant data.
     * @throws InvalidPlantException If the ID does not resolve to any plant
     * registry.
     */
    public Plant getFromMap(int id) throws InvalidPlantException {
        if (plantMap.get(id) != null) {
            return plantMap.get(id);
        } else {
            throw new InvalidPlantException(PlantExceptions.INEXISTANT);
        }
    }

    /**
     * Gets the plant data from the list that contains all the plants.
     *
     * @param id The ID of the plant to look for.
     * @return The plant data.
     * @throws InvalidPlantException If the ID does not resolve to any plant
     * registry.
     */
    public Plant getFromList(int id) throws InvalidPlantException {
        for (int i = 0; i < plantList.size(); i++) {
            if (plantList.get(i).getId() == id) {
                return plantList.get(i);
            }
        }
        throw new InvalidPlantException(PlantExceptions.INEXISTANT);
    }

    /**
     * Add a new plant to the list and map of plants.
     *
     * @param plant The new plant to be added.
     */
    public void add(Plant plant) {
        int id = plant.getId();
        plantList.add(plant);
        plantMap.put(id, plant);
    }

    /**
     * Updates all the data of a certain plant given by it's ID, any null or
     * empy parameters are not considered for the update.
     *
     * @param id The ID of the plant to be updated.
     * @param newName The new name of this plant.
     * @param newDescription The new description of this plant.
     * @param newQuantity The new quantity of this type.
     * @param newPlantingDate The new date of this plant.
     * @param newSchedule A new list of times to be watered on.
     * @param newPlantPicture The new picture of this plant.
     * @return A message indicating the successfulness and failure of the
     * update.
     * @throws InvalidPlantException If the ID does not resolve to any plant
     * registry.
     */
    public String update(int id, String newName, String newDescription, int newQuantity, LocalDate newPlantingDate, ArrayList<LocalTime> newSchedule,
            BufferedImage newPlantPicture) throws InvalidPlantException {
        if (plantMap.get(id) != null) {
            throw new InvalidPlantException(PlantExceptions.INEXISTANT);
        }
        return plantMap.get(id).updatePlantBatch(newName, newDescription, newQuantity, newPlantingDate, newSchedule, newPlantPicture);
    }

    /**
     * Delest a plant from the database and then the list and maps/
     *
     * @param id The ID of the plant to be deleted.
     * @throws InvalidPlantException If the ID does not resolve to any plant
     * registry.
     */
    public void delete(int id) throws InvalidPlantException {
        for (int i = 0; i < plantList.size(); i++) {
            if (plantList.get(i).getId() == id) {
                plantMap.get(id).delete();
                plantList.remove(i);
                plantMap.remove(id);
                break;
            }
        }
        throw new InvalidPlantException(PlantExceptions.INEXISTANT);
    }
}
