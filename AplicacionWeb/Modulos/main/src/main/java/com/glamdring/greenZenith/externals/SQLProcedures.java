package com.glamdring.greenZenith.externals;

public enum SQLProcedures {
    insertUser("CALL insertUser(?,?,?,?,?,?)"),
    insertUserPicture("CALL insertUserPicture(?,?)"),
    insertPlant("CALL insertPlant(?,?,?,?,?)"),
    insertPlantPicture("CALL insertPlantPicture(?,?)"),
    insertIntoPlantSchedule("CALL insertIntoPlantSchedule(?,?)"),
    insertProduct("CALL insertProduct(?,?,?,?,?)"),
    insertProductPicture("CALL insertProductPicture(?,?)"),
    insertMessage("CALL insertMessage(?,?,?,?)"),
    insertIntoCart("CALL insertIntoCart(?,?,?)"),
    deleteUser("CALL deleteUser(?)"),
    deleteUserPicture("CALL deleteUserPicture(?)"),
    deletePlant("CALL deletePlant(?)"),
    deletePlantPicture("CALL deletePlantPicture(?)"),
    deletePlantSchedule("CALL deletePlantSchedule(?)"),
    deleteProduct("CALL deleteProduct(?)"),
    deleteProductPicture("CALL deleteProductPicture(?)"),
    deleteMessage("CALL deleteMessage(?)"),
    deleteCart("CALL deleteCart(?)"),
    deleteCartProduct("CALL deleteCartProduct(?,?)"),
    updateUserUsername("CALL updateUserUsername(?,?)"),
    updateUserEmail("CALL updateUserEmail(?,?)"),
    updateUserAge("CALL updateUserAge(?,?)"),
    updateUserLocation("CALL updateUserLocation(?,?)"),
    updateUserPassword("CALL updateUserPassword(?,?)"),
    setUserAdministratorAccess("CALL setUserAdministratorAccess(?,?)"),
    updateUserPicture("CALL updateUserPicture(?,?)"),
    updatePlantName("CALL updatePlantName(?,?)"),
    updatePlantingDate("CALL updatePlantingDate(?,?)"),
    updatePlantDescription("CALL updatePlantDescription(?,?)"),
    updatePlantQuantity("CALL updatePlantQuantity(?,?)"),
    updatePlantPicture("CALL updatePlantPicture(?,?)"),
    updateProductTitle("CALL updateProductTitle(?,?)"),
    updateProductDescription("CALL updateProductDescription(?,?)"),
    updateProductPrice("CALL updateProductPrice(?,?)"),
    updateProductQuantity("CALL updateProductQuantity(?,?)"),
    updateProductPicture("CALL updateProductPicture(?,?)"),
    selectUser("SELECT * FROM PUser WHERE ID = ?"),
    selectUserImage("SELECT * FROM UserPicture WHERE ID = ?"),
    selectPlant("SELECT * FROM Plant WHERE ID = ?"),
    selectPlantImage("SELECT * FROM PlantPicture WHERE ID = ?"),
    selectPlantSchedule("SELECT * FROM PlantSchedule WHERE ID = ?"),
    selectProduct("SELECT * FROM Product WHERE ID = ?"),
    selectProductImage("SELECT * FROM ProductPicture WHERE ID = ?"),
    selectMessage("SELECT * FROM Message WHERE ID = ?"),
    selectCartProducts("SELECT * FROM Cart WHERE ID = ?");

    protected final String callableStatement;

    SQLProcedures(String callableStatment) {
        this.callableStatement = callableStatment;
    }

    protected String giveCall() {
        return callableStatement;
    }

}
