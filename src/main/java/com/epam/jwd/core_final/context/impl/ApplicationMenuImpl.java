package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.exception.NonUniqueEntityNameException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ApplicationMenuImpl implements ApplicationMenu {

    private static ApplicationMenuImpl instance;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationMenuImpl.class);

    private static final String MAIN_MENU = "Main menu:\n" +
            "1. Show all crew members\n" +
            "2. Show all spaceships\n" +
            "3. Show all flight missions\n" +
            "4. Flight missions menu\n" +
            "5. Exit\n";

    private static final String FLIGHT_MISSIONS_MENU = "Flight missions menu:\n" +
            "1. Create flight mission\n" +
            "2. Export flight missions to file in Json format\n" +
            "3. Back to previous menu\n";

    private final Scanner scanner = new Scanner(System.in);
    private final CrewService crewService = CrewServiceImpl.createInstance();
    private final SpaceshipService spaceshipService = SpaceshipServiceImpl.createInstance();
    private final MissionService missionService = MissionServiceImpl.createInstance();
    private final EntityFactory<FlightMission> entityFactory = FlightMissionFactory.createInstance();
    private final String fileName = BaikonurContext.getApplicationProperties().getMissionsFileName();
    private final String fileDirectory = BaikonurContext.getApplicationProperties().getOutputRootDir();
    private final DateTimeFormatter dateTimeFormatter = BaikonurContext.getApplicationProperties().getDateTimeFormatter();

    private ApplicationMenuImpl() {

    }

    public static ApplicationMenuImpl createInstance() {
        if (instance == null) {
            instance = new ApplicationMenuImpl();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return BaikonurContext.createInstance();
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("Welcome to App!\n");
        boolean applicationRun = true;
        while (applicationRun) {
            System.out.println(MAIN_MENU);
            String command = handleUserInput("Enter command: ");
            switch (command.toLowerCase()) {
                case ("1"):
                case ("show all crew members"): {
                    crewService.findAllCrewMembers().forEach(System.out::println);
                    break;
                }case ("2"):
                case ("show all spaceships"): {
                    spaceshipService.findAllSpaceships().forEach(System.out::println);
                    break;
                }

                case ("3"):
                case ("show all flight missions"): {
                    missionService.findAllMissions().forEach(System.out::println);
                    break;
                }
                case ("4"):
                case ("flight missions menu"): {
                    flightMissionsMenu();
                    break;
                }
                case ("5"):
                case ("exit"): {
                    applicationRun = false;
                    break;
                }
                default:{
                    System.out.println("Try again!! ");
                }
            }
        }
    }

    @Override
    public String handleUserInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public void flightMissionsMenu() {
        boolean newIteration = true;
        while (newIteration) {
            System.out.println(FLIGHT_MISSIONS_MENU);
            String command = handleUserInput("Enter command: ");
            switch (command.toLowerCase()) {
                case ("1"):
                case ("create flight mission"): {
                    String inputMissionName = handleUserInput("Enter mission name: ");
                    String inputMissionStartDateTime = handleUserInput(
                            "Enter mission start date time in format yyyy-MM-dd HH:mm:ss: ");
                    String inputMissionEndDateTime = handleUserInput(
                            "Enter mission end date time in format yyyy-MM-dd HH:mm:ss: ");
                    String inputMissionDistance = handleUserInput("Enter mission distance: ");
                    try {
                        Long missionDistance = Long.valueOf(inputMissionDistance);
                        if (missionDistance > 0) {
                            LocalDateTime missionStart = LocalDateTime.parse(inputMissionStartDateTime, dateTimeFormatter);
                            LocalDateTime missionEnd = LocalDateTime.parse(inputMissionEndDateTime, dateTimeFormatter);
                            missionService.createMission(entityFactory.create(inputMissionName, missionStart, missionEnd,
                                    missionDistance, MissionResult.PLANNED));
                        } else {
                            System.out.println("You may enter mission distance >0");
                        }
                    } catch (DateTimeParseException | NumberFormatException | NonUniqueEntityNameException e) {
                        LOGGER.warn("Error of input data in mission creation", e);
                        System.out.println("Invalid input in mission creation");
                    }
                }
                case ("2"):
                case ("export flight missions to file in Json format"): {
                    List<FlightMission> flightMissions = missionService.findAllMissions();
                    File filePath = new File(String.format("src/main/resources/%s", fileDirectory));
                    filePath.mkdirs();
                    File file = new File(filePath + "/" + fileName + ".json");
                    try (FileWriter fileWriter = new FileWriter(file)) {
                        if (!flightMissions.isEmpty()) {
                            fileWriter.append("[");
                            List<String> strings = flightMissions.stream().map(flightMission -> {
                                try {
                                    return flightMission.convertToJson();
                                } catch (JsonProcessingException e) {
                                    LOGGER.error("Json serialization flight mission object error");
                                }
                                return null;
                            }).collect(Collectors.toList());
                            String result = String.join(",\r\n", strings);
                            fileWriter.append(result);
                            fileWriter.append("]\r\n");
                        }
                        fileWriter.flush();
                    } catch (IOException e) {
                        LOGGER.error("File not found or json serialization error", e);
                    }
                    break;
                }
                case ("3"):
                case ("back to previous menu"): {
                    newIteration = false;
                    break;
                }
                default: {
                    System.out.println("Try again: ");
                }
            }
        }
    }
}
