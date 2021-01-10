package com.epam.jwd.core_final.domain;

import java.util.Objects;

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
    private boolean doesHeIsSurvive = true;

    public CrewMember(String name, Role role, Rank rank) {
        super(name);
        this.role = role;
        this.rank = rank;
    }

    public CrewMember(String member) {
        super((String) member.subSequence(2, member.length() - 3));
        this.role = Role.resolveRoleById(Integer.parseInt(String.valueOf(member.charAt(0))));
        this.rank = Rank.resolveRankById((Integer.parseInt(String.valueOf(member.charAt((member.length() - 1))))));
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isDoesHeIsSurvive() {
        return doesHeIsSurvive;
    }

    public void setDoesHeIsSurvive(boolean doesHeIsSurvive) {
        this.doesHeIsSurvive = doesHeIsSurvive;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", memberRole=" + role +
                ", memberRank=" + rank +
                ", isReadyForNextMissions=" + doesHeIsSurvive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewMember that = (CrewMember) o;
        return doesHeIsSurvive == that.doesHeIsSurvive && role == that.role && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, rank, doesHeIsSurvive);
    }
}
