package com.epam.jwd.core_final.exception;

import com.epam.jwd.core_final.domain.BaseEntity;

public class EntityIsNotToBeCreatedException extends UnknownEntityException{

    public EntityIsNotToBeCreatedException(String entityName, BaseEntity[] baseEntity){
        super(entityName, baseEntity);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ". Is not able to be created!!!!";
    }
}
