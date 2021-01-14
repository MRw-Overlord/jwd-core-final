package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.BaikonurContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.NonUniqueEntityNameException;
import com.epam.jwd.core_final.service.MissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MissionServiceImpl implements MissionService {

    private static MissionServiceImpl instance;

    private final ApplicationContext applicationContext;

    private MissionServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static MissionServiceImpl createInstance() {
        if (instance == null) {
            instance = new MissionServiceImpl(BaikonurContext.createInstance());
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return new ArrayList<>(applicationContext.retrieveBaseEntityList(FlightMission.class));
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        FlightMissionCriteria flightMissionCriteria = (FlightMissionCriteria) criteria;
        List<FlightMission> allFlightMissions = findAllMissions();
        Stream<FlightMission> filteredStream = filterByCriteria(allFlightMissions, flightMissionCriteria);
        return filteredStream.collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        FlightMissionCriteria flightMissionCriteria = (FlightMissionCriteria) criteria;
        List<FlightMission> allFlightMissions = findAllMissions();
        Stream<FlightMission> filteredStream = filterByCriteria(allFlightMissions, flightMissionCriteria);
        return filteredStream.findFirst();
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        List<FlightMission> allFlightMissions = findAllMissions();
        allFlightMissions.forEach(mission -> {
            if (mission.getName().equalsIgnoreCase(flightMission.getName())) {
                throw new NonUniqueEntityNameException("Flight mission with the same name is already in the context");
            }
        });
        return saveFlightMission(flightMission);
    }

    private FlightMission saveFlightMission(FlightMission flightMission) {
        applicationContext.retrieveBaseEntityList(FlightMission.class).add(flightMission);
        return flightMission;
    }

    private Stream<FlightMission> filterByCriteria(List<FlightMission> allFlightMissions,
                                                   FlightMissionCriteria flightMissionCriteria) {
        Stream<FlightMission> stream = allFlightMissions.stream();
        if (flightMissionCriteria.getId() != null) {
            stream = stream.filter(flightMission -> flightMission.getId().equals(flightMissionCriteria.getId()));
        }
        if (flightMissionCriteria.getName() != null) {
            stream = stream.filter(flightMission -> flightMission.getName()
                    .equalsIgnoreCase(flightMissionCriteria.getName()));
        }
        if (flightMissionCriteria.getBeforeStartDate() != null) {
            stream = stream.filter(flightMission -> flightMission.getStartDate()
                    .isBefore(flightMissionCriteria.getBeforeStartDate()));
        }
        if (flightMissionCriteria.getAfterStartDate() != null) {
            stream = stream.filter(flightMission -> flightMission.getStartDate()
                    .isAfter(flightMissionCriteria.getAfterStartDate()));
        }
        if (flightMissionCriteria.getBeforeEndDate() != null) {
            stream = stream.filter(flightMission -> flightMission.getEndDate()
                    .isBefore(flightMissionCriteria.getBeforeEndDate()));
        }
        if (flightMissionCriteria.getAfterEndDate() != null) {
            stream = stream.filter(flightMission -> flightMission.getEndDate()
                    .isAfter(flightMissionCriteria.getAfterEndDate()));
        }
        if (flightMissionCriteria.getSmallerDistance() != null) {
            stream = stream.filter(flightMission -> flightMission.getDistance()
                    < flightMissionCriteria.getSmallerDistance());
        }
        if (flightMissionCriteria.getBiggerDistance() != null) {
            stream = stream.filter(flightMission -> flightMission.getDistance()
                    > flightMissionCriteria.getBiggerDistance());
        }
        stream = (flightMissionCriteria.getAssignedSpaceship() == null)
                ? stream.filter(flightMission -> flightMission.getAssignedSpaceship() == null)
                : stream.filter(flightMission -> flightMission.getAssignedSpaceship()
                .equals(flightMissionCriteria.getAssignedSpaceship()));
        stream = (flightMissionCriteria.getAssignedCrews() == null)
                ? stream.filter(flightMission -> flightMission.getAssignedCrew() == null)
                : stream.filter(flightMission -> flightMission.getAssignedCrew()
                .equals(flightMissionCriteria.getAssignedCrews()));
        if (flightMissionCriteria.getMissionResult() != null) {
            stream = stream.filter(flightMission -> flightMission.getMissionResult()
                    == flightMissionCriteria.getMissionResult());
        }
        return stream;
    }
}
