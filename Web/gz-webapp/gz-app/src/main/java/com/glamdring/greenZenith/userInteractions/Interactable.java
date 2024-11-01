package com.glamdring.greenZenith.userInteractions;

/**
 * Containts useful getters for preestablished attributes of objects that can be
 * interacted with to establish certain properties.
 *
 * @author Glamdring (Σxz)
 * @version 1.1.0
 * @since 0.1
 */
public interface Interactable {

    /**
     * Each large set of User owned items contains a general description of the
     * set.
     *
     * @return The description of a group of items.
     */
    String getDescription();

    /**
     * Each large set of User owned items contains a defined amount that
     * represents how many of a single item there is.
     *
     * @return The quantity of a single item that a group has.
     */
    int getQuantity();
}
