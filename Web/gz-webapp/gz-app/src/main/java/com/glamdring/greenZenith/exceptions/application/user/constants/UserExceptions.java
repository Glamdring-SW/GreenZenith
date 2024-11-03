package com.glamdring.greenZenith.exceptions.application.user.constants;

/**
 * Contains predefined exception messages to indicate a special type of
 * exception when caused by an interaction of the User.
 *
 * @author Glamdring (Σxz)
 * @version 1.1.0
 * @since 0.1
 */
public enum UserExceptions {

    /**
     * The connection to the database utilizng the
     * {@link com.glamdring.greenZenith.externals.database.GZDBConnector} failed
     * to construct.
     */
    GZDBCONNECTION("GZDBConnectorFailedToConstruct"),
    /**
     * The ID utilized for establishing a User couldn't be found within the
     * database.
     */
    ID("NonExistentID"),
    /**
     * The email utilized for establishing a User couldn't be found within the
     * database.
     */
    EMAIL("IncorrectEmail"),
    /**
     * The password utilized for establishing a User couldn't be found within
     * the database.
     */
    PASSWORD("IncorrectPassword"),
    /**
     * The age entered by the User is not within the valid range.
     */
    AGE("InvalidAge"),
    /**
     * The picture entered by the User cannot be saved.
     */
    PICTURE("InvalidPicture"),
    /**
     * The location entered by the User cannot be resolved.
     */
    LOCATION("InvalidLocation"),
    /**
     * The format of the User's username does not follow the established format,
     *
     * @see com.glamdring.greenZenith.handlers.formats.GZFormatter
     */
    FORMAT_USERNAME("InvalidUsernameFormat"),
    /**
     * The format of the User's email does not follow the established format,
     *
     * @see com.glamdring.greenZenith.handlers.formats.GZFormatter
     */
    FORMAT_EMAIL("InvalidEmailFormat"),
    /**
     * The format of the User's password does not follow the established format,
     *
     * @see com.glamdring.greenZenith.handlers.formats.GZFormatter
     */
    FORMAT_PASSWORD("InvalidPasswordFormat"),;

    /**
     * Holds the value of each item's predefined exception message.
     */
    private final String exceptionMessage;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param exceptionMessage The string value of the keyword.
     */
    UserExceptions(String exceptionMessage) {
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
