package com.glamdring.greenZenith.externals.database.constants;

/**
 * The GZDBTables enumeration holds the different SQL Table names used within
 * the Green Zenith proyect, this garuantees a correct and precise usage of the
 * SQL syntax.
 * <p>
 * @author Glamdring (Σxz)
 * @version 1.0.1
 * @since 0.1
 */
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

    /**
     * Holds the value of each item's assigned table.
     */
    private final String table;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param reservedKeyword
     */
    GZDBTables(String table) {
        this.table = table;
    }

    /**
     * @return The table string held by the item.
     */
    public String getTable() {
        return table;
    }

}
