package com.glamdring.greenZenith.exceptions.application.plant;

import com.glamdring.greenZenith.exceptions.application.plant.constants.PlantExceptions;

/**
 * Custom exception class to indicate a failure that happened with an
 * interaction made by a User and it's plants.
 *
 * @see PlantExceptions
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.2
 */
public class InvalidPlantException extends Exception {

    /**
     * The exception item indicating the type of exception with a short message
     * attached to it.
     *
     * @param exceptionType An enum item that contains a specific message.
     */
    public InvalidPlantException(PlantExceptions exceptionType) {
        super(exceptionType.getExceptionMessage());
    }

}
