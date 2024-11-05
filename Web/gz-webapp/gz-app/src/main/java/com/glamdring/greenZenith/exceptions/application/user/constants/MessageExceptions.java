package com.glamdring.greenZenith.exceptions.application.user.constants;

/**
 * Contains predefined exception messages to indicate a special type of
 * exception when caused by an interaction of Users.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.2
 */
public enum MessageExceptions {

    LENGTH("InvalidMessageLength");

    /**
     * Holds the value of each item's predefined exception message.
     */
    private final String exceptionMessage;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param exceptionMessage The string value of the keyword.
     */
    MessageExceptions(String exceptionMessage) {
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
