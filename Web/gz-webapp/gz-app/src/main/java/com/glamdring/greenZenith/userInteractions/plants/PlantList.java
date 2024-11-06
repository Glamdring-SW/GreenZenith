package com.glamdring.greenZenith.userInteractions.plants;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.application.plant.InvalidPlantException;
import com.glamdring.greenZenith.exceptions.application.plant.constants.PlantExceptions;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.userInteractions.users.User;

/**
 * Provides a list that contains all the different plants that a user posseses.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.1
 */
public class PlantList extends ArrayList<Plant> {

    /**
     * Creates a list with all the plants that a user owns.
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
            ArrayList<LinkedHashMap<String, Object>> plantList = owner.gzdbc.select(GZDBTables.PLANT, owner.insertMap);
            if (!plantList.isEmpty()) {
                for (LinkedHashMap<String, Object> plant : plantList) {
                    this.add(new Plant((int) plant.get("ID"), owner));
                }
            }
        } catch (GZDBResultException e) {
            throw new InvalidPlantException(PlantExceptions.OWNER, e);
        }
    }

}
