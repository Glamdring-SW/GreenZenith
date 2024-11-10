package com.glamdring.greenZenith.userInteractions.plants.constants;

/**
 * Contains predefined confirmation messages to indicate if a certain action
 * regarding a plant was executed successfully.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.2
 */
public enum PlantConfirmations {

 /**
     * If the update of a user's name was successful.
     */
    NAME_UPDATE(""),
    /**
     * If the update of a user's email was successful.
     */
    DESCRIPTION_UPDATE(""),
    /**
     * If the update of a user's age was successful.
     */
    QUANTITY_UPDATE(""),
    /**
     * If the update of a user's picture was successful.
     */
    PLANTINGDATE_UPDATE(""),
    /**
     * If the update of a user's picture was not successful but we resorted to
     * use the default profile picture..
     */
    PICTURE_DEFAULT(""),
    /**
     * If the update of a user's name password was successful.
     */
    PICTURE_UPDATE(""),
     /**
     * If the update of a user's name password was successful.
     */
    SCHEDULE_UPDATE(""),
    /**
     * If the update of a user's administrator access was successful.
     */
    NOCHANGES(""),;

    /**
     * Holds the value of each item's predefined exception message.
     */
    private final String confirmationMessage;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param confirmationMessage The string value of the keyword.
     */
    PlantConfirmations(String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
    }

    /**
     * Gets the predefined confirmation message designed to give significant but
     * concise information.
     *
     * @return The predefined confirmation message string value held by the
     * item.
     */
    public String getConfirmationMessage() {
        return confirmationMessage;
    }
}
