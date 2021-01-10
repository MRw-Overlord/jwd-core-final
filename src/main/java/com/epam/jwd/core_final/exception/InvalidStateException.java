package com.epam.jwd.core_final.exception;

public class InvalidStateException extends Exception {
    // todo

    public InvalidStateException(String message){
        super(message);
    }

    public InvalidStateException(String message, Throwable e){
        super(message, e);
    }

    public InvalidStateException() {
        super();
    }
}
