package com.glamdring.greenZenith.externals.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.exceptions.database.constants.GZDBExceptionMessages;
import com.glamdring.greenZenith.externals.database.constants.GZDBActions;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;

/**
 * Provides methods for connecting to a MySQL database, performing various CRUD
 * operations, and retrieving table information. It utilizes a JDBC Driver and
 * some default credentials.
 *
 * @author Glamdring (Σxz)
 * @version 2.1.1
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
     * The main constructor for this class, utilizes our default MySQL
     * connection credentials.
     *
     * @throws GZDBResultException If the JDBC driver cannot be found or the
     * default arguments for accessing the database cannot connect resolve a
     * connection, this exception will be thrown. For more information @see
     * GZDBResultException
     */
    public GZDBConnector() throws GZDBResultException {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            connection.createStatement().execute("SET CHARACTER SET 'utf8'");
            connection.createStatement().execute("SET NAMES 'utf8'");
        } catch (SQLException | ClassNotFoundException e) {
            throw new GZDBResultException(GZDBExceptionMessages.CONSTRUCTION_CONNECTOR, e);
        }
    }

    /**
     * Performs an insertion into the database with a given map.
     *
     * @param table The table in which the SQL action will be performed. @see
     * GZDBTables
     * @param insertMap A map that defines the input to use for SQL Actions.
     * @throws GZDBResultException A message indicating which SQL Action or
     * Query failed and the map that made the failure happen. @see
     * GZDBResultException
     */
    public void insert(GZDBTables table, LinkedHashMap<String, Object> insertMap) throws GZDBResultException {
        executor = new GZDBExecutor(connection, preparedStatement, GZDBActions.INSERT, table);
        try {
            executor.executeInsert(insertMap);
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.INSERT, insertMap, e);
        }
    }

    /**
     * Performs a selection from the database and returns the matching elements
     * contained in a list.
     *
     * @param table The table in which the SQL action will be performed. @see
     * GZDBTables
     * @param selectmap A map that defines the restrictions to use for the SQL
     * Query.
     * @return A list providing maps that contain the different entries given by
     * the SQL Query.
     * @throws GZDBResultException A message indicating which SQL Action or
     * Query failed and the map that made the failure happen. @see
     * GZDBResultException
     */
    public ArrayList<LinkedHashMap<String, Object>> select(GZDBTables table, LinkedHashMap<String, Object> selectmap) throws GZDBResultException {
        executor = new GZDBExecutor(connection, preparedStatement, GZDBActions.SELECT, table);
        try {
            return executor.executeSelect(selectmap);
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.SELECT, selectmap, e);
        }
    }

    /**
     * Performs a selection from the database and returns all the elements of
     * the given table.
     *
     * @param table The table in which the SQL action will be performed. @see
     * GZDBTables
     * @return A list providing maps that contain the different entries given by
     * the SQL Query.
     * @throws GZDBResultException A message indicating which SQL Action or
     * Query failed and the map that made the failure happen. @see
     * GZDBResultException
     */
    public ArrayList<LinkedHashMap<String, Object>> selectAll(GZDBTables table) throws GZDBResultException {
        executor = new GZDBExecutor(connection, preparedStatement, GZDBActions.SELECT, table);
        try {
            return executor.executeSelectAll();
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.SELECT, e);
        }
    }

    /**
     * Performs an update of the datab of a certain table with the provided
     * restrictions.
     *
     * @param table The table in which the SQL action will be performed. @see
     * GZDBTables
     * @param updateMap A map that defines the updated data.
     * @param restrictionMap A map that defines the restrictions for updating an
     * entry.
     * @throws GZDBResultException A message indicating which SQL Action or
     * Query failed and the map that made the failure happen. @see
     * GZDBResultException
     */
    public void update(GZDBTables table, LinkedHashMap<String, Object> updateMap, LinkedHashMap<String, Object> restrictionMap) throws GZDBResultException {
        executor = new GZDBExecutor(connection, preparedStatement, GZDBActions.UPDATE, table);
        try {
            executor.executeUpdate(updateMap, restrictionMap);
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.UPDATE, updateMap, e);
        }
    }

    /**
     * Performs the deletion of a specific entry with the given restrictions.
     *
     * @param table The table in which the SQL action will be performed. @see
     * GZDBTables
     * @param deleteMap A map that defines the restrictions for the deletion of
     * an entry.
     * @throws GZDBResultException A message indicating which SQL Action or
     * Query failed and the map that made the failure happen. @see
     * GZDBResultException
     */
    public void delete(GZDBTables table, LinkedHashMap<String, Object> deleteMap) throws GZDBResultException {
        executor = new GZDBExecutor(connection, preparedStatement, GZDBActions.DELETE, table);
        try {
            executor.executeDelete(deleteMap);
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.DELETE, deleteMap, e);
        }
    }

    /**
     * Executes an automatic SQL Query to look for the names of the different
     * fields contained on a table.
     *
     * @param table The table to retrieve the field names. @see GDZBTables
     * @return A set containing all the field names.
     * @throws GZDBResultException Indicates a failiure on the automatic SQL
     * Query caused by the table of input.
     */
    public LinkedHashSet<String> getTableFields(GZDBTables table) throws GZDBResultException {
        executor = new GZDBExecutor(connection, preparedStatement, GZDBActions.SELECT, table);
        try {
            return executor.getTableFields();
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.SELECT, e);
        }
    }

    /**
     * Executes an automatic SQL Query to look for the types and respective
     * names of the different fields contained on a table.
     *
     * @param table The table to retrieve the field types. @see GDZBTables
     * @return A map containing all the field types with names as keys.
     * @throws GZDBResultException Indicates a failiure on the automatic SQL
     * Query caused by the table of input.
     */
    public LinkedHashMap<String, String> getTableTypes(GZDBTables table) throws GZDBResultException {
        executor = new GZDBExecutor(connection, preparedStatement, GZDBActions.SELECT, table);
        try {
            return executor.getTableTypes();
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.SELECT, e);
        }
    }

    /**
     * Retrieves the connection to the database, used mainly for BLOB creations.
     *
     * @return The connection to the database.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Closes the connection to the database.
     */
    public void close() {
        try {
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {

        }
    }
}
