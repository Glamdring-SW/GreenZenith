package com.glamdring.greenZenith.exceptions.application.product;

import com.glamdring.greenZenith.exceptions.application.product.constants.ProductExceptions;

/**
 * Custom exception class to indicate a failure that happened with an
 * interaction made by a User with it's owned products and cart.
 *
 * @see ProductExceptions
 * @author Glamdring (Σxz)
 * @version 1.0.1
 * @since 0.2
 */
public class InvalidProductException extends Exception {

    /**
     * The exception item indicating the type of exception with a short message
     * attached to it.
     *
     * @param exceptionType An enum item that contains a specific message.
     */
    public InvalidProductException(ProductExceptions exceptionType) {
        super(exceptionType.getExceptionMessage());
    }

    /**
     * The exception item indicating the type of exception with a short message
     * attached to it and the parent exception that caused it.
     *
     * @param exceptionType An enum item that contains a specific message.
     * @param superException The parent exception.
     */
    public InvalidProductException(ProductExceptions exceptionType, Exception superException) {
        super(exceptionType.getExceptionMessage() + "\n" + superException.getMessage());
    }

}
