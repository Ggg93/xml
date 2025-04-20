package dev.gl.xml.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author gl
 */
public class Logging {

    public static Logger logger;

    public static Logger getLocalLogger(Class clazz) {

        // firstly check that we have already read JUL configuration
        if (logger == null) {
            initLogger();
        }
        return Logger.getLogger(clazz.getCanonicalName());
    }

    
    /**
     * reading JUL properties from logging.properties if exists
     */
    private static void initLogger() {

        File file = new File(".\\logging.properties");
        if (!file.exists()) {
            Logger logger = Logger.getLogger(Logging.class.getCanonicalName());
            logger.config("Logger has been initialized from default configuration");
            return;
        }

        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream(file));
            Logger logger = Logger.getLogger(Logging.class.getCanonicalName());
            logger.config("Logger has been initialized from custom configuration");

        } catch (IOException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

}
