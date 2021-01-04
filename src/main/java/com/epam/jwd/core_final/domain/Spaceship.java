package com.epam.jwd.core_final.domain;

import java.util.Map;

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

    public Spaceship(String name, Map<Role, Short> crew, long totalAvailableFlightDistance) {
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
}
