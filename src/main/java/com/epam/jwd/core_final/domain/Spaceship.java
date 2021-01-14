package com.epam.jwd.core_final.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo
    private static Long idUnique = 0L;

    private final Map<Role, Short> mapByRoleShort;
    private Long flightDistance;
    private Boolean isReadyForNextMission;

    public Spaceship(String name, Map<Role, Short> mapByRoleShort, Long flightDistance) {
        super(idUnique++, name);
        this.mapByRoleShort = mapByRoleShort;
        this.flightDistance = flightDistance;
        this.isReadyForNextMission = true;
    }

    public Map<Role, Short> getMapByRoleShort() {
        return new HashMap<>(mapByRoleShort);
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getReadyForNextMission() {
        return isReadyForNextMission;
    }

    public void setReadyForNextMission(Boolean readyForNextMission) {
        isReadyForNextMission = readyForNextMission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return getId().equals(spaceship.getId()) &&
                getName().equals(spaceship.getName()) &&
                Objects.equals(mapByRoleShort, spaceship.mapByRoleShort) &&
                Objects.equals(flightDistance, spaceship.flightDistance) &&
                Objects.equals(isReadyForNextMission, spaceship.isReadyForNextMission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), mapByRoleShort, flightDistance, isReadyForNextMission);
    }

    @Override
    public String toString() {
        return "Spaceship{\n" +
                "\tid: " + getId() +
                "\n\tname: " + getName() +
                "\n\tcapacityByRole: " + mapByRoleShort +
                "\n\tflightDistance: " + flightDistance +
                "\n\tisReadyForNextMission: " + isReadyForNextMission +
                "\n}";
    }
}
