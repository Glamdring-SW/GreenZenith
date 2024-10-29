package com.glamdring.greenZenith.externals.database.constants;

import com.glamdring.greenZenith.externals.database.GZDBExecutor;

/**
 * The GZDBReserved enumeration holds different exclusive SQL keywords used for
 * setting up SQL statements, like actions and queries, this garuantees a
 * correct and precise usage of the SQL syntax, for the most prominent usage
 *
 * @see GZDBExecutor
 * <p>
 * @author Glamdring (Σxz)
 * @version 1.5.0
 * @since 0.1
 */
public enum GZDBReserved {
    INTO("INTO"),
    FROM("FROM"),
    VALUES("VALUES"),
    WHERE("WHERE"),
    SET("SET"),
    FIELD("Field"),
    TYPE("Type"),
    BOOLEAN("tinyint"),
    INT("int"),
    DECIMAL("decimal"),
    VARCHAR("varchar"),
    TEXT("text"),
    DATE("date"),
    TIME("time"),
    DATETIME("datetime"),
    MEDIUMBLOB("mediumblob");

    /**
     * Holds the value of each item's assigned reserved keyword.
     */
    private final String reservedKeyword;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param reservedKeyword
     */
    GZDBReserved(String reservedKeyword) {
        this.reservedKeyword = reservedKeyword;
    }

    /**
     * @return The reserved keyword string held by the item.
     */
    public String getKeyword() {
        return reservedKeyword;
    }

}
