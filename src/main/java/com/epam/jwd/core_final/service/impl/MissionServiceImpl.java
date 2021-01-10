package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.EntityIsNotUniqException;
import com.epam.jwd.core_final.service.MissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {
    private static MissionServiceImpl instance;
    private final ApplicationContext applicationContext;

    private MissionServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static MissionServiceImpl getInstance() {
        if (instance == null) {
            instance = new MissionServiceImpl(NassaContext.getInstance());
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return new ArrayList<>(applicationContext.retrieveBaseEntityList(FlightMission.class));
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        List<FlightMission> flightMissions = findAllMissions();
        FlightMissionCriteria missionCriteria = (FlightMissionCriteria) criteria;
        return flightMissions.stream().filter(flightMission -> (
                (flightMission.getName().equals(missionCriteria.getName()) || missionCriteria.getName() == null) &&
                        (missionCriteria.getDistance().equals(flightMission.getMissionsDistance()) ||
                                missionCriteria.getDistance() == null) &&
                        (missionCriteria.getStartDate().equals(flightMission.getStartDate()) ||
                                missionCriteria.getStartDate() == null)))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        List<FlightMission> missions = findAllMissions();
        FlightMissionCriteria missionCriteria = (FlightMissionCriteria) criteria;
        return missions.stream().filter(mission -> (
                (missionCriteria.getName() == null || mission.getName().equals(missionCriteria.getName())) &&
                        (missionCriteria.getDistance() == null ||
                                missionCriteria.getDistance().equals(mission.getMissionsDistance())) &&
                        (missionCriteria.getStartDate() == null ||
                                missionCriteria.getStartDate().equals(mission.getStartDate()))
                )).findFirst();
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission oldFlightMission, FlightMission newFlightMission) {
        oldFlightMission.setMissionsDistance(newFlightMission.getMissionsDistance());
        oldFlightMission.setStartDate(newFlightMission.getStartDate());
        oldFlightMission.setEndDate(newFlightMission.getEndDate());
        return oldFlightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        List<FlightMission> allFlightMissions = findAllMissions();
        allFlightMissions.forEach(mission -> {
            if (mission.getName().equalsIgnoreCase(flightMission.getName())) {
                throw new EntityIsNotUniqException("This flight mission is already start and don't uniq!!!!");
            }
        });
        return saveFlightMission(flightMission);
    }

    private FlightMission saveFlightMission(FlightMission flightMission) {
        applicationContext.retrieveBaseEntityList(FlightMission.class).add(flightMission);
        return flightMission;
    }
}