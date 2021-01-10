package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.NotUniqEntityException;
import com.epam.jwd.core_final.service.MissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MissionServiceImpl.class);
    private static MissionServiceImpl instance;
    protected List<FlightMission> missionList = new ArrayList<>();

    private MissionServiceImpl() {

    }

    public static MissionServiceImpl getInstance() {
        if (instance == null) {
            instance = new MissionServiceImpl();
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return this.missionList;
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
    public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
        missionList.set(Integer.parseInt(flightMission.getId().toString()) - 1, flightMission);
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        missionList.add(flightMission);
        SpaceshipServiceImpl spaceshipCrud = SpaceshipServiceImpl.getInstance();
        SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria.Builder().setIsReadyForNextMissions(true).build();
        spaceshipCrud.findAllSpaceshipsByCriteria(spaceshipCriteria)
                .forEach(e -> {
                    try {
                        spaceshipCrud.assignSpaceshipOnMission(e);
                    } catch (RuntimeException err) {
                        LOGGER.error(err.getMessage());
                    }
                });


        CrewServiceImpl crewService = CrewServiceImpl.getInstance();
        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria.Builder()
                .setDoesHeIsSurvive(true).build();
        crewService.findAllCrewMembersByCriteria(crewMemberCriteria)
                .forEach(e -> {
                    try {
                        crewService.assignCrewMemberOnMission(e);
                    } catch (RuntimeException err) {
                        LOGGER.error(err.getMessage());
                    }
                });


        return flightMission;
    }
}