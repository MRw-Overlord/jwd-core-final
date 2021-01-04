package com.epam.jwd.core_final.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class FlightMission extends AbstractBaseEntity {
    // todo

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private long missionsDistance;
    private Spaceship spaceship;
    private List<CrewMember> crew;
    private MissionResult missionResult;

    public FlightMission(String name, LocalDate startDate, LocalDate endDate, long missionsDistance) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.missionsDistance = missionsDistance;
    }

    @Override
    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getMissionsDistance() {
        return missionsDistance;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public List<CrewMember> getCrew() {
        return crew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setMissionsDistance(long missionsDistance) {
        this.missionsDistance = missionsDistance;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public void setCrew(List<CrewMember> crew) {
        this.crew = crew;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    @Override
    public String toString() {
        return ("Flight Mission -- Mission name " + name
                + " Mission start: " + startDate
                + " Mission end: " + endDate
                + " Mission Distance: " + missionsDistance);
    }
}
