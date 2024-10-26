package com.glamdring.greenZenith.externals.database.constants;

public enum GZDBTables {

    USER("PUser"),
    USERPICTURE("UserPicture"),
    PLANT("Plant"),
    PLANTPICTURE("PlantPicture"),
    PLANTSCHEDULE("PlantSchedule"),
    PRODUCT("Product"),
    PRODUCTPICTURE("ProductPicture"),
    MESSAGE("Message"),
    CART("Cart");

    private final String table;

    GZDBTables(String table) {
        this.table = table;
    }

    public  String getTable() {
        return table;
    }

}
