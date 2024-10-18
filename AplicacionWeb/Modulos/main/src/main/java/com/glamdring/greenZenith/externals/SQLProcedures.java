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
    insertIntoCart("CALL insertIntoCart(?,?)"),
    deleteUser("CALL deleteUser(?)"),
    deleteUserPicture("CALL deleteUserPicture(?)"),
    deletePlant("CALL deletePlant(?)"),
    deletePlantPicture("CALL deletePlantPicture(?)"),
    deletePlantSchedule("CALL deletePlantSchedule(?)"),
    deleteProduct("CALL deleteProduct(?)"),
    deleteProductPicture("CALL deleteProductPicture(?)"),
    deleteMessage("CALL deleteMessage(?)"),
    deleteCart("CALL deleteCart(?)"),
    deleteCartProduct("CALL deleteCartProduct(?,?)");

    protected final String callableStatement;

    SQLProcedures(String callableStatment) {
        this.callableStatement = callableStatment;
    }

    protected String giveCall() {
        return callableStatement;
    }

}
