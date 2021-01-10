package com.epam.jwd.core_final.exception;

public class EntityIsNotToBeAssignedException extends RuntimeException {
    private String message;

    public EntityIsNotToBeAssignedException() {
        this.message = "Entity is not able to be assigned";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
