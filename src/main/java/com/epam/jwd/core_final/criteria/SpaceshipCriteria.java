package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    private final Role roleInNumber;
    private final Short smallerNumberOfRole;
    private final Short biggestNumberOfRole;
    private final Long smallerNumberRole;
    private final Long biggestNumberRole;
    private final Boolean readinessForNextMission;

    private SpaceshipCriteria(Long whereId, String whereName, Role roleInNumber,
                              Short smallerNumberOfRole, Short biggestNumberOfRole, Long biggestNumberRole,
                              Long smallerNumberRole, Boolean readinessForNextMission) {
        super(whereId, whereName);
        this.roleInNumber = roleInNumber;
        this.smallerNumberOfRole = smallerNumberOfRole;
        this.biggestNumberOfRole = biggestNumberOfRole;
        this.smallerNumberRole = smallerNumberRole;
        this.biggestNumberRole = biggestNumberRole;
        this.readinessForNextMission = readinessForNextMission;
    }

    public Role getRoleInNumber() {
        return roleInNumber;
    }

    public Short getSmallerNumberOfRole() {
        return smallerNumberOfRole;
    }

    public Short getBiggestNumberOfRole() {
        return biggestNumberOfRole;
    }

    public Long getSmallerNumberRole() {
        return smallerNumberRole;
    }

    public Long getBiggestNumberRole() {
        return biggestNumberRole;
    }

    public Boolean getReadinessForNextMission() {
        return readinessForNextMission;
    }

    public static Builder builder() {
        return new Builder();
    }

    private static class Builder {

        private Long id;
        private String name;
        private Role roleExistInCapacity;
        private Short lesserCountOfRole;
        private Short greaterCountOfRole;
        private Long lesserFlightDistance;
        private Long greaterFlightDistance;
        private Boolean readyForNextMission;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRoleExistInCapacity(Role role) {
            this.roleExistInCapacity = role;
            return this;
        }

        public Builder setLesserCountOfRole(Short countOfRole) {
            this.lesserCountOfRole = countOfRole;
            return this;
        }

        public Builder setGreaterCountOfRole(Short countOfRole) {
            this.greaterCountOfRole = countOfRole;
            return this;
        }

        public Builder setLesserFlightDistance(Long flightDistance) {
            this.lesserFlightDistance = flightDistance;
            return this;
        }

        public Builder setGreaterFlightDistance(Long flightDistance) {
            this.greaterFlightDistance = flightDistance;
            return this;
        }

        public Builder setReadyForNextMission(Boolean isReadyForNextMission) {
            this.readyForNextMission = isReadyForNextMission;
            return this;
        }

        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(id,
                    this.name,
                    this.roleExistInCapacity,
                    this.lesserCountOfRole,
                    this.greaterCountOfRole,
                    this.lesserFlightDistance,
                    this.greaterFlightDistance,
                    this.readyForNextMission);
        }
    }
}
