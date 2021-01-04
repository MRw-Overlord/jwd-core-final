package com.epam.jwd.core_final.domain;

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
    //todo

    private String inputRootDir;
    private String outputRootDir;
    private String crewFileName;
    private String missionFileName;
    private String spaceshipsFileName;
    private Integer fileRefreshRate;
    private String dateTimeFormat;

    private ApplicationProperties(String inputRootDir,
                                 String outputRootDir,
                                 String crewFileName,
                                 String missionFileName,
                                 String spaceshipsFileName,
                                 Integer fileRefreshRate,
                                 String dateTimeFormat){
        this.crewFileName = crewFileName;
        this.inputRootDir = inputRootDir;
        this.dateTimeFormat = dateTimeFormat;
        this.outputRootDir = outputRootDir;
        this.missionFileName = missionFileName;
        this.spaceshipsFileName = spaceshipsFileName;
        this.fileRefreshRate = fileRefreshRate;
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
        return missionFileName;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    private static class Builder{
        private String inputRootDir;
        private String outputRootDir;
        private String crewFileName;
        private String missionFileName;
        private String spaceshipsFileName;
        private Integer fileRefreshRate;
        private String dateTimeFormat;

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
    }
}
