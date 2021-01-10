package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Spaceship;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private final Long flightDistance;
    private final Boolean isReadyForNextMissions;

    private SpaceshipCriteria(Builder builder){
        super(builder);
        this.flightDistance = builder.flightDistance;
        this.isReadyForNextMissions = builder.isReadyForNextMissions;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class Builder extends Criteria.Builder{
        private Long flightDistance;
        private Boolean isReadyForNextMissions;

        public Builder setFlightDistance(Long flightDistance){
            this.flightDistance = flightDistance;
            return this;
        }

        public Builder setIsReadyForNextMissions(Boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public SpaceshipCriteria build(){
            return new SpaceshipCriteria(this);
        }
    }
}
