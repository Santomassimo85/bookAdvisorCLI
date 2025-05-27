package com.bookadvisor.util;

import java.io.IOException;
import java.util.logging.*;

public class AppLogger {

    private static final Logger logger = Logger.getLogger("BookAdvisorLogger");

    static {
        try {
            // Create the logs/ directory if it doesn't exist
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get("logs"));

            // General log: app.log
            FileHandler appHandler = new FileHandler("logs/app.log", true);
            appHandler.setFormatter(new SimpleFormatter());
            appHandler.setLevel(Level.INFO);

            // Error log only: error.log
            FileHandler errorHandler = new FileHandler("logs/error.log", true);
            errorHandler.setFormatter(new SimpleFormatter());
            errorHandler.setLevel(Level.SEVERE);

            // Console output
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.INFO);

            logger.setUseParentHandlers(false);
            logger.addHandler(appHandler);
            logger.addHandler(errorHandler);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.INFO);

        } catch (IOException e) {
            System.err.println("❌ Logger setup failed: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
