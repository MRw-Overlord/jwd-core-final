package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.EntityIsNotToBeAssignedException;
import com.epam.jwd.core_final.exception.EntityIsNotToBeCreatedException;
import com.epam.jwd.core_final.exception.EntityIsNotUniqException;
import com.epam.jwd.core_final.service.CrewService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {
    private static CrewServiceImpl instance;
    private final ApplicationContext applicationContext;

    private CrewServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static CrewServiceImpl getInstance() {
        if (instance == null) {
            instance = new CrewServiceImpl(NassaContext.getInstance());
        }
        return instance;
    }


    @Override
    public List<CrewMember> findAllCrewMembers() {
        return new ArrayList<>(applicationContext.retrieveBaseEntityList(CrewMember.class));
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> crewMembers = findAllCrewMembers();
        CrewMemberCriteria memberCriteria = (CrewMemberCriteria) criteria;
        return crewMembers.stream().filter(crewMember -> (
                (crewMember.getName().equals(memberCriteria.getName()) || memberCriteria.getName() == null) &&
                        (crewMember.getRank() == memberCriteria.getRank() || memberCriteria.getRank() == null) &&
                        (crewMember.isDoesHeIsSurvive() == memberCriteria.isDoesHeIsSurvive() ||
                                memberCriteria.isDoesHeIsSurvive() == null) &&
                        (crewMember.getRole() == memberCriteria.getRole() || memberCriteria.getRole() == null)))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> crewMembers = findAllCrewMembers();
        CrewMemberCriteria memberCriteria = (CrewMemberCriteria) criteria;
        return crewMembers.stream().filter(crewMember -> (
                (memberCriteria.getName() == null || crewMember.getName().equals(memberCriteria.getName())) &&
                        (memberCriteria.getRank() == null || crewMember.getRank() == memberCriteria.getRank()) &&
                        (memberCriteria.isDoesHeIsSurvive() == null ||
                                crewMember.isDoesHeIsSurvive() == memberCriteria.isDoesHeIsSurvive()) &&
                        (memberCriteria.getRole() == null || crewMember.getRole() == memberCriteria.getRole())
        )).findFirst();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember oldCrewMember, CrewMember newCrewMember) {
        oldCrewMember.setRank(newCrewMember.getRank());
        oldCrewMember.setRole(newCrewMember.getRole());
        return oldCrewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws EntityIsNotToBeAssignedException {
        Collection<FlightMission> allFlightMission = applicationContext.retrieveBaseEntityList(FlightMission.class);
        allFlightMission.forEach(flightMission -> {
            if (flightMission.getAssignedCrew().contains(crewMember)) {
                throw new EntityIsNotToBeAssignedException(CrewMember.class.getSimpleName());
            }
        });
        crewMember.setDoesHeIsSurvive(false);
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws EntityIsNotToBeCreatedException {
        List<CrewMember> allCrewMembers = findAllCrewMembers();
        allCrewMembers.forEach(crewMemberFromMission -> {
            if (crewMemberFromMission.getName().equalsIgnoreCase(crewMember.getName())) {
                throw new EntityIsNotUniqException(crewMemberFromMission.getName());
            }
        });
        return saveCrewMember(crewMember);
    }

    private CrewMember saveCrewMember(CrewMember crewMember) {
        applicationContext.retrieveBaseEntityList(CrewMember.class).add(crewMember);
        return crewMember;
    }
}