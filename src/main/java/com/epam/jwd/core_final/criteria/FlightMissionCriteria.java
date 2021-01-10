package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Spaceship assignedSpaceship;
    private final Long distance;
    private final MissionResult missionResult;
    private final List<CrewMember> assignedCrew;

    private FlightMissionCriteria(Builder builder) {
        super(builder);
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.assignedCrew = builder.assignedCrew;
        this.assignedSpaceship = builder.assignedSpaceship;
        this.missionResult = builder.missionResult;
        this.distance = builder.distant;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Spaceship getAssignedSpaceship() {
        return assignedSpaceship;
    }

    public Long getDistance() {
        return distance;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public static class Builder extends Criteria.Builder {
        private LocalDate startDate;
        private LocalDate endDate;
        private Spaceship assignedSpaceship;
        private Long distant;
        private MissionResult missionResult;
        private List<CrewMember> assignedCrew;

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setAssignedSpaceship(Spaceship assignedSpaceship) {
            this.assignedSpaceship = assignedSpaceship;
            return this;
        }

        public Builder setDistant(Long distant) {
            this.distant = distant;
            return this;
        }

        public Builder setMissionResult(MissionResult missionResult) {
            this.missionResult = missionResult;
            return this;
        }

        public Builder setAssignedCrew(List<CrewMember> assignedCrew) {
            this.assignedCrew = assignedCrew;
            return this;
        }

        public FlightMissionCriteria build() {
            return new FlightMissionCriteria(this);
        }
    }
}
