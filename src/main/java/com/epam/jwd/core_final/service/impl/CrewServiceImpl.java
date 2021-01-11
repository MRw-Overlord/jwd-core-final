package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityIsNotToBeAssignedException;
import com.epam.jwd.core_final.exception.NotUniqEntityException;
import com.epam.jwd.core_final.service.CrewService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {
    private static CrewServiceImpl instance;
    private final ApplicationContext applicationContext;
    protected List<CrewMember> crewMemberList;

    private CrewServiceImpl() {
        this.applicationContext = NassaContext.getInstance();
        this.crewMemberList =  (List<CrewMember>)applicationContext.retrieveBaseEntityList(CrewMember.class);
    }

    public static CrewServiceImpl getInstance() {
        if (instance == null) {
            instance = new CrewServiceImpl();
        }
        return instance;
    }


    @Override
    public List<CrewMember> findAllCrewMembers() {
        return this.crewMemberList;
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        CrewMemberCriteria memberCriteria = (CrewMemberCriteria) criteria;
        return this.crewMemberList.stream().filter(crewMember -> (
                (crewMember.getName().equals(memberCriteria.getName()) || memberCriteria.getName() == null) &&
                        (crewMember.getRank() == memberCriteria.getRank() || memberCriteria.getRank() == null) &&
                        (crewMember.isDoesHeIsSurvive() == memberCriteria.isDoesHeIsSurvive() ||
                                memberCriteria.isDoesHeIsSurvive() == null) &&
                        (crewMember.getRole() == memberCriteria.getRole() || memberCriteria.getRole() == null)))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        CrewMemberCriteria memberCriteria = (CrewMemberCriteria) criteria;
        return this.crewMemberList.stream().filter(crewMember -> (
                (memberCriteria.getName() == null || crewMember.getName().equals(memberCriteria.getName())) &&
                        (memberCriteria.getRank() == null || crewMember.getRank() == memberCriteria.getRank()) &&
                        (memberCriteria.isDoesHeIsSurvive() == null ||
                                crewMember.isDoesHeIsSurvive() == memberCriteria.isDoesHeIsSurvive()) &&
                        (memberCriteria.getRole() == null || crewMember.getRole() == memberCriteria.getRole())
        )).findFirst();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        crewMemberList.set(Integer.parseInt(crewMember.getId().toString()) - 1, crewMember);
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws EntityIsNotToBeAssignedException {
       if (!crewMember.isDoesHeIsSurvive()){
           throw new EntityIsNotToBeAssignedException();
       }
        SpaceshipServiceImpl spaceshipCrud = SpaceshipServiceImpl.getInstance();
        SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria
                .Builder().setIsReadyForNextMissions(false).setIsVacant(crewMember.getRole()).build();
        Optional<Spaceship> optionalSpaceship = spaceshipCrud.findSpaceshipByCriteria(spaceshipCriteria);
        if (optionalSpaceship.isPresent()) {
            Spaceship suitableSpaceship = optionalSpaceship.get();
            Map<Role, Short> spaceshipCrew = suitableSpaceship.getCrew();
            Short crewRoleNumber = (short)(spaceshipCrew.get(crewMember.getRole()) - 1);
            spaceshipCrew.put(crewMember.getRole(), crewRoleNumber);
            spaceshipCrud.updateSpaceshipDetails(suitableSpaceship);
            MissionServiceImpl missionCrud = MissionServiceImpl.getInstance();
            FlightMissionCriteria missionCriteria = new FlightMissionCriteria.Builder().setAssignedSpaceship(suitableSpaceship).build();
            FlightMission currentMission = missionCrud.findMissionByCriteria(missionCriteria).get();
            List<CrewMember> missionCrew = currentMission.getAssignedCrew();
            if (missionCrew == null) missionCrew = new ArrayList<>();
            missionCrew.add(crewMember);
            currentMission.setAssignedCrew(missionCrew);
            missionCrud.updateSpaceshipDetails(currentMission);
            crewMember.setDoesHeIsSurvive(false);
            this.updateCrewMemberDetails(crewMember);
        } else {
            throw new EntityIsNotToBeAssignedException();
        }

    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws NotUniqEntityException {
        CrewMemberCriteria criteria = new CrewMemberCriteria.Builder().build();
        criteria.setName(crewMember.getName());
        if (findCrewMemberByCriteria(criteria).isPresent()) {
            throw new NotUniqEntityException();
        } else {
            crewMemberList.add(crewMember);
        }
        return crewMember;
    }
}