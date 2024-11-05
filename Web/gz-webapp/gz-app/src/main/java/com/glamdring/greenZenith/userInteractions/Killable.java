package com.glamdring.greenZenith.userInteractions;

/**
 * Containts methods for stopping certain objects and deleting them from the
 * database.
 *
 * @author Glamdring (Σxz)
 * @version 1.2.0
 * @since 0.1
 */
public interface Killable {
    /**
     * Deletes an object from the database.
     */
    void delete();
}
