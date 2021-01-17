package com.epam.jwd.core_final.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private static Long idUnique = 0L;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime startDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime endDate;
    private final Long distance;
    private Spaceship assignedSpaceship;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    public FlightMission(String name, LocalDateTime startDate, LocalDateTime endDate, Long distance,
                         MissionResult missionResult) {
        super(idUnique++, name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.missionResult = missionResult;
    }

    public FlightMission(String name, LocalDateTime startDate, LocalDateTime endDate, Long distance,
                         Spaceship assignedSpaceship, List<CrewMember> assignedCrew, MissionResult missionResult) {
        super(idUnique++, name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.assignedSpaceship = assignedSpaceship;
        this.assignedCrew = assignedCrew;
        this.missionResult = missionResult;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Long getDistance() {
        return distance;
    }

    public Spaceship getAssignedSpaceship() {
        return assignedSpaceship;
    }

    public void setAssignedSpaceship(Spaceship assignedSpaceship) {
        this.assignedSpaceship = assignedSpaceship;
    }

    public List<CrewMember> getAssignedCrew() {
        return new ArrayList<>(assignedCrew);
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightMission that = (FlightMission) o;
        return getId().equals(that.getId()) &&
                getName().equals(that.getName()) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(distance, that.distance) &&
                Objects.equals(assignedSpaceship, that.assignedSpaceship) &&
                Objects.equals(assignedCrew, that.assignedCrew) &&
                missionResult == that.missionResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), startDate, endDate, distance, assignedSpaceship, assignedCrew,
                missionResult);
    }

    @Override
    public String toString() {
        return "FlightMission{ \n" +
                "Id: " + getId() +
                "\nName: " + getName() +
                "\nStartDate: " + startDate +
                "\nEndDate: " + endDate +
                "\nDistance: " + distance +
                "\n### AssignedSpaceship ###: " + assignedSpaceship +
                "\n\n\n### AssignedCrew ###: " + assignedCrew +
                "\nMissionResult: " + missionResult +
                "\n}";
    }

    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.registerModule(new JavaTimeModule());
        //mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(this);
    }
}
