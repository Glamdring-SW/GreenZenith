package com.glamdring.greenZenith.externals.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.exceptions.database.constants.GZDBExceptionMessages;
import com.glamdring.greenZenith.externals.database.constants.GZDBActions;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;

public class GZDBConnector {

    // final private String driverName = "com.mysql.cj.jdbc.Driver";
    final private String urlDB = "jdbc:mysql://localhost:3306/GreenZenith";
    final private String usernameDB = "Administrator";
    final private String passwordDB = "1234";

    private Connection connection;
    private CallableStatement callableStatement;
    private GZDBExecutor executor;
    private HashMap<String, Object> resultMap;

    public GZDBConnector() throws GZDBResultException {
        try {
            connection = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.CONSTRUCTION_CONNECTOR);
        }
    }

    public GZDBConnector(String urlDB, String usernameDB, String passwordDB) throws GZDBResultException {
        try {
            connection = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.CONSTRUCTION_CONNECTOR);
        }
    }

    public HashMap<String, Object> makeAction(GZDBActions action, GZDBTables table, HashMap<String, Object> insertMap, HashMap<String, Object> restrictionMap) throws GZDBResultException {
        executor = new GZDBExecutor(callableStatement, connection, action, table);
        resultMap = new HashMap<>();

        switch (action) {
            case INSERT -> {
                try {
                    executor.executeInsert(insertMap);
                    resultMap.put("RESULT_STATE", (Boolean) true);
                } catch (SQLException e) {
                    throw new GZDBResultException(GZDBExceptionMessages.INSERT, insertMap);
                }
            }
            case SELECT -> {
                try {
                    resultMap = executor.executeSelect(insertMap);
                } catch (SQLException e) {
                    throw new GZDBResultException(GZDBExceptionMessages.SELECT, insertMap);
                }
            }
            case UPDATE -> {
                try {
                    executor.executeUpdate(insertMap, restrictionMap);
                    resultMap.put("RESULT_STATE", (Boolean) true);
                } catch (SQLException e) {
                    throw new GZDBResultException(GZDBExceptionMessages.UPDATE, insertMap);
                }
            }
            case DELETE -> {
                try {
                    executor.executeDelete(insertMap);
                    resultMap.put("RESULT_STATE", (Boolean) true);
                } catch (SQLException e) {
                    throw new GZDBResultException(GZDBExceptionMessages.DELETE, insertMap);
                }
            }
            default -> {
                throw new GZDBResultException(GZDBExceptionMessages.UNREACHABLE_CASE, insertMap);
            }
        }
        return resultMap;
    }

    public HashSet<String> getTableFields(GZDBTables table) throws GZDBResultException {
        executor = new GZDBExecutor(callableStatement, connection, GZDBActions.SELECT, table);
        try {
            return executor.getTableFields();
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.SELECT);
        }
    }

    public HashMap<String, String> getTableTypes(GZDBTables table) throws GZDBResultException {
        executor = new GZDBExecutor(callableStatement, connection, GZDBActions.SELECT, table);
        try {
            return executor.getTableTypes();
        } catch (SQLException e) {
            throw new GZDBResultException(GZDBExceptionMessages.SELECT);
        }
    }
}
