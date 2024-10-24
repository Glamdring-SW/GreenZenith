package com.glamdring.greenZenith.externals.database;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.exceptions.database.constants.GZDBExceptionMessages;
import com.glamdring.greenZenith.externals.database.constants.GZDBActions;
import com.glamdring.greenZenith.externals.database.constants.GZDBReserved;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;

public class GZDBExecutor {

    private final char SPACE_CHAR = 32;
    private final char EVERYTHING_CHAR = 42;
    private final char COMMA_CHAR = 44;
    private final char EQUAL_CHAR = 61;
    private final char QUESTION_CHAR = 63;

    private final String DESCRIBE_TABLE = "DESCRIBE ?";
    private final String AND_OPERATOR = "AND";

    private StringBuilder statementBuilder;

    private final Connection connection;
    private final GZDBActions action;
    private final GZDBTables table;
    private CallableStatement callableStatement;

    public GZDBExecutor(CallableStatement callableStatement, Connection connection, GZDBActions action, GZDBTables table) {
        this.callableStatement = callableStatement;
        this.connection = connection;
        this.action = action;
        this.table = table;
    }

    private ResultSet describeTable() throws SQLException {
        callableStatement = connection.prepareCall(DESCRIBE_TABLE);
        callableStatement.setString(1, table.getTable());
        ResultSet resultDescriptionSet = callableStatement.executeQuery();
        return resultDescriptionSet;
    }

    public HashSet<String> getTableFields() throws SQLException {
        HashSet<String> fieldsSet = new HashSet<>();
        ResultSet resultSet = describeTable();
        while (resultSet.next()) {
            fieldsSet.add(resultSet.getString(GZDBReserved.FIELD.getKeyword()));
        }
        return fieldsSet;
    }

    public HashMap<String, String> getTableTypes() throws SQLException {
        HashMap<String, String> typesMap = new HashMap<>();
        ResultSet resultSet = describeTable();
        while (resultSet.next()) {
            typesMap.put(
                    resultSet.getString(GZDBReserved.FIELD.getKeyword()),
                    resultSet.getString(GZDBReserved.TYPE.getKeyword()));
        }
        return typesMap;
    }

    private HashMap<String, Object> getResultMapWithWhereClause(ResultSet resultSet, HashMap<String, String> typesMap) throws SQLException {
        HashMap<String, Object> resultMap = new HashMap<>();
        while (resultSet.next()) {
            for (String outputField : typesMap.keySet()) {
                if (typesMap.get(outputField).contains(GZDBReserved.BOOLEAN.getKeyword())) {
                    resultMap.put(outputField, resultSet.getBoolean(outputField));
                } else if (typesMap.get(outputField).contains(GZDBReserved.INT.getKeyword())) {
                    resultMap.put(outputField, resultSet.getInt(outputField));
                } else if (typesMap.get(outputField).contains(GZDBReserved.DECIMAL.getKeyword())) {
                    resultMap.put(outputField, resultSet.getBigDecimal(outputField));
                } else if (typesMap.get(outputField).contains(GZDBReserved.VARCHAR.getKeyword())) {
                    resultMap.put(outputField, resultSet.getString(outputField));
                } else if (typesMap.get(outputField).contains(GZDBReserved.TEXT.getKeyword())) {
                    resultMap.put(outputField, resultSet.getString(outputField));
                } else if (typesMap.get(outputField).contains(GZDBReserved.DATE.getKeyword())) {
                    resultMap.put(outputField, resultSet.getDate(outputField));
                } else if (typesMap.get(outputField).contains(GZDBReserved.TIME.getKeyword())) {
                    resultMap.put(outputField, resultSet.getTime(outputField));
                } else if (typesMap.get(outputField).contains(GZDBReserved.DATETIME.getKeyword())) {
                    resultMap.put(outputField, resultSet.getTimestamp(outputField));
                } else if (typesMap.get(outputField).contains(GZDBReserved.MEDIUMBLOB.getKeyword())) {
                    resultMap.put(outputField, resultSet.getBlob(outputField));
                }
            }
        }
        return resultMap;
    }

    private CallableStatement setCallableStatementArguments(CallableStatement callableStatement, HashMap<String, Object> inputMap, HashMap<String, String> typesMap) throws SQLException {
        for (String inputField : inputMap.keySet()) {
            if (typesMap.get(inputField).contains(GZDBReserved.BOOLEAN.getKeyword())) {
                callableStatement.setBoolean(inputField, (Boolean) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.INT.getKeyword())) {
                callableStatement.setInt(inputField, (Integer) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.DECIMAL.getKeyword())) {
                callableStatement.setBigDecimal(inputField, (BigDecimal) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.VARCHAR.getKeyword())) {
                callableStatement.setString(inputField, (String) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.TEXT.getKeyword())) {
                callableStatement.setString(inputField, (String) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.DATE.getKeyword())) {
                callableStatement.setDate(inputField, (Date) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.TIME.getKeyword())) {
                callableStatement.setTime(inputField, (Time) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.DATETIME.getKeyword())) {
                callableStatement.setTimestamp(inputField, (Timestamp) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.MEDIUMBLOB.getKeyword())) {
                callableStatement.setBlob(inputField, (Blob) inputMap.get(inputField));
            }
        }
        return callableStatement;
    }

    private CallableStatement setCallableStatementArguments(CallableStatement callableStatement, HashMap<String, Object> inputMap1, HashMap<String, Object> inputMap2, HashMap<String, String> typesMap) throws SQLException, GZDBResultException {
        Set<String> keys1 = inputMap1.keySet();
        Set<String> keys2 = inputMap2.keySet();
        keys1.retainAll(keys2);
        if (keys1.isEmpty()) {
            throw new GZDBResultException(GZDBExceptionMessages.KEYINPUT_2, inputMap2);
        }
        HashMap<String, Object> inputMap = new HashMap<>();
        inputMap.putAll(inputMap1);
        inputMap.putAll(inputMap2);
        for (String inputField : inputMap.keySet()) {
            if (typesMap.get(inputField).contains(GZDBReserved.BOOLEAN.getKeyword())) {
                callableStatement.setBoolean(inputField, (Boolean) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.INT.getKeyword())) {
                callableStatement.setInt(inputField, (Integer) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.DECIMAL.getKeyword())) {
                callableStatement.setBigDecimal(inputField, (BigDecimal) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.VARCHAR.getKeyword())) {
                callableStatement.setString(inputField, (String) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.TEXT.getKeyword())) {
                callableStatement.setString(inputField, (String) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.DATE.getKeyword())) {
                callableStatement.setDate(inputField, (Date) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.TIME.getKeyword())) {
                callableStatement.setTime(inputField, (Time) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.DATETIME.getKeyword())) {
                callableStatement.setTimestamp(inputField, (Timestamp) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.MEDIUMBLOB.getKeyword())) {
                callableStatement.setBlob(inputField, (Blob) inputMap.get(inputField));
            }
        }
        return callableStatement;
    }

    private String makeSelectValueClause() throws SQLException {
        StringBuilder valueClause = new StringBuilder();
        int index = getTableFields().size();
        valueClause.append("(");
        if (table.equals(GZDBTables.USER)
                || table.equals(GZDBTables.PLANT)
                || table.equals(GZDBTables.PRODUCT)
                || table.equals(GZDBTables.MESSAGE)) {
            valueClause.append("NULL, ");
            index--;
        }
        for (int i = 0; i < index - 1; i++) {
            valueClause.append("?, ");
        }
        valueClause.append("?)");
        return valueClause.toString();
    }

    private String getInsertStatement() throws SQLException {
        statementBuilder = new StringBuilder();
        statementBuilder.append(action.getAction());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(GZDBReserved.INTO.getKeyword());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(table.getTable());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(GZDBReserved.VALUES.getKeyword());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(makeSelectValueClause());
        return statementBuilder.toString();
    }

    public void executeInsert(HashMap<String, Object> insertMap) throws SQLException, GZDBResultException {
        HashMap<String, String> typesMap = getTableTypes();
        if (insertMap.size() < typesMap.size()) {
            throw new GZDBResultException(GZDBExceptionMessages.INPUT_SMALLER, insertMap);
        } else if (insertMap.size() > typesMap.size()) {
            throw new GZDBResultException(GZDBExceptionMessages.INPUT_BIGGER, insertMap);
        }
        String statement = getInsertStatement();
        callableStatement = connection.prepareCall(statement);
        callableStatement = setCallableStatementArguments(callableStatement, insertMap, typesMap);
        callableStatement.executeUpdate();
    }

    private String makeWhereClause(HashMap<String, Object> selectMap) throws SQLException, GZDBResultException {
        int index = 0;
        StringBuilder whereClause = new StringBuilder();
        HashSet<String> fieldsSet = getTableFields();
        for (String inputField : selectMap.keySet()) {
            if (!fieldsSet.contains(inputField)) {
                throw new GZDBResultException(GZDBExceptionMessages.SELECT_NOFIELD, selectMap);
            }
            whereClause.append(inputField);
            whereClause.append(SPACE_CHAR);
            whereClause.append(EQUAL_CHAR);
            whereClause.append(SPACE_CHAR);
            whereClause.append(QUESTION_CHAR);
            index++;
            if (index != selectMap.size()) {
                whereClause.append(SPACE_CHAR);
                whereClause.append(AND_OPERATOR);
                whereClause.append(SPACE_CHAR);
            }
        }
        return whereClause.toString();
    }

    private String getSelectStatement(HashMap<String, Object> selectMap) throws SQLException, GZDBResultException {
        statementBuilder = new StringBuilder();
        statementBuilder.append(action.getAction());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(EVERYTHING_CHAR);
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(GZDBReserved.FROM.getKeyword());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(table.getTable());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(GZDBReserved.WHERE.getKeyword());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(makeWhereClause(selectMap));
        return statementBuilder.toString();
    }

    public HashMap<String, Object> executeSelect(HashMap<String, Object> selectMap) throws SQLException, GZDBResultException {
        HashMap<String, String> typesMap = getTableTypes();
        String statement = getSelectStatement(selectMap);
        callableStatement = connection.prepareCall(statement);
        callableStatement = setCallableStatementArguments(callableStatement, selectMap, typesMap);
        ResultSet resultSet = callableStatement.executeQuery();
        HashMap<String, Object> resultMap = getResultMapWithWhereClause(resultSet, typesMap);
        return resultMap;
    }

    private String makeUpdateClause(HashMap<String, Object> updateMap) throws SQLException, GZDBResultException {
        int index = 0;
        StringBuilder updateClause = new StringBuilder();
        HashSet<String> fieldsSet = getTableFields();
        for (String inputField : updateMap.keySet()) {
            if (!fieldsSet.contains(inputField)) {
                throw new GZDBResultException(GZDBExceptionMessages.UPDATE_NOFIELD, updateMap);
            }
            updateClause.append(inputField);
            updateClause.append(SPACE_CHAR);
            updateClause.append(EQUAL_CHAR);
            updateClause.append(SPACE_CHAR);
            updateClause.append(QUESTION_CHAR);
            index++;
            if (index != updateMap.size()) {
                updateClause.append(COMMA_CHAR);
                updateClause.append(SPACE_CHAR);
            }
        }
        return updateClause.toString();
    }

    private String getUpdateStatement(HashMap<String, Object> updateMap, HashMap<String, Object> selectMap) throws SQLException, GZDBResultException {
        statementBuilder = new StringBuilder();
        statementBuilder.append(action.getAction());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(table.getTable());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(GZDBReserved.SET.getKeyword());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(makeUpdateClause(selectMap));
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(GZDBReserved.WHERE.getKeyword());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(makeWhereClause(updateMap));
        return statementBuilder.toString();
    }

    public void executeUpdate(HashMap<String, Object> updateMap, HashMap<String, Object> selectMap) throws SQLException, GZDBResultException {
        HashMap<String, String> typesMap = getTableTypes();
        String statement = getUpdateStatement(updateMap, selectMap);
        callableStatement = connection.prepareCall(statement);
        callableStatement = setCallableStatementArguments(callableStatement, updateMap, selectMap, typesMap);
        callableStatement.executeUpdate();
    }

    private String getDeleteStatement(HashMap<String, Object> deleteMap) throws SQLException, GZDBResultException {
        statementBuilder = new StringBuilder();
        statementBuilder.append(action.getAction());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(GZDBReserved.FROM.getKeyword());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(table.getTable());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(GZDBReserved.WHERE.getKeyword());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(makeWhereClause(deleteMap));
        return statementBuilder.toString();
    }

    public void executeDelete(HashMap<String, Object> deleteMap) throws SQLException, GZDBResultException {
        HashMap<String, String> typesMap = getTableTypes();
        String statement = getDeleteStatement(deleteMap);
        callableStatement = connection.prepareCall(statement);
        callableStatement = setCallableStatementArguments(callableStatement, deleteMap, typesMap);
        callableStatement.executeUpdate();
    }
}
