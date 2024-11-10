package com.glamdring.greenZenith.exceptions.application.plant.constants;

/**
 * Contains predefined exception messages to indicate a special type of
 * exception when caused by an interaction of the User with it's plants.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.1
 * @since 0.2
 */
public enum PlantExceptions {

    /**
     * The length of the User's plant name does not follow the established
     * restrictions.
     */
    LENGTH_NAME("InvalidPlantNameLength"),
    /**
     * The length of the User's plant description does not follow the
     * established restrictions.
     */
    LENGTH_DESCRIPTION("InvalidPlantDescriptionLength"),
    /**
     * The quantity of the User's plant is not a valid number.
     */
    QUANTITY("InvalidPlantQuantity"),
     /**
     * The quantity of the User's plant is not a valid number.
     */
    QUANTITY_DECREASEBY1("InvalidPlantQuantity"),
    /**
     * The image of the User's plant can not be utilized.
     */
    IMAGE("InvalidPlantPicture"),
    /**
     * The date of the User's plant can not be utilized.
     */
    DATE("InvalidPlantPicture"),
    /**
     * The time list of the User's plant can not be utilized.
     */
    SCHEDULE("InvalidPlantPicture"),
    /**
     * The owner of this plant cannot be resolved.
     */
    OWNER("InvalidPlantOwnership"),
    /**
     * The connection to the database cannot be resolved.
     */
    DATABASE("InvalidPlantDatabaseConnection"),
    /**
     * The owner of this plant cannot be resolved.
     */
    INEXISTANT("La planta especificada no existe."),;

    /**
     * Holds the value of each item's predefined exception message.
     */
    private final String exceptionMessage;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param exceptionMessage The string value of the keyword.
     */
    PlantExceptions(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Gets the predefined exception message designed to give significant but
     * concise information.
     *
     * @return The predefined exception message string value held by the item.
     */
    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
