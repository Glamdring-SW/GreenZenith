package com.glamdring.greenZenith.externals;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GZDBConnectivity {

    final private String driverName = "com.mysql.cj.jdbc.Driver";
    final private String urlDB = "jdbc:mysql://localhost:3306/GreenZenith";
    final private String usernameDB = "Administrator";
    final private String passwordDB = "1234";

    private SQLProcedures sqlProcedures;

    private Connection connection;
    private Statement statement;
    private CallableStatement callableStatement;
    private ResultSet resultSet;

    public GZDBConnectivity() {
        try {
            connection = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GZDBConnectivity(String urlDB, String usernameDB, String passwordDB) {
        try {
            connection = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(String username, String email, int age, String location, String password, boolean administratorAccess) {
        try {
            callableStatement = connection.prepareCall(sqlProcedures.insertUser.giveCall());
            callableStatement.setString(2, username);
            callableStatement.setString(3, email);
            callableStatement.setInt(4, age);
            callableStatement.setString(5, location);
            callableStatement.setString(6, password);
            callableStatement.setBoolean(7, administratorAccess);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertUserPicture(int userID, Blob picture) {
        try {
            callableStatement = connection.prepareCall(sqlProcedures.insertUserPicture.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.setBlob(2, picture);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public void insertPlant(String plantName, java.sql.Date plantingDate, String plantDescription, int quantity, int userID) {
        try {
            callableStatement = connection.prepareCall(sqlProcedures.insertPlant.giveCall());
            callableStatement.setString(2, plantName);
            callableStatement.setDate(3, plantingDate);
            callableStatement.setString(4, plantDescription);
            callableStatement.setInt(5, quantity);
            callableStatement.setInt(6, userID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertPlantPicture(int plantID, Blob picture) {
        try {
            callableStatement = connection.prepareCall(sqlProcedures.insertPlantPicture.giveCall());
            callableStatement.setInt(1, plantID);
            callableStatement.setBlob(2, picture);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertIntoPlantSchedule(int plantID, java.sql.Time waterTime) {
        try {
            callableStatement = connection.prepareCall(sqlProcedures.insertIntoPlantSchedule.giveCall());
            callableStatement.setInt(1, plantID);
            callableStatement.setTime(2, waterTime);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProduct(String title, String productDescription, java.math.BigDecimal price, int quantity, int productID) {
        try {
            callableStatement = connection.prepareCall(sqlProcedures.insertProduct.giveCall());
            callableStatement.setString(2, title);
            callableStatement.setString(3, productDescription);
            callableStatement.setBigDecimal(4, price);
            callableStatement.setInt(5, quantity);
            callableStatement.setInt(6, productID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertProductPicture(int productID, Blob picture) {
        try {
            callableStatement = connection.prepareCall(sqlProcedures.insertProductPicture.giveCall());
            callableStatement.setInt(1, productID);
            callableStatement.setBlob(2, picture);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
