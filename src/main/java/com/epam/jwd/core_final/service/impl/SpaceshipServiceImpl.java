package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityIsNotToBeAssignedException;
import com.epam.jwd.core_final.exception.NotUniqEntityException;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {
    private static SpaceshipServiceImpl instance;
    protected List<Spaceship> spaceshipsList;

    private SpaceshipServiceImpl() {
        ApplicationContext applicationContext = NassaContext.getInstance();
        this.spaceshipsList = (List<Spaceship>) applicationContext.retrieveBaseEntityList(Spaceship.class);
    }

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return this.spaceshipsList;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        List<Spaceship> spaceships = findAllSpaceships();
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;
        return spaceships.stream().filter(spaceship -> (
                (spaceshipCriteria.getName() == null || spaceship.getName().equals(spaceshipCriteria.getName())) &&
                        (spaceshipCriteria.getFlightDistance() == null ||
                                spaceship.getTotalAvailableFlightDistance() >= spaceshipCriteria.getFlightDistance()) &&
                        (spaceshipCriteria.getReadyForNextMissions() == null || spaceship.isReadyForNextMissions()
                                == spaceshipCriteria.getReadyForNextMissions())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        List<Spaceship> spaceships = findAllSpaceships();
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;
        return spaceships.stream().filter(spaceship -> (
                (spaceshipCriteria.getName() == null || spaceship.getName().equals(spaceshipCriteria.getName())) &&
                        (spaceshipCriteria.getFlightDistance() == null ||
                                spaceship.getTotalAvailableFlightDistance() >= spaceshipCriteria.getFlightDistance()) &&
                        (spaceshipCriteria.getReadyForNextMissions() == null ||
                                spaceship.isReadyForNextMissions() == spaceshipCriteria.getReadyForNextMissions())
        )).findFirst();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        spaceshipsList.set(Integer.parseInt(spaceship.getId().toString()) - 1, spaceship);
        return spaceship;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws EntityIsNotToBeAssignedException {
        if (!spaceship.isReadyForNextMissions()) throw new EntityIsNotToBeAssignedException();
        MissionServiceImpl missionService = MissionServiceImpl.getInstance();
        FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria.Builder()
                .setDistant(spaceship.getTotalAvailableFlightDistance())
                .build();
        flightMissionCriteria.setAssignedSpaceShift(false);
        Optional<FlightMission> optionalMission = missionService.findMissionByCriteria(flightMissionCriteria);
        if (optionalMission.isPresent()) {
            FlightMission suitableMission = optionalMission.get();
            suitableMission.setAssignedSpaceship(spaceship);
            missionService.updateSpaceshipDetails(suitableMission);
            spaceship.setReadyForNextMissions(false);
            this.updateSpaceshipDetails(spaceship);
        } else {
            throw new EntityIsNotToBeAssignedException();
        }
    }

    @Override
    public Spaceship createSpaceship(String name, Long distance, Map<Role, Short> crew) throws RuntimeException, NotUniqEntityException {
        Spaceship spaceship = SpaceshipFactory.getInstance().create(name, distance, crew);
        Optional<Spaceship> spaceshipOptional = findSpaceshipByCriteria(new SpaceshipCriteria.Builder() {{
            setName(name);
        }}.build());
        Collection<Spaceship> spaceships = findAllSpaceships();
        spaceships.add(spaceship);
        return spaceship;
    }
}

