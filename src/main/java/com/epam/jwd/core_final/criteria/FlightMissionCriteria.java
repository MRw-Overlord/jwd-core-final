package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {

    private final LocalDateTime beforeStartDate;
    private final LocalDateTime afterStartDate;
    private final LocalDateTime beforeEndDate;
    private final LocalDateTime afterEndDate;
    private final Long smallerDistance;
    private final Long biggerDistance;
    private final Spaceship assignedSpaceship;
    private final List<CrewMember> assignedCrews;
    private final MissionResult missionResult;

    private FlightMissionCriteria(Long whereId, String whereName, LocalDateTime beforeStartDate,
                                  LocalDateTime afterStartDate, LocalDateTime beforeEndDate,
                                  LocalDateTime afterEndDate, Long smallerDistance, Long biggerDistance,
                                  Spaceship assignedSpaceship, List<CrewMember> assignedCrews,
                                  MissionResult missionResult) {
        super(whereId, whereName);
        this.beforeStartDate = beforeStartDate;
        this.afterStartDate = afterStartDate;
        this.beforeEndDate = beforeEndDate;
        this.afterEndDate = afterEndDate;
        this.smallerDistance = smallerDistance;
        this.biggerDistance = biggerDistance;
        this.assignedSpaceship = assignedSpaceship;
        this.assignedCrews = assignedCrews;
        this.missionResult = missionResult;
    }

    public LocalDateTime getBeforeStartDate() {
        return beforeStartDate;
    }

    public LocalDateTime getAfterStartDate() {
        return afterStartDate;
    }

    public LocalDateTime getBeforeEndDate() {
        return beforeEndDate;
    }

    public LocalDateTime getAfterEndDate() {
        return afterEndDate;
    }

    public Long getSmallerDistance() {
        return smallerDistance;
    }

    public Long getBiggerDistance() {
        return biggerDistance;
    }

    public Spaceship getAssignedSpaceship() {
        return assignedSpaceship;
    }

    public List<CrewMember> getAssignedCrews() {
        return assignedCrews;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public static Builder builder() {
        return new Builder();
    }

    private static class Builder {

        private Long id;
        private String name;
        private LocalDateTime beforeStartDate;
        private LocalDateTime afterStartDate;
        private LocalDateTime beforeEndDate;
        private LocalDateTime afterEndDate;
        private Long smallerDistance;
        private Long biggerDistance;
        private Spaceship assignedSpaceship;
        private List<CrewMember> assignedCrews;
        private MissionResult missionResult;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBeforeStartDate(LocalDateTime dateTime) {
            this.beforeStartDate = dateTime;
            return this;
        }

        public Builder setAfterStartDate(LocalDateTime dateTime) {
            this.afterStartDate = dateTime;
            return this;
        }

        public Builder setBeforeEndDate(LocalDateTime dateTime) {
            this.beforeEndDate = dateTime;
            return this;
        }

        public Builder setAfterEndDate(LocalDateTime dateTime) {
            this.afterEndDate = dateTime;
            return this;
        }

        public Builder setSmallerDistance(Long distance) {
            this.smallerDistance = distance;
            return this;
        }

        public Builder setBiggerDistance(Long distance) {
            this.biggerDistance = distance;
            return this;
        }

        public Builder setAssignedSpaceship(Spaceship spaceship) {
            this.assignedSpaceship = spaceship;
            return this;
        }

        public Builder setAssignedCrew(List<CrewMember> assignedCrewMembers) {
            this.assignedCrews = assignedCrewMembers;
            return this;
        }

        public Builder setMissionResult(MissionResult missionResult) {
            this.missionResult = missionResult;
            return this;
        }

        public FlightMissionCriteria build() {
            return new FlightMissionCriteria(id, name, beforeStartDate, afterStartDate,
                    beforeEndDate, afterEndDate, smallerDistance, biggerDistance,
                    assignedSpaceship, assignedCrews, missionResult);
        }
    }
}
