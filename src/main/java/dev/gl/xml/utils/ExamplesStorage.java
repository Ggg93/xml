package dev.gl.xml.utils;

import java.io.File;
import java.util.logging.Logger;

/**
 *
 * @author gl
 */
public class ExamplesStorage {

    public static final String EMPLOYEE_XML_PATH
            = ".\\src\\main\\resources\\dev\\gl\\xml\\employee\\employee.xml";

    private static final Logger LOGGER = Logging.getLocalLogger(ExamplesStorage.class);

    public static File getFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            LOGGER.severe("File does not exist");
            throw new RuntimeException("File \"" + path + "\" not found");
        }
        return file;
    }
}
