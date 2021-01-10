package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeFormatter;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */

public class ApplicationProperties {
    public static final Logger LOGGER = LoggerFactory.getLogger(ApplicationProperties.class);
    //todo

    private final String inputRootDir;
    private final String outputRootDir;
    private final String crewFileName;
    private final String missionsFileName;
    private final String spaceshipsFileName;
    private final Integer fileRefreshRate;
    private final DateTimeFormatter dateTimeFormat;

    public ApplicationProperties(String inputRootDir, String outputRootDir, String crewFilename,
                                 String missionsFileName, String spaceshipsFileName, Integer fileRefreshRate,
                                 DateTimeFormatter patternForDateTimeFormatter) {
        this.inputRootDir = inputRootDir;
        this.outputRootDir = outputRootDir;
        this.crewFileName = crewFilename;
        this.missionsFileName = missionsFileName;
        this.spaceshipsFileName = spaceshipsFileName;
        this.fileRefreshRate = fileRefreshRate;
        this.dateTimeFormat = patternForDateTimeFormatter;
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getMissionFileName() {
        return missionsFileName;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public DateTimeFormatter getDateTimeFormat() {
        return dateTimeFormat;
    }

/*    public static Builder builder(){
        return new Builder();
    }

    private static class Builder{
        private String inputRootDir;
        private String outputRootDir;
        private String crewFileName;
        private String missionFileName;
        private String spaceshipsFileName;
        private Integer fileRefreshRate;
        private String dateTimeFormat;

        private Builder(){

        }

        public Builder setInputRootDir(String inputRootDir){
            this.inputRootDir = inputRootDir;
            return this;
        }

        public Builder setOutputRootDir(String outputRootDir){
            this.outputRootDir = outputRootDir;
            return this;
        }

        public Builder setCrewFileName(String crewFileName){
            this.crewFileName = crewFileName;
            return this;
        }

        public Builder setMissionFileName(String missionFileName){
            this.missionFileName = missionFileName;
            return this;
        }

        public Builder setSpaceshipsFileName(String spaceshipsFileName){
            this.spaceshipsFileName = spaceshipsFileName;
            return this;
        }

        public Builder setFileRefreshRate(Integer fileRefreshRate){
            this.fileRefreshRate = fileRefreshRate;
            return this;
        }

        public Builder setDateTimeFormat(String dateTimeFormat) {
            this.dateTimeFormat = dateTimeFormat;
            return this;
        }

        public ApplicationProperties build(){
            return new ApplicationProperties(this.inputRootDir,
                    this.outputRootDir,
                   this.crewFileName,
                    this.missionFileName,
                    this.spaceshipsFileName,
                    this.fileRefreshRate,
                    this.dateTimeFormat);
        }
    }*/
}
