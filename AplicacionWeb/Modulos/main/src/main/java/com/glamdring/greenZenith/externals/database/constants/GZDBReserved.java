package com.glamdring.greenZenith.externals.database.constants;

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

    private final String reservedKeyword;

    GZDBReserved(String reservedKeyword
    ) {
        this.reservedKeyword = reservedKeyword;
    }

    public String getKeyword() {
        return reservedKeyword;
    }

}
