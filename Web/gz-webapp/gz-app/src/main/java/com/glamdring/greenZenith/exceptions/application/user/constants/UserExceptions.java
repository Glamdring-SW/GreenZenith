package com.glamdring.greenZenith.exceptions.application.user.constants;

public enum UserExceptions {

    GZDBCONNECTION("GZDBConnectorFailedToConstruct"),
    ID("NonExistentID"),
    EMAIL("IncorrectEmail"),
    PASSWORD("IncorrectPassword");

    private final String exceptionMessage;

    UserExceptions(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

}
