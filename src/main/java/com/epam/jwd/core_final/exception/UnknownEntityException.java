package com.epam.jwd.core_final.exception;

import com.epam.jwd.core_final.domain.BaseEntity;

public class UnknownEntityException extends RuntimeException {

    private final String entityName;
    private final BaseEntity[] args;

    public UnknownEntityException(String entityName) {
        super();
        this.entityName = entityName;
        this.args = null;
    }

    public UnknownEntityException(String entityName, BaseEntity[] args) {
        super();
        this.entityName = entityName;
        this.args = args;
    }

    @Override
    public String getMessage() {
        // todo
        // you should use entityName, args (if necessary)
        if(args == null) {
            return this.entityName + ": " + super.getMessage();
        } String message = null;
        for(BaseEntity someEntity : args){
            message = message + someEntity.getName() + super.getMessage() + "\n";
        }
        return message;
    }
}
