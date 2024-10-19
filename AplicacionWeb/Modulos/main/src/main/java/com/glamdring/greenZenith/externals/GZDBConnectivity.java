package com.glamdring.greenZenith.externals;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GZDBConnectivity {

    // final private String driverName = "com.mysql.cj.jdbc.Driver";
    final private String urlDB = "jdbc:mysql://localhost:3306/GreenZenith";
    final private String usernameDB = "Administrator";
    final private String passwordDB = "1234";

    private Connection connection;
    private CallableStatement callableStatement;
    private ResultSet resultSet;

    public GZDBConnectivity() {
        try {
            connection = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
        } catch (SQLException e) {

        }
    }

    public GZDBConnectivity(String urlDB, String usernameDB, String passwordDB) {
        try {
            connection = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
        } catch (SQLException e) {

        }
    }

    public void insertUser(String username, String email, int age, String location, String password, boolean administratorAccess) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.insertUser.giveCall());
            callableStatement.setString(1, username);
            callableStatement.setString(2, email);
            callableStatement.setInt(3, age);
            callableStatement.setString(4, location);
            callableStatement.setString(5, password);
            callableStatement.setBoolean(6, administratorAccess);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void insertUserPicture(int userID, Blob picture) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.insertUserPicture.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.setBlob(2, picture);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void insertPlant(String plantName, java.sql.Date plantingDate, String plantDescription, int quantity, int userID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.insertPlant.giveCall());
            callableStatement.setString(1, plantName);
            callableStatement.setDate(2, plantingDate);
            callableStatement.setString(3, plantDescription);
            callableStatement.setInt(4, quantity);
            callableStatement.setInt(5, userID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void insertPlantPicture(int plantID, Blob picture) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.insertPlantPicture.giveCall());
            callableStatement.setInt(1, plantID);
            callableStatement.setBlob(2, picture);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void insertIntoPlantSchedule(int plantID, java.sql.Time waterTime) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.insertIntoPlantSchedule.giveCall());
            callableStatement.setInt(1, plantID);
            callableStatement.setTime(2, waterTime);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void insertProduct(String title, String productDescription, java.math.BigDecimal price, int quantity, int productID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.insertProduct.giveCall());
            callableStatement.setString(1, title);
            callableStatement.setString(2, productDescription);
            callableStatement.setBigDecimal(3, price);
            callableStatement.setInt(4, quantity);
            callableStatement.setInt(5, productID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void insertProductPicture(int productID, Blob picture) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.insertProductPicture.giveCall());
            callableStatement.setInt(1, productID);
            callableStatement.setBlob(2, picture);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void insertMessage(String content, java.sql.Timestamp datetime, int senderID, int recieverID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.insertMessage.giveCall());
            callableStatement.setString(1, content);
            callableStatement.setTimestamp(2, datetime);
            callableStatement.setInt(3, senderID);
            callableStatement.setInt(4, recieverID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void insertIntoCart(int userID, int productID, int quantity) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.insertIntoCart.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.setInt(2, productID);
            callableStatement.setInt(3, quantity);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deleteUser(int userID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.deleteUser.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deleteUserPicture(int userID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.deleteUserPicture.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deletePlant(int plantID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.deletePlant.giveCall());
            callableStatement.setInt(1, plantID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deletePlantPicture(int plantID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.deletePlantPicture.giveCall());
            callableStatement.setInt(1, plantID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deletePlantSchedule(int plantID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.deletePlantSchedule.giveCall());
            callableStatement.setInt(1, plantID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deleteProduct(int productID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.deleteProduct.giveCall());
            callableStatement.setInt(1, productID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deleteProductPicture(int productID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.deleteProductPicture.giveCall());
            callableStatement.setInt(1, productID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deleteMessage(int messageID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.deleteMessage.giveCall());
            callableStatement.setInt(1, messageID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deleteCart(int cartID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.deleteCart.giveCall());
            callableStatement.setInt(1, cartID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deleteCartProduct(int userID, int productID) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.deleteCartProduct.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.setInt(2, productID);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateUserUsername(int userID, String newUsername) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updateUserUsername.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.setString(2, newUsername);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateUserEmail(int userID, String newEmail) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updateUserEmail.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.setString(2, newEmail);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateUserAge(int userID, int newAge) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updateUserAge.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.setInt(2, newAge);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateUserLocation(int userID, String newLocation) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updateUserLocation.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.setString(2, newLocation);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateUserPassword(int userID, String newPassword) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updateUserPassword.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.setString(2, newPassword);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void setUserAdministratorAccess(int userID, boolean access) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.setUserAdministratorAccess.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.setBoolean(2, access);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateUserPicture(int userID, Blob newPicture) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updateUserPicture.giveCall());
            callableStatement.setInt(1, userID);
            callableStatement.setBlob(2, newPicture);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updatePlantName(int plantID, String newName) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updatePlantName.giveCall());
            callableStatement.setInt(1, plantID);
            callableStatement.setString(2, newName);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updatePlantingDate(int plantID, java.sql.Date newDate) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updatePlantingDate.giveCall());
            callableStatement.setInt(1, plantID);
            callableStatement.setDate(2, newDate);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updatePlantDescription(int plantID, String newDescription) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updatePlantDescription.giveCall());
            callableStatement.setInt(1, plantID);
            callableStatement.setString(2, newDescription);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updatePlantQuantity(int plantID, int newQuantity) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updatePlantQuantity.giveCall());
            callableStatement.setInt(1, plantID);
            callableStatement.setInt(2, newQuantity);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updatePlantPicture(int plantID, Blob newPicture) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updatePlantPicture.giveCall());
            callableStatement.setInt(1, plantID);
            callableStatement.setBlob(2, newPicture);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateProductTitle(int productID, String newTitle) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updateProductTitle.giveCall());
            callableStatement.setInt(1, productID);
            callableStatement.setString(2, newTitle);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateProductDescription(int productID, String newDescription) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updateProductDescription.giveCall());
            callableStatement.setInt(1, productID);
            callableStatement.setString(2, newDescription);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateProductPrice(int productID, java.math.BigDecimal newPrice) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updateProductPrice.giveCall());
            callableStatement.setInt(1, productID);
            callableStatement.setBigDecimal(2, newPrice);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateProductQuantity(int productID, int newQuantity) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updateProductQuantity.giveCall());
            callableStatement.setInt(1, productID);
            callableStatement.setInt(2, newQuantity);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateProductPicture(int productID, Blob newPicture) {
        try {
            callableStatement = connection.prepareCall(SQLProcedures.updateProductPicture.giveCall());
            callableStatement.setInt(1, productID);
            callableStatement.setBlob(2, newPicture);
            callableStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public HashMap<String, Object> getUser(int userID) {
        HashMap<String, Object> resultMap = new HashMap<>();
        try {
            callableStatement = connection.prepareCall(SQLProcedures.selectUser.giveCall());
            callableStatement.setInt(1, userID);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                resultMap.put("ID", (Integer) resultSet.getInt("ID"));
                resultMap.put("Username", resultSet.getString("PUsername"));
                resultMap.put("Email", resultSet.getString("Email"));
                resultMap.put("Age", (Integer) resultSet.getInt("Age"));
                resultMap.put("Location", resultSet.getString("Location"));
                resultMap.put("Password", resultSet.getString("PasswordUser"));
                resultMap.put("AdministratorAccess", (Boolean) resultSet.getBoolean("AdministratorAccess"));
            }
            callableStatement = connection.prepareCall(SQLProcedures.selectUserImage.giveCall());
            callableStatement.setInt(1, userID);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                resultMap.put("Picture", resultSet.getBlob("Picture"));
            }
        } catch (SQLException e) {

        }
        return resultMap;
    }

    public HashMap<String, Object> getPlant(int plantID) {
        int index = 01;
        HashMap<String, Object> resultMap = new HashMap<>();
        try {
            callableStatement = connection.prepareCall(SQLProcedures.selectPlant.giveCall());
            callableStatement.setInt(1, plantID);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                resultMap.put("ID", (Integer) resultSet.getInt("ID"));
                resultMap.put("PlantName", resultSet.getString("PlantName"));
                resultMap.put("PlantingDate", resultSet.getDate("PlantingDate"));
                resultMap.put("PlantDescription", resultSet.getString("PlantDescription"));
                resultMap.put("Quantity", (Integer) resultSet.getInt("Quantity"));
                resultMap.put("UserID", (Integer) resultSet.getInt("UserID"));
            }
            callableStatement = connection.prepareCall(SQLProcedures.selectPlantImage.giveCall());
            callableStatement.setInt(1, plantID);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                resultMap.put("Picture", resultSet.getBlob("Picture"));
            }
            callableStatement = connection.prepareCall(SQLProcedures.selectPlantSchedule.giveCall());
            callableStatement.setInt(1, plantID);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                resultMap.put("WaterTime" + index, resultSet.getTime("WaterTime"));
                index++;
            }
        } catch (SQLException e) {

        }
        return resultMap;
    }

    public HashMap<String, Object> getProduct(int productID) {
        HashMap<String, Object> resultMap = new HashMap<>();
        try {
            callableStatement = connection.prepareCall(SQLProcedures.selectProduct.giveCall());
            callableStatement.setInt(1, productID);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                resultMap.put("ID", (Integer) resultSet.getInt("ID"));
                resultMap.put("Title", resultSet.getString("Title"));
                resultMap.put("ProductDescription", resultSet.getString("ProductDescription"));
                resultMap.put("Price", resultSet.getBigDecimal("Price"));
                resultMap.put("Quantity", (Integer) resultSet.getInt("Quantity"));
                resultMap.put("PlantID", (Integer) resultSet.getInt("PlantID"));
            }
            callableStatement = connection.prepareCall(SQLProcedures.selectProductImage.giveCall());
            callableStatement.setInt(1, productID);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                resultMap.put("Picture", resultSet.getBlob("Picture"));
            }
        } catch (SQLException e) {

        }
        return resultMap;
    }

    public HashMap<String, Object> getMessage(int messageID) {
        HashMap<String, Object> resultMap = new HashMap<>();
        try {
            callableStatement = connection.prepareCall(SQLProcedures.selectMessage.giveCall());
            callableStatement.setInt(1, messageID);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                resultMap.put("ID", (Integer) resultSet.getInt("ID"));
                resultMap.put("Content", resultSet.getString("Content"));
                resultMap.put("SentDateTime", resultSet.getTimestamp("SentDateTime"));
                resultMap.put("SenderID", (Integer) resultSet.getInt("SenderID"));
                resultMap.put("RecieverID", (Integer) resultSet.getInt("RecieverID"));
            }
        } catch (SQLException e) {

        }
        return resultMap;
    }

    public HashMap<String, Object> getCartProducts(int userID) {
        int index = 1;
        HashMap<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("UserID", (Integer) userID);
            callableStatement = connection.prepareCall(SQLProcedures.selectCartProducts.giveCall());
            callableStatement.setInt(1, userID);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                resultMap.put("ProductID" + index, (Integer) resultSet.getInt("ProductID"));
                resultMap.put("Quantity" + index, (Integer) resultSet.getInt("Quantity"));
                index++;
            }
        } catch (SQLException e) {

        }
        return resultMap;
    }
}
