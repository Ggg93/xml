package dev.gl.xml.sax;

import dev.gl.xml.employee.Employee;
import dev.gl.xml.utils.ExamplesStorage;
import dev.gl.xml.utils.Logging;
import java.io.File;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author gl
 */
public class EmployeeSAXParser {

    public static void main(String[] args) {
        Logging.initLogger();
        Logger logger = Logger.getLogger(EmployeeSAXParser.class.getCanonicalName());
        
        File file = new File(ExamplesStorage.EMPLOYEE_XML_PATH);
        if (!file.exists()) {
            logger.severe("File is not exists!");
            return;
        }

        SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
        try {
            EmployeeSAXHandler handler = new EmployeeSAXHandler();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, handler); // handler functions are callbacks

            Employee employee = handler.getEmployee();
            logger.info(employee.toString());

        } catch (Exception e) {
            logger.severe(e.getClass() + ": " + e.getLocalizedMessage());
        }

    }
}
