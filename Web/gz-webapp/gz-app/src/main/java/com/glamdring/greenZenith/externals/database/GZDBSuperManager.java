package com.glamdring.greenZenith.externals.database;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;

/**
 * Establishes a connection with the database and obtains the different field
 * names and data types of a specific table.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
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
    public LinkedHashMap<String, Object> insertMap = null;
    /**
     * A map used for establishing conditions when doing SQL Queries and
     * Actions, usually handled by the children class.
     */
    public LinkedHashMap<String, Object> restrictionMap = null;
    /**
     * A map used for obtaining resulting data when doing SQL Queries, usually
     * handled by the children class.
     */
    public LinkedHashMap<String, Object> resultMap = null;

    /**
     * A map defined by the database that contains all the names of each field
     * within the defined table.
     */
    public LinkedHashSet<String> tableFields = null;
    /**
     * A map defined by the database that contains all the names of each data
     * type with their respective field name as key.
     */
    public LinkedHashMap<String, String> tableTypes = null;

    /**
     * Establishes a connection to the MySQL Server and database utilizing the
     * predefined credentials.
     *
     * @param table The table to define the field names and data types from.
     * @throws GZDBResultException If the table cannot be found or the
     * connection cannot be resolved.
     */
    public GZDBSuperManager(GZDBTables table) throws GZDBResultException {
        gzdbc = new GZDBConnector();
        tableFields = gzdbc.getTableFields(table);
        tableTypes = gzdbc.getTableTypes(table);
    }

    /**
     * Establishes a connection to the MySQL Server and database utilizing user
     * defined credentials as parameters.
     *
     * @param table The table to define the field names and data types from.
     * @param urlDB The URL where the MySQL Server and database is located on.
     * @param usernameDB The username to use for accessing the MySQL Server.
     * @param passwordDB The respective password to grant access to the MySQL
     * Server.
     * @throws GZDBResultException If the table cannot be found or the
     * connection cannot be resolved.
     */
    public GZDBSuperManager(GZDBTables table, String urlDB, String usernameDB, String passwordDB) throws GZDBResultException {
        gzdbc = new GZDBConnector(urlDB, usernameDB, passwordDB);
        tableFields = gzdbc.getTableFields(table);
        tableTypes = gzdbc.getTableTypes(table);
    }

}
