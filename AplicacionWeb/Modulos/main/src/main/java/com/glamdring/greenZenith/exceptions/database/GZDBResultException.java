package com.glamdring.greenZenith.exceptions.database;

import java.util.HashMap;

import com.glamdring.greenZenith.exceptions.database.constants.GZDBExceptionMessages;

public class GZDBResultException extends Exception {

    private HashMap<String, Object> faultyResultMap = null;

    public GZDBResultException(GZDBExceptionMessages exceptionType) {
        super(exceptionType.getExceptionMessage());
    }

    public GZDBResultException(GZDBExceptionMessages exceptionType, Exception exceptionSuper) {
        super(exceptionType.getExceptionMessage() + "\n" + exceptionSuper.getMessage());
    }

    public GZDBResultException(GZDBExceptionMessages exceptionType, HashMap<String, Object> faultyResultMap) {
        super(exceptionType.getExceptionMessage());
        this.faultyResultMap = faultyResultMap;
    }

    public GZDBResultException(GZDBExceptionMessages exceptionType, HashMap<String, Object> faultyResultMap, Exception exceptionSuper) {
        super(exceptionType.getExceptionMessage() + "\n" + exceptionSuper.getMessage());
        this.faultyResultMap = faultyResultMap;
    }

    public HashMap<String, Object> getFaultyResultMap() {
        return faultyResultMap;
    }
}
