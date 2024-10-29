package com.glamdring.greenZenith.externals.database.constants;

/**
 * The GZDBActions enumeration holds different exclusive SQL keywords used for
 * setting up SQL statements, however it is exclusively used for SQL Actions and
 * Queries, this garuantees a correct and precise usage of the SQL syntax.
 * <p>
 * @author Glamdring (Σxz)
 * @version 1.5.0
 * @since 0.1
 */
public enum GZDBActions {
    INSERT("INSERT"),
    SELECT("SELECT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    /**
     * Holds the value of each item's assigned action.
     */
    private final String action;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param reservedKeyword
     */
    GZDBActions(String action) {
        this.action = action;
    }

    /**
     * @return The SQL Action string held by the item.
     */
    public String getAction() {
        return action;
    }
}
