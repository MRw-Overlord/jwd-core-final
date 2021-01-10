package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            Application.start();
        } catch (InvalidStateException e){
            LOGGER.error("Your application is died :[");
        }
        LOGGER.info("Ty loh)))");

    }

}