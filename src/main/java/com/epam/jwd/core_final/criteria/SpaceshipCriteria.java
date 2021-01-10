package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private Long flightDistance;
    private Boolean isReadyForNextMissions;
    private Role isVacant;
    private Map<Role, Short> crew;

    private SpaceshipCriteria(Builder builder) {
        super(builder);
        this.flightDistance = builder.flightDistance;
        this.isReadyForNextMissions = builder.isReadyForNextMissions;
        this.isVacant = builder.isVacant;
        this.crew = builder.crew;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Role getIsVacant() {
        return isVacant;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class Builder extends Criteria.Builder {
        private Long flightDistance;
        private Boolean isReadyForNextMissions;
        private Role isVacant;
        private Map<Role, Short> crew;

        public Builder setIsVacant(Role isVacant) {
            this.isVacant = isVacant;
            return this;
        }

        public Builder setCrew(Map<Role, Short> crew) {
            this.crew = crew;
            return this;
        }

        public Builder setFlightDistance(Long flightDistance) {
            this.flightDistance = flightDistance;
            return this;
        }


        public Builder setIsReadyForNextMissions(Boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(this);
        }
    }
}
