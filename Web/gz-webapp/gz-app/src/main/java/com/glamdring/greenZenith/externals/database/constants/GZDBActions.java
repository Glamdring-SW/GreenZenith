package com.glamdring.greenZenith.externals.database.constants;

/**
 * Contains different exclusive SQL keywords used for setting up SQL statements,
 * however it is exclusively used for SQL Actions and Queries, this garuantees a
 * correct and precise usage of the SQL syntax.
 *
 * @author Glamdring (Σxz)
 * @version 1.1.0
 * @since 0.1
 */
public enum GZDBActions {
    /**
     * SQL Statement for insertions
     */
    INSERT("INSERT"),
    /**
     * SQL Statement for selections
     */
    SELECT("SELECT"),
    /**
     * SQL Statement for updates
     */
    UPDATE("UPDATE"),
    /**
     * SQL Statement for deletions
     */
    DELETE("DELETE");

    /**
     * Holds the value of each item's assigned action.
     */
    private final String action;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param action The SQL Action keyword.
     */
    GZDBActions(String action) {
        this.action = action;
    }

    /**
     * Gets the predefined exception message designed to give significant but
     * concise information.
     *
     * @return The SQL Action string value held by the item.
     */
    public String getAction() {
        return action;
    }
}
