package dev.gl.xml.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author gl
 */
public class Logging {

    public static void initLogger() {
        Logger logger = null;

        File file = new File(".\\logging.properties");
        if (!file.exists()) {
            logger = Logger.getLogger(Logging.class.getCanonicalName());
        }

        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream(file));
            logger = Logger.getLogger(Logging.class.getCanonicalName());
        } catch (Exception e) {
            logger.severe("Something went wrong: " + e.getLocalizedMessage());
        }
        
        logger.config("Logger has been initialized from " 
                + (file.exists() ? "custom" : "default") + " configuration");

    }
}
