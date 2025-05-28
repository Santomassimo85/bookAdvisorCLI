package com.bookadvisor.util;

import java.io.IOException;
import java.util.logging.*;

/**
 * Singleton class for application-wide logging.
 * Configures logging to console, app log file, and error log file.
 */
public class AppLogger {

    // Singleton instance of AppLogger
    private static AppLogger instance;
    // Java Logger instance used for logging
    private final Logger logger;

    /**
     * Private constructor to configure the logger.
     * Sets up handlers for console, application log, and error log.
     */
    private AppLogger() {
        logger = Logger.getLogger("BookAdvisorLogger");

        try {
            // Ensure the logs directory exists
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get("logs"));

            // Handler for general application logs
            FileHandler appHandler = new FileHandler("logs/app.log", true);
            appHandler.setFormatter(new SimpleFormatter());
            appHandler.setLevel(Level.INFO);

            // Handler for error logs (SEVERE level)
            FileHandler errorHandler = new FileHandler("logs/error.log", true);
            errorHandler.setFormatter(new SimpleFormatter());
            errorHandler.setLevel(Level.SEVERE);

            // Handler for console output
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.INFO);

            // Disable default parent handlers
            logger.setUseParentHandlers(false);
            // Add custom handlers
            logger.addHandler(consoleHandler);
            logger.addHandler(appHandler);
            logger.addHandler(errorHandler);
            // Set logger level
            logger.setLevel(Level.INFO);

        } catch (IOException e) {
            System.err.println("❌ Logger setup failed: " + e.getMessage());
        }
    }

    /**
     * Returns the singleton instance of AppLogger.
     *
     * @return AppLogger instance
     */
    public static synchronized AppLogger getInstance() {
        if (instance == null) {
            instance = new AppLogger();
        }
        return instance;
    }

    /**
     * Returns the configured Logger instance.
     *
     * @return Logger instance
     */
    public Logger getLogger() {
        return logger;
    }
}
