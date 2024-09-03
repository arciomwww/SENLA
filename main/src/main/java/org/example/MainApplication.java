package org.example;

import controller.MainController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApplication {
    private static final Logger logger = Logger.getLogger(MainApplication.class.getName());

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ApplicationContext("controller", "service", "database", "util");

            MainController controller = context.getBean(MainController.class);

            controller.handleRequest();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while running the application", e);
        }
    }
}