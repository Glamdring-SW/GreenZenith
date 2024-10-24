package com.glamdring.greenZenith.externals.database.constants;

public enum GZDBActions {
    INSERT("INSERT"),
    SELECT("SELECT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private final String action;

    GZDBActions(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
