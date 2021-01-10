package com.epam.jwd.core_final.exception;

import com.epam.jwd.core_final.domain.BaseEntity;

public class EntityIsNotUniqException extends UnknownEntityException {

    public EntityIsNotUniqException(String name){
        super(name);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
