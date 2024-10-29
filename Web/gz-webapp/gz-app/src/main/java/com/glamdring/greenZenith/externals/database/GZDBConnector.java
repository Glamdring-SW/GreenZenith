package com.glamdring.greenZenith.externals.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.exceptions.database.constants.GZDBExceptionMessages;
import com.glamdring.greenZenith.externals.database.constants.GZDBActions;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;

/**
 * The GZDBConnector provides methods for connecting to a MySQL database,
 * performing various CRUD operations, and retrieving table information.
 * <p>
 * It utilizes a JDBC Driver and some default credentials.
 * <p>
 * @author Glamdring (Σxz)
 * @version 2.0.0
 * @since 0.1
 */
public class GZDBConnector {

    /**
     * The constant string indicating the name of the JDBC Driver used.
     */
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * The constant string indicating the default database URL.
     */
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/GreenZenith";
    /**
     * The constant string indicating the default database user.
     */
    private static final String DATABASE_USERNAME = "root";
    /**
     * The constant string indicating the default database password associated
     * with the default user.
     */
    private static final String DATABASE_PASSWORD = "1234";

    /**
     * Establishes the connection to the desired MySQL Server.
     */
    private Connection connection;
    /**
     * Prepares different SQL statements for the CRUD operations.
     */
    private PreparedStatement preparedStatement;
    /**
     * Executes multiple CRUD actions, prepares different statement strings,
     * establishes inputs and outputs for the CRUD operations.
     */
    private GZDBExecutor executor;
    /**
     * Contains the different results of executed SQL Queries.
     */
    private LinkedHashMap<String, Object> resultMap;

    /**
     * The main constructor for this class, utilizes our default MySQL
     * connection credentials.
     * <p>
     * @throws GZDBResultException If the JDBC driver cannot be found or the
     * default arguments for accessing the database cannot connect resolve a
     * connection, this exception will be thrown. For more information @see
     * GZDBResultException
     */
    public GZDBConnector() throws GZDBResultException {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new GZDBResultException(GZDBExceptionMessages.CONSTRUCTION_CONNECTOR, e);
        }
    }

    /**
     * A secondary constructor for this class, requires other user-defined
     * credentials.
     * <p>
     * @param urlDB The URL required to access the MySQL server and database.
     * @param usernameDB The name of the user that will access the database.
     * @param passwordDB The password needed to access the database with the
     * respective username.
     * @throws GZDBResultException If the JDBC driver cannot be found or the
     * arguments provided for accessing the database cannot resolve a
     * connection, this exception will be thrown.
     */
    public GZDBConnector(String urlDB, String usernameDB, String passwordDB) throws GZDBResultException {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
        } catch (SQLException | ClassNotFoundException e) {
            throw new GZDBResultException(GZDBExceptionMessages.CONSTRUCTION_CONNECTOR, e);
        }
    }

    /**
     * Performs database actions like insert, select, update, or delete, based
     * on the provided parameters and returns a LinkedHashMap with the result.
     * <p>
     * @param action The SQL Action to be performed on the database. @see
     * GZDBActions
     * @param table The table in which the SQL action will be performed. @see
     * GZDBTables
     * @param insertMap A map that defines the input to use for SQL Actions.
     * @param restrictionMap A map that defines the restrictions for SQL
     * Queries.
     * @return A map containing either a confirmation of a successful SQL Action
     * or the result of a SQL Query.
     * @throws GZDBResultException A message indicating which SQL Action or
     * Query failed and the map that made the failure happen. @see
     * GZDBResultException
     */
    public LinkedHashMap<String, Object> makeAction(GZDBActions action, GZDBTables table, LinkedHashMap<String, Object> insertMap, LinkedHashMap<String, Object> restrictionMap) throws GZDBResultException {
        executor = new GZDBExecutor(preparedStatement, connection, action, table);
        resultMap = new LinkedHashMap<>();
        switch (action) {
            case INSERT -> {
                try {
                    executor.executeInsert(insertMap);
                    resultMap.put("RESULT_STATE", (Boolean) true);
                } catch (SQLException e) {
                    throw new GZDBResultException(GZDBExceptionMessages.INSERT, insertMap, e);
                }
            }
            case SELECT -> {
                try {
                    resultMap = executor.executeSelect(insertMap);
                } catch (SQLException e) {
                    throw new GZDBResultException(GZDBExceptionMessages.SELECT, insertMap, e);
                }
            }
            case UPDATE -> {
                try {
                    executor.executeUpdate(insertMap, restrictionMap);
                    resultMap.put("RESULT_STATE", (Boolean) true);
                } catch (SQLException e) {
                    throw new GZDBResultException(GZDBExceptionMessages.UPDATE, insertMap, e);
                }
            }
            case DELETE -> {
                try {
                    executor.executeDelete(insertMap);
                    resultMap.put("RESULT_STATE", (Boolean) true);
                } catch (SQLException e) {
                    throw new GZDBResultException(GZDBExceptionMessages.DELETE, insertMap, e);
                }
            }
            default -> {
                throw new GZDBResultException(GZDBExceptionMessages.UNREACHABLE_CASE);
            }
        }
        return resultMap;
    }

    /**
     * Executes an automatic SQL Query to look for the names of the different
     * fields contained on a table.
     * <p>
     * @param table The table to retrieve the field names. @see GDZBTables
     * @return A set containing all the field names.
     * @throws GZDBResultException Indicates a failiure on the automatic SQL
     * Query caused by the table of input.
     */
    public LinkedHashSet<String> getTableFields(GZDBTables table) throws GZDBResultException {
        executor = new GZDBExecutor(preparedStatement, connection, GZDBActions.SELECT, table);
        try {
            return executor.getTableFields();
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.SELECT, e);
        }
    }

    /**
     * Executes an automatic SQL Query to look for the types and respective
     * names of the different fields contained on a table.
     * <p>
     * @param table The table to retrieve the field types. @see GDZBTables
     * @return A map containing all the field types with names as keys.
     * @throws GZDBResultException Indicates a failiure on the automatic SQL
     * Query caused by the table of input.
     */
    public LinkedHashMap<String, String> getTableTypes(GZDBTables table) throws GZDBResultException {
        executor = new GZDBExecutor(preparedStatement, connection, GZDBActions.SELECT, table);
        try {
            return executor.getTableTypes();
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.SELECT, e);
        }
    }

    /**
     * Closes the connection to the database.
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {

        }
    }
}
