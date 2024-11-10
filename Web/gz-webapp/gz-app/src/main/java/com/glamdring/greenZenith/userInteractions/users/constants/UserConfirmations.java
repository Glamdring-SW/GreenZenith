package com.glamdring.greenZenith.userInteractions.users.constants;

/**
 * Contains predefined confirmation messages to indicate if a certain regarding a user action was
 * executed successfully.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.2
 */
public enum UserConfirmations {

    /**
     * If the register of a user was successful.
     */
    REGISTER(""),
    /**
     * If the log in of a user was successful.
     */
    LOGIN(""),
    /**
     * If the update of a user's name was successful.
     */
    USERNAME_UPDATE(""),
    /**
     * If the update of a user's email was successful.
     */
    EMAIL_UPDATE(""),
    /**
     * If the update of a user's age was successful.
     */
    AGE_UPDATE(""),
    /**
     * If the update of a user's picture was successful.
     */
    PICTURE_UPDATE(""),
    /**
     * If the update of a user's picture was not successful but we resorted to
     * use the default profile picture..
     */
    PICTURE_DEFAULT(""),
    /**
     * If the update of a user's name password was successful.
     */
    PASSWORD_UPDATE(""),
    /**
     * If the update of a user's location was successful.
     */
    LOCATION_UPDATE(""),
    /**
     * If the update of a user's administrator access was successful.
     */
    ADMINISTRATORACCESS_UPDATE(""),
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
    UserConfirmations(String confirmationMessage
    ) {
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
