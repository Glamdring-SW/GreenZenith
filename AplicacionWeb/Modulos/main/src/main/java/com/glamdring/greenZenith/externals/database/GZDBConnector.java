package com.glamdring.greenZenith.externals.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.HashSet;

import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.exceptions.database.constants.GZDBExceptionMessages;
import com.glamdring.greenZenith.externals.database.constants.GZDBActions;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;

public class GZDBConnector {

    // final private String driverName = "com.mysql.cj.jdbc.Driver";
    final private String urlDB = "jdbc:mysql://localhost:3306/GreenZenith";
    final private String usernameDB = "root";
    final private String passwordDB = "1234";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private GZDBExecutor executor;
    private LinkedHashMap<String, Object> resultMap;

    public GZDBConnector() throws GZDBResultException {
        try {
            connection = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.CONSTRUCTION_CONNECTOR, e);
        }
    }

    public GZDBConnector(String urlDB, String usernameDB, String passwordDB) throws GZDBResultException {
        try {
            connection = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.CONSTRUCTION_CONNECTOR, e);
        }
    }

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

    public HashSet<String> getTableFields(GZDBTables table) throws GZDBResultException {
        executor = new GZDBExecutor(preparedStatement, connection, GZDBActions.SELECT, table);
        try {
            return executor.getTableFields();
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.SELECT, e);
        }
    }

    public LinkedHashMap<String, String> getTableTypes(GZDBTables table) throws GZDBResultException {
        executor = new GZDBExecutor(preparedStatement, connection, GZDBActions.SELECT, table);
        try {
            return executor.getTableTypes();
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.SELECT, e);
        }
    }
}
