
package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.context.impl.ApplicationMenuImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            ApplicationMenu menu = Application.start();
            menu.printAvailableOptions();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    //Artem
}