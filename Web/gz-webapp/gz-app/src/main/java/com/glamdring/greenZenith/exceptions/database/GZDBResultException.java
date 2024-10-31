package com.glamdring.greenZenith.exceptions.database;

import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.database.constants.GZDBExceptionMessages;

/**
 * Custom exception that handles faulty input maps, faulty SQL executions and
 * and the inclusion of custom and specific exception messages for a better
 * control.
 *
 * @see GZDBExceptionMessages
 * @author Glamdring (Σxz)
 * @version 1.1.0
 * @since 0.1
 */
public class GZDBResultException extends Exception {

    /**
     * The input map that caused an exception.
     */
    private LinkedHashMap<String, Object> faultyResultMap = null;

    /**
     * Establishes the exception message to use our {@code GZDBResultException}
     * in.
     *
     * @param exceptionType The item of our enum class that will grant the
     * message on the constructor.
     */
    public GZDBResultException(GZDBExceptionMessages exceptionType) {
        super(exceptionType.getExceptionMessage());
    }

    /**
     * Establishes the exception message to use our {@code GZDBResultException}
     * in and the parent's message.
     *
     * @param exceptionType The item of our enum class that will grant the
     * message on the constructor.
     * @param exceptionSuper The parent's exception message for better
     * clarification.
     */
    public GZDBResultException(GZDBExceptionMessages exceptionType, Exception exceptionSuper) {
        super(exceptionType.getExceptionMessage() + "\n" + exceptionSuper.getMessage());
    }

    /**
     * Establishes the exception message to use our {@code GZDBResultException}
     * in and includes the map which contains incorrect data.
     *
     * @param exceptionType The item of our enum class that will grant the
     * message on the constructor.
     * @param faultyResultMap The map containing data not supported by the
     * program, causing an exception.
     */
    public GZDBResultException(GZDBExceptionMessages exceptionType, LinkedHashMap<String, Object> faultyResultMap) {
        super(exceptionType.getExceptionMessage());
        this.faultyResultMap = faultyResultMap;
    }

    /**
     *
     * @param exceptionType The item of our enum class that will grant the
     * message on the constructor.
     * @param faultyResultMap The map containing data not supported by the
     * program, causing an exception.
     * @param exceptionSuper The parent's exception message for better
     * clarification.
     */
    public GZDBResultException(GZDBExceptionMessages exceptionType, LinkedHashMap<String, Object> faultyResultMap, Exception exceptionSuper) {
        super(exceptionType.getExceptionMessage() + "\n" + exceptionSuper.getMessage());
        this.faultyResultMap = faultyResultMap;
    }

    /**
     * Obtains a map containing the different faulty and incorrect data which
     * caused an error to ocurr.
     *
     * @return The input map that caused the exception to happen
     */
    public LinkedHashMap<String, Object> getFaultyResultMap() {
        return faultyResultMap;
    }
}
