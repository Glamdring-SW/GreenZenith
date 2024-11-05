package com.glamdring.greenZenith.exceptions.application.user;

import com.glamdring.greenZenith.exceptions.application.user.constants.UserExceptions;

/**
 * Custom exception class to indicate a failure that happened with an
 * interaction made by a User.
 *
 * @see UserExceptions
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.1
 */
public class InvalidUserException extends Exception {

    /**
     * The exception item indicating the type of exception with a short message
     * attached to it.
     *
     * @param exceptionType An enum item that contains a specific message.
     */
    public InvalidUserException(UserExceptions exceptionType) {
        super(exceptionType.getExceptionMessage());
    }

    /**
     * The exception item indicating the type of exception with a short message
     * attached to it and the parent exception that caused it.
     *
     * @param exceptionType An enum item that contains a specific message.
     * @param superException The parent exception.
     */
    public InvalidUserException(UserExceptions exceptionType, Exception superException) {
        super(exceptionType.getExceptionMessage() + "\n" + superException.getMessage());
    }

}
