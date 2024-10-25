package com.glamdring.greenZenith.exceptions.application.user;

import com.glamdring.greenZenith.exceptions.application.user.constants.UserExceptions;

public class InvalidUserException extends Exception{

    public InvalidUserException(UserExceptions exceptionType){
        super(exceptionType.getExceptionMessage());
    }

}
