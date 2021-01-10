package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {
    private Long id;
    private String name;

    protected Criteria(Criteria.Builder builder){
        this.id = builder.id;
        this.name = builder.name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    static class Builder<T extends Builder<T>> {
        private Long id;
        private String name;

        public T setId(Long id){
            this.id = id;
            return (T) this;
        }

        public Builder<T> setName(String name){
            this.name = name;
            return (T) this;
        }

    }
}
