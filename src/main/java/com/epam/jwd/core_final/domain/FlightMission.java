package com.epam.jwd.core_final.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    private LocalDate startDate;
    private LocalDate endDate;
    private long missionsDistance;
    private Spaceship assignedSpaceship;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    public FlightMission(String name,
                         LocalDate startDate,
                         LocalDate endDate,
                         long missionsDistance,
                         MissionResult flightMissionResult) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.missionsDistance = missionsDistance;
        this.missionResult = flightMissionResult;
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

    public Spaceship getAssignedSpaceship() {
        return assignedSpaceship;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
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

    public void setAssignedSpaceship(Spaceship assignedSpaceship) {
        this.assignedSpaceship = assignedSpaceship;
    }

    public void setAssignedCrew(List<CrewMember> crew) {
        this.assignedCrew = crew;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    @Override
    public String toString() {
        return "FlightMission{" +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", missionsDistance=" + missionsDistance +
                ", spaceship=" + assignedSpaceship +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightMission that = (FlightMission) o;
        return missionsDistance == that.missionsDistance && startDate.equals(that.startDate) && endDate.equals(that.endDate) && assignedSpaceship.equals(that.assignedSpaceship) && assignedCrew.equals(that.assignedCrew) && missionResult == that.missionResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, missionsDistance, assignedSpaceship, assignedCrew, missionResult);
    }
}
