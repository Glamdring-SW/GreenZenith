package com.glamdring.greenZenith.externals.database.constants;

/**
 * Contains the different SQL Table names used within the Green Zenith proyect,
 * this garuantees a correct and precise usage of the SQL syntax.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.1
 * @since 0.1
 */
public enum GZDBTables {

    /**
     * The established name of the table that contains the information of each
     * user.
     */
    USER("PUser"),
    /**
     * An additional table used for loading images, made for increasing
     * performance.
     */
    USERPICTURE("UserPicture"),
    /**
     * The established name of the table that contains the information of each
     * plant that a user owns.
     */
    PLANT("Plant"),
    /**
     * An additional table used for loading images, made for increasing
     * performance.
     */
    PLANTPICTURE("PlantPicture"),
    /**
     * The established name of the table that contains the information of the
     * schedule of each plant
     */
    PLANTSCHEDULE("PlantSchedule"),
    /**
     * The established name of the table that contains the information of each
     * product availiable on sale.
     */
    PRODUCT("Product"),
    /**
     * An additional table used for loading images, made for increasing
     * performance.
     */
    PRODUCTPICTURE("ProductPicture"),
    /**
     * The established name of the table that contains the information of each
     * message sent and recieved by a user.
     */
    MESSAGE("Message"),
    /**
     * The established name of the table that contains the information of the
     * products wanted by a user.
     */
    CART("Cart");

    /**
     * Holds the value of each item's assigned table.
     */
    private final String table;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param table The database table name.
     */
    GZDBTables(String table) {
        this.table = table;
    }

    /**
     * Gets the predefined exception message designed to give significant but
     * concise information.
     *
     * @return The table string value held by the item.
     */
    public String getTable() {
        return table;
    }

}
