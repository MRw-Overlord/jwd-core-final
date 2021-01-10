package com.epam.jwd.core_final.exception;

import com.epam.jwd.core_final.domain.BaseEntity;

public class EntityIsNotToBeAssignedException extends UnknownEntityException{


    public EntityIsNotToBeAssignedException(String entityName, BaseEntity[] baseEntity){
        super(entityName, baseEntity);
    }

    public EntityIsNotToBeAssignedException(String name){
        super(name);
    }



    @Override
    public String getMessage() {
        return super.getMessage() + ". Is not able to be assigned!!!!";
    }
}
