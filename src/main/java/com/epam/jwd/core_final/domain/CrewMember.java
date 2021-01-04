package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    // todo
    private Role role;
    private Rank rank;
    private boolean doesItSurvive = true;

    public CrewMember(String name, Role role, Rank rank) {
        super(name);
        this.role = role;
        this.rank = rank;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isDoesItSurvive() {
        return doesItSurvive;
    }

    public void setDoesItSurvive(boolean doesItSurvive) {
        this.doesItSurvive = doesItSurvive;
    }

    @Override
    public String toString() {
        return ("CrewMember -- Role: " + role + " name: " + super.getName() + " rank: " + rank);
    }
}
