package com.glamdring.greenZenith.exceptions.application.user.constants;

public enum UserExceptions {

    GZDBCONNECTION("GZDBConnectorFailedToConstruct"),
    ID("NonExistentID"),
    EMAIL("IncorrectEmail"),
    PASSWORD("IncorrectPassword"),
    AGE("InvalidAge"),
    PICTURE("InvalidPicture"),
    LOCATION("InvalidLocation"),
    FORMAT_USERNAME("InvalidUsernameFormat"),
    FORMAT_EMAIL("InvalidEmailFormat"),
    FORMAT_PASSWORD("InvalidPasswordFormat"),;

    private final String exceptionMessage;

    UserExceptions(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

}
