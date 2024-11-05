package com.glamdring.greenZenith.exceptions.application.user.messages;

import com.glamdring.greenZenith.exceptions.application.user.constants.MessageExceptions;

/**
 * Custom exception class to indicate a failure that happened with an
 * interaction made by different users using messages.
 *
 * @see UserExceptions
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.2
 */
public class InvalidMessageException extends Exception {

    /**
     * The exception item indicating the type of exception with a short message
     * attached to it.
     *
     * @param exceptionType An enum item that contains a specific message.
     */
    public InvalidMessageException(MessageExceptions exceptionType) {
        super(exceptionType.getExceptionMessage());
    }

    /**
     * The exception item indicating the type of exception with a short message
     * attached to it and the parent exception that caused it.
     *
     * @param exceptionType An enum item that contains a specific message.
     * @param superException The parent exception.
     */
    public InvalidMessageException(MessageExceptions exceptionType, Exception superException) {
        super(exceptionType.getExceptionMessage() + "\n" + superException.getMessage());
    }
}
