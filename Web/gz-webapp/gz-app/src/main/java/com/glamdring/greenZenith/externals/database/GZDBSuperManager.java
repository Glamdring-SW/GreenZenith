package com.glamdring.greenZenith.externals.database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.database.GZDBResultException;

/**
 * Establishes a connection with the database and obtains the different field
 * names and data types of a specific table.
 *
 * @author Glamdring (Σxz)
 * @version 1.2.2
 * @since 0.1
 */
public class GZDBSuperManager {

    /**
     * The connector utilized to access the database.
     */
    public GZDBConnector gzdbc;

    /**
     * A map used for insertions into the database table, usually handled by the
     * children class.
     */
    public LinkedHashMap<String, Object> insertMap = new LinkedHashMap<>();
    /**
     * A map used for establishing conditions when doing SQL Queries and
     * Actions, usually handled by the children class.
     */
    public LinkedHashMap<String, Object> restrictionMap = new LinkedHashMap<>();
    /**
     * A map used for obtaining resulting data when doing SQL Queries, usually
     * handled by the children class.
     */
    public ArrayList<LinkedHashMap<String, Object>> resultList = new ArrayList<>();

    /**
     * Establishes a connection to the MySQL Server and database utilizing the
     * predefined credentials.
     *
     * @throws GZDBResultException If the table cannot be found or the
     * connection cannot be resolved.
     */
    public GZDBSuperManager() throws GZDBResultException {
        gzdbc = new GZDBConnector();
    }

    /**
     * Resets the insertion and restriction maps to be used as new, so any
     * garbage data is not utilized on new usages.
     */
    public void resetMaps() {
        insertMap.clear();
        restrictionMap.clear();
    }

    /**
     * Adds the confirmation message to a String for future usage as feedback of
     * an update.
     *
     * @param messageBuilder The string that builds the complete feedback
     * message.
     * @param message The message to append.
     */
    public void appendUpdateMessage(StringBuilder messageBuilder, String message) {
        char lineFeed = 10;
        char carriageReturn = 13;
        messageBuilder.append(message);
        messageBuilder.append(lineFeed);
        messageBuilder.append(carriageReturn);
    }

    /**
     * The connection used for the database.
     *
     * @return A connection
     */
    public Connection getConnection() {
        return gzdbc.getConnection();
    }

}
