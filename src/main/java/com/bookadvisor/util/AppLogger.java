package com.bookadvisor.util;

import java.io.IOException;
import java.util.logging.*;

public class AppLogger {

    private static AppLogger instance;
    private final Logger logger;

    private AppLogger() {
        logger = Logger.getLogger("BookAdvisorLogger");

        try {
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get("logs"));

            FileHandler appHandler = new FileHandler("logs/app.log", true);
            appHandler.setFormatter(new SimpleFormatter());
            appHandler.setLevel(Level.INFO);

            FileHandler errorHandler = new FileHandler("logs/error.log", true);
            errorHandler.setFormatter(new SimpleFormatter());
            errorHandler.setLevel(Level.SEVERE);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.INFO);

            logger.setUseParentHandlers(false);
            logger.addHandler(consoleHandler);
            logger.addHandler(appHandler);
            logger.addHandler(errorHandler);
            logger.setLevel(Level.INFO);

        } catch (IOException e) {
            System.err.println("❌ Logger setup failed: " + e.getMessage());
        }
    }

    public static synchronized AppLogger getInstance() {
        if (instance == null) {
            instance = new AppLogger();
        }
        return instance;
    }

    public Logger getLogger() {
        return logger;
    }
}
