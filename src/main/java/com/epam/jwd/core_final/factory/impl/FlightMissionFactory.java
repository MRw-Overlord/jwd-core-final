package com.epam.jwd.core_final.factory.impl;


import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;

public class FlightMissionFactory implements EntityFactory<FlightMission> {
    private static FlightMissionFactory instance;
    private Long id = 1L;

    private FlightMissionFactory() {

    }

    public static FlightMissionFactory getInstance() {
        if (instance == null) {
            instance = new FlightMissionFactory();
        }
        return instance;
    }

    @Override
    public FlightMission create(Object... args) {
        return new FlightMission(id++, (String) args[0],
                (LocalDate) args[1],
                (LocalDate) args[2],
                (Long) args[3],
                (MissionResult) args[4]);
    }
}
