package com.glamdring.greenZenith.userInteractions;

import java.awt.image.BufferedImage;

/**
 * Containts useful getters for preestablished attributes of the most important
 * objects within the proyect.
 *
 * @author Glamdring (Σxz)
 * @version 1.2.0
 * @since 0.1
 */
public interface Attributable {

    /**
     * Each object posseses an ID or unique number identificator on the database
     * to refer easily to it when doing SQL Queries and Actions.
     *
     * @return The unique number identificator of an registry in the database.
     */
    int getId();

    /**
     * Each object posseses a custom descriptive name given by a User.
     *
     * @return An already defined name given by a User.
     */
    String getName();

    /**
     * Each object posseses an image or picture that represents the existance of
     * an object in real life.
     *
     * @return A representative picture given by a User.
     */
    BufferedImage getPicture();
}
