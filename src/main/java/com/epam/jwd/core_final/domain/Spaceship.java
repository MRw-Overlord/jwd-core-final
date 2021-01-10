package com.epam.jwd.core_final.domain;

import java.util.Map;
import java.util.Objects;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo
    private Map<Role, Short> crew;
    private long totalAvailableFlightDistance;
    private boolean isReadyForNextMissions = true;

    public Spaceship(String name, Map<Role, Short> crew, Long totalAvailableFlightDistance) {
        super(name);
        this.crew = crew;
        this.totalAvailableFlightDistance = totalAvailableFlightDistance;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public long getTotalAvailableFlightDistance() {
        return totalAvailableFlightDistance;
    }

    public boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    public void setTotalAvailableFlightDistance(long totalAvailableFlightDistance) {
        this.totalAvailableFlightDistance = totalAvailableFlightDistance;
    }

    public void setReadyForNextMissions(boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    @Override
    public String toString() {
        return ("Spaceship -- name: " + super.getName()
                + " Flight Distance: " + totalAvailableFlightDistance
                + " Is ready for next missions: " + isReadyForNextMissions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return totalAvailableFlightDistance == spaceship.totalAvailableFlightDistance &&
                isReadyForNextMissions == spaceship.isReadyForNextMissions &&
                crew.equals(spaceship.crew);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crew, totalAvailableFlightDistance, isReadyForNextMissions);
    }
}
