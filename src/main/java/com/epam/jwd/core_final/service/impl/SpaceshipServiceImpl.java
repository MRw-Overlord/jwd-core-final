package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityIsNotToBeAssignedException;
import com.epam.jwd.core_final.exception.EntityIsNotUniqException;
import com.epam.jwd.core_final.service.SpaceshipService;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {
    private static SpaceshipServiceImpl instance;
    private final ApplicationContext applicationContext;

    private SpaceshipServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipServiceImpl(NassaContext.getInstance());
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return (List<Spaceship>) NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);
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
    public Spaceship updateSpaceshipDetails(Spaceship olsSpaceship, Spaceship newSpaceship) {
        olsSpaceship.setTotalAvailableFlightDistance(newSpaceship.getTotalAvailableFlightDistance());
        olsSpaceship.setReadyForNextMissions(newSpaceship.isReadyForNextMissions());
        return olsSpaceship;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws EntityIsNotToBeAssignedException {
        Collection<FlightMission> allFlightMissions = applicationContext.retrieveBaseEntityList(FlightMission.class);
        allFlightMissions.forEach(flightMission -> {
            if (flightMission.getAssignedSpaceship().equals(spaceship)){
                throw new EntityIsNotToBeAssignedException(Spaceship.class.getSimpleName());
            }
        });
        spaceship.setReadyForNextMissions(false);
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        List<Spaceship> allSpaceships = findAllSpaceships();
        allSpaceships.forEach(spaceshipFromContext -> {
            if (spaceshipFromContext.getName().equalsIgnoreCase(spaceship.getName())) {
                throw new EntityIsNotUniqException("Spaceship with the same name is already in the context");
            }
        });
        return saveSpaceship(spaceship);
    }

    private Spaceship saveSpaceship(Spaceship spaceship) {
        applicationContext.retrieveBaseEntityList(Spaceship.class).add(spaceship);
        return spaceship;
    }

}
