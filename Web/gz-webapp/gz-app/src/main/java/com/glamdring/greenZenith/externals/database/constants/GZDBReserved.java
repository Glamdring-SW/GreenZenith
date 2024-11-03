package com.glamdring.greenZenith.externals.database.constants;

/**
 * Contains different exclusive SQL keywords used for setting up SQL statements,
 * like actions and queries, this garuantees a correct and precise usage of the
 * SQL syntax, for the most prominent usage @see GZDBExecutor
 *
 * @author Glamdring (Σxz)
 * @version 1.5.0
 * @since 0.1
 */
public enum GZDBReserved {
    /**
     * Indicates the table to execute statements in.
     */
    INTO("INTO"),
    /**
     * Indicates the table to retrieve data from.
     */
    FROM("FROM"),
    /**
     * Indicates what values to be looked for when doing insertions.
     */
    VALUES("VALUES"),
    /**
     * Indicates the conditions taht need to be fullfilled in order to execute a
     * statement.
     */
    WHERE("WHERE"),
    /**
     * Indicates the fields that will be updayed.
     */
    SET("SET"),
    /**
     * The 'Field' column that describes the name of each field.
     */
    FIELD("Field"),
    /**
     * The 'Type' column that describes the data type of each field.
     */
    TYPE("Type"),
    /**
     * SQL identifier for a boolean value.
     */
    BOOLEAN("tinyint"),
    /**
     * SQL identifier for an integer value.
     */
    INT("int"),
    /**
     * SQL identifier for a decimal value.
     */
    DECIMAL("decimal"),
    /**
     * SQL identifier for a string value.
     */
    VARCHAR("varchar"),
    /**
     * SQL identifier for a long string value.
     */
    TEXT("text"),
    /**
     * SQL identifier for a date value.
     */
    DATE("date"),
    /**
     * SQL identifier for a time value.
     */
    TIME("time"),
    /**
     * SQL identifier for a timestamp value.
     */
    DATETIME("datetime"),
    /**
     * SQL identifier for a BLOB value.
     */
    MEDIUMBLOB("mediumblob");

    /**
     * Holds the value of each item's assigned reserved keyword.
     */
    private final String reservedKeyword;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param reservedKeyword the SQL reserved keyword identifier.
     */
    GZDBReserved(String reservedKeyword) {
        this.reservedKeyword = reservedKeyword;
    }

    /**
     * Gets the predefined exception message designed to give significant but
     * concise information.
     *
     * @return The reserved keyword string value held by the item.
     */
    public String getKeyword() {
        return reservedKeyword;
    }

}
