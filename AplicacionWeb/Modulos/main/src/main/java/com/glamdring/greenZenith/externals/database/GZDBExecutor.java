package com.glamdring.greenZenith.externals.database;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
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

    private final String AND_OPERATOR = "AND";

    private StringBuilder statementBuilder;

    private final Connection connection;
    private final GZDBActions action;
    private final GZDBTables table;
    private PreparedStatement preparedStatement;

    public GZDBExecutor(PreparedStatement preparedStatement, Connection connection, GZDBActions action, GZDBTables table) {
        this.preparedStatement = preparedStatement;
        this.connection = connection;
        this.action = action;
        this.table = table;
    }

    private ResultSet describeTable() throws SQLException {
        preparedStatement = connection.prepareStatement("DESCRIBE " + table.getTable());
        ResultSet resultDescriptionSet = preparedStatement.executeQuery();
        return resultDescriptionSet;
    }

    public LinkedHashSet<String> getTableFields() throws SQLException {
        LinkedHashSet<String> fieldsSet = new LinkedHashSet<>();
        ResultSet resultSet = describeTable();
        while (resultSet.next()) {
            fieldsSet.add(resultSet.getString(GZDBReserved.FIELD.getKeyword()));
        }
        return fieldsSet;
    }

    public LinkedHashMap<String, String> getTableTypes() throws SQLException {
        LinkedHashMap<String, String> typesMap = new LinkedHashMap<>();
        ResultSet resultSet = describeTable();
        while (resultSet.next()) {
            typesMap.put(
                    resultSet.getString(GZDBReserved.FIELD.getKeyword()),
                    resultSet.getString(GZDBReserved.TYPE.getKeyword()));
        }
        return typesMap;
    }

    private LinkedHashMap<String, Object> getResultMapWithWhereClause(ResultSet resultSet, LinkedHashMap<String, String> typesMap) throws SQLException {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
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

    private PreparedStatement setPreparedStatementArguments(PreparedStatement preparedStatement, LinkedHashMap<String, Object> inputMap, LinkedHashMap<String, String> typesMap) throws SQLException {
        int index = 1;
        for (String inputField : inputMap.keySet()) {
            if (typesMap.get(inputField).equals(GZDBReserved.BOOLEAN.getKeyword())) {
                preparedStatement.setBoolean(index, (Boolean) inputMap.get(inputField));
            } else if (typesMap.get(inputField).equals(GZDBReserved.INT.getKeyword())) {
                preparedStatement.setInt(index, (Integer) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.DECIMAL.getKeyword())) {
                preparedStatement.setBigDecimal(index, (BigDecimal) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.VARCHAR.getKeyword())) {
                preparedStatement.setString(index, (String) inputMap.get(inputField));
            } else if (typesMap.get(inputField).equals(GZDBReserved.TEXT.getKeyword())) {
                preparedStatement.setString(index, (String) inputMap.get(inputField));
            } else if (typesMap.get(inputField).equals(GZDBReserved.DATE.getKeyword())) {
                preparedStatement.setDate(index, (Date) inputMap.get(inputField));
            } else if (typesMap.get(inputField).equals(GZDBReserved.TIME.getKeyword())) {
                preparedStatement.setTime(index, (Time) inputMap.get(inputField));
            } else if (typesMap.get(inputField).equals(GZDBReserved.DATETIME.getKeyword())) {
                preparedStatement.setTimestamp(index, (Timestamp) inputMap.get(inputField));
            } else if (typesMap.get(inputField).equals(GZDBReserved.MEDIUMBLOB.getKeyword())) {
                preparedStatement.setBlob(index, (Blob) inputMap.get(inputField));
            }
            index++;
        }
        return preparedStatement;
    }

    private PreparedStatement setPreparedStatementArguments(PreparedStatement preparedStatement, LinkedHashMap<String, Object> inputMap1, LinkedHashMap<String, Object> inputMap2, LinkedHashMap<String, String> typesMap) throws SQLException, GZDBResultException {
        int index = 1;
        Set<String> keys1 = inputMap1.keySet();
        Set<String> keys2 = inputMap2.keySet();
        keys1.retainAll(keys2);
        if (keys1.isEmpty()) {
            throw new GZDBResultException(GZDBExceptionMessages.KEYINPUT_2, inputMap2);
        }
        LinkedHashMap<String, Object> inputMap = new LinkedHashMap<>();
        inputMap.putAll(inputMap1);
        inputMap.putAll(inputMap2);
        for (String inputField : inputMap.keySet()) {
            if (typesMap.get(inputField).contains(GZDBReserved.BOOLEAN.getKeyword())) {
                preparedStatement.setBoolean(index, (Boolean) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.INT.getKeyword())) {
                preparedStatement.setInt(index, (Integer) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.DECIMAL.getKeyword())) {
                preparedStatement.setBigDecimal(index, (BigDecimal) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.VARCHAR.getKeyword())) {
                preparedStatement.setString(index, (String) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.TEXT.getKeyword())) {
                preparedStatement.setString(index, (String) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.DATE.getKeyword())) {
                preparedStatement.setDate(index, (Date) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.TIME.getKeyword())) {
                preparedStatement.setTime(index, (Time) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.DATETIME.getKeyword())) {
                preparedStatement.setTimestamp(index, (Timestamp) inputMap.get(inputField));
            } else if (typesMap.get(inputField).contains(GZDBReserved.MEDIUMBLOB.getKeyword())) {
                preparedStatement.setBlob(index, (Blob) inputMap.get(inputField));
            }
        }
        return preparedStatement;
    }

    private String makeSelectValueClause(LinkedHashMap<String, Object> insertMap, LinkedHashMap<String, String> typesMap) throws SQLException {
        StringBuilder valueClause = new StringBuilder();
        int index = 0;
        int total = typesMap.size();
        valueClause.append("(");
        for (Map.Entry<String, String> typeMap : typesMap.entrySet()) {
            index++;
            String type = typeMap.getKey();
            if (!insertMap.containsKey(type)) {
                if (index != total) {
                    valueClause.append("DEFAULT, ");
                } else {
                    valueClause.append("DEFAULT)");
                }
            } else {
                if (index != total) {
                    valueClause.append("?, ");
                } else {
                    valueClause.append("?)");
                }
            }
        }
        return valueClause.toString();
    }

    private String getInsertStatement(LinkedHashMap<String, Object> insertMap, LinkedHashMap<String, String> typesMap) throws SQLException {
        statementBuilder = new StringBuilder();
        statementBuilder.append(action.getAction());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(GZDBReserved.INTO.getKeyword());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(table.getTable());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(GZDBReserved.VALUES.getKeyword());
        statementBuilder.append(SPACE_CHAR);
        statementBuilder.append(makeSelectValueClause(insertMap, typesMap));
        return statementBuilder.toString();
    }

    public void executeInsert(LinkedHashMap<String, Object> insertMap) throws SQLException, GZDBResultException {
        LinkedHashMap<String, String> typesMap = getTableTypes();
        String statement = getInsertStatement(insertMap, typesMap);
        preparedStatement = connection.prepareStatement(statement);
        preparedStatement = setPreparedStatementArguments(preparedStatement, insertMap, typesMap);
        preparedStatement.executeUpdate();
    }

    private String makeWhereClause(LinkedHashMap<String, Object> selectMap) throws SQLException, GZDBResultException {
        int index = 0;
        StringBuilder whereClause = new StringBuilder();
        LinkedHashSet<String> fieldsSet = getTableFields();
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

    private String getSelectStatement(LinkedHashMap<String, Object> selectMap) throws SQLException, GZDBResultException {
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

    public LinkedHashMap<String, Object> executeSelect(LinkedHashMap<String, Object> selectMap) throws SQLException, GZDBResultException {
        LinkedHashMap<String, String> typesMap = getTableTypes();
        String statement = getSelectStatement(selectMap);
        preparedStatement = connection.prepareStatement(statement);
        preparedStatement = setPreparedStatementArguments(preparedStatement, selectMap, typesMap);
        ResultSet resultSet = preparedStatement.executeQuery();
        LinkedHashMap<String, Object> resultMap = getResultMapWithWhereClause(resultSet, typesMap);
        return resultMap;
    }

    private String makeUpdateClause(LinkedHashMap<String, Object> updateMap) throws SQLException, GZDBResultException {
        int index = 0;
        StringBuilder updateClause = new StringBuilder();
        LinkedHashSet<String> fieldsSet = getTableFields();
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

    private String getUpdateStatement(LinkedHashMap<String, Object> updateMap, LinkedHashMap<String, Object> selectMap) throws SQLException, GZDBResultException {
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

    public void executeUpdate(LinkedHashMap<String, Object> updateMap, LinkedHashMap<String, Object> selectMap) throws SQLException, GZDBResultException {
        LinkedHashMap<String, String> typesMap = getTableTypes();
        String statement = getUpdateStatement(updateMap, selectMap);
        preparedStatement = connection.prepareStatement(statement);
        preparedStatement = setPreparedStatementArguments(preparedStatement, updateMap, selectMap, typesMap);
        preparedStatement.executeUpdate();
    }

    private String getDeleteStatement(LinkedHashMap<String, Object> deleteMap) throws SQLException, GZDBResultException {
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

    public void executeDelete(LinkedHashMap<String, Object> deleteMap) throws SQLException, GZDBResultException {
        LinkedHashMap<String, String> typesMap = getTableTypes();
        String statement = getDeleteStatement(deleteMap);
        preparedStatement = connection.prepareStatement(statement);
        preparedStatement = setPreparedStatementArguments(preparedStatement, deleteMap, typesMap);
        preparedStatement.executeUpdate();
    }
}
