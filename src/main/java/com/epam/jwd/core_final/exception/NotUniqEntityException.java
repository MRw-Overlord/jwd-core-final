package com.epam.jwd.core_final.exception;

import com.epam.jwd.core_final.domain.BaseEntity;

public class NotUniqEntityException extends RuntimeException {
    private String message;

    public NotUniqEntityException() {
        super();
        this.message = "Entity is not unique";
    }

    @Override
    public String getMessage() {
        return message;
    }
}