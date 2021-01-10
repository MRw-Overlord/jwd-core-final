package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private final Role role;
    private final Rank rank;
    private final Boolean doesHeIsSurvive;

    private CrewMemberCriteria(Builder builder){
        super(builder);
        this.role = builder.role;
        this.rank = builder.rank;
        this.doesHeIsSurvive = builder.doesHeIsSurvive;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean isDoesHeIsSurvive() {
        return doesHeIsSurvive;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public static class Builder extends Criteria.Builder{
        private Role role;
        private Rank rank;
        private Boolean doesHeIsSurvive;

        public Builder setRole(Role role){
            this.role = role;
            return this;
        }

        public Builder setRank(Rank rank){
            this.rank = rank;
            return this;
        }

        public Builder setDoesHeIsSurvive(Boolean doesHeIsSurvive){
            this.doesHeIsSurvive = doesHeIsSurvive;
            return this;
        }

        public CrewMemberCriteria build(){
            return new CrewMemberCriteria(this);
        }
    }
}
