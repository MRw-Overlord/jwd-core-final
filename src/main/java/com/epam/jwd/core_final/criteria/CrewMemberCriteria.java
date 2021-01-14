package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    private final Role memberRole;
    private final Rank memberRank;
    private final Boolean readyForNextMission;

    private CrewMemberCriteria(Long whereId, String whereName, Role memberRole, Rank memberRank,
                               Boolean readyForNextMission) {
        super(whereId, whereName);
        this.memberRole = memberRole;
        this.memberRank = memberRank;
        this.readyForNextMission = readyForNextMission;
    }

    public Role getMemberRole() {
        return memberRole;
    }

    public Rank getMemberRank() {
        return memberRank;
    }

    public Boolean getReadyForNextMission() {
        return readyForNextMission;
    }

    public static Builder builder() {
        return new Builder();
    }

    private static class Builder {

        private Long id;
        private String name;
        private Role memberRole;
        private Rank memberRank;
        private Boolean readyForNextMission;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRole(Role role) {
            memberRole = role;
            return this;
        }

        public Builder setRank(Rank rank) {
            memberRank = rank;
            return this;
        }

        public Builder setReadyForNextMission(Boolean isReadyForNextMission) {
            readyForNextMission = isReadyForNextMission;
            return this;
        }

        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(id, name, memberRole, memberRank,
                    readyForNextMission);
        }
    }
}
