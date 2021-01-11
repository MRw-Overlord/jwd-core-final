package com.epam.jwd.core_final.factory.impl;


import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.HashMap;
import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {
    private static SpaceshipFactory instance ;
    private Long id;

    private SpaceshipFactory(){

    }

    public static SpaceshipFactory getInstance(){
        if(instance == null){
            instance = new SpaceshipFactory();
        }
        return instance;
    }

    @Override
    public Spaceship create(Object... args) {
        Map<Role, Short> spaceshipCrew = new HashMap<>();
        String crew = (String) args[2];
        crew = crew.substring(1, crew.length() - 1);
        String[] rawData = crew.split(",");
        for (String data : rawData) {
            Role role = Role.resolveRoleById((int) Long.parseLong(String.valueOf(data.charAt(0))));
            Short number = Short.parseShort(String.valueOf(data.charAt(2)));
            spaceshipCrew.put(role, number);
        }
        return new Spaceship(id++, (String)args[0], spaceshipCrew, (Long)args[1]);
    }

}