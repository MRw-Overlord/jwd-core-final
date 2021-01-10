package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * id {@link Long} - entity id
 * name {@link String} - entity name
 */
public abstract class AbstractBaseEntity implements BaseEntity {

    private long id;
    private String name;

    public AbstractBaseEntity(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public Long getId() {
        // todo
        return this.id;
    }

    @Override
    public String getName() {
        // todo
        return this.name;
    }
}
