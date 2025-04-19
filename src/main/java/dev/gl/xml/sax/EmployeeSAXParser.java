package dev.gl.xml.sax;

import dev.gl.xml.employee.Employee;
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
        
        File xml = new File(".\\src\\main\\resources\\dev\\gl\\xml\\employee\\employee.xml");
        if (!xml.exists()) {
            logger.severe("File is not exists!");
            return;
        }

        SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
        try {
            EmployeeSAXHandler handler = new EmployeeSAXHandler();
            SAXParser parser = factory.newSAXParser();
            parser.parse(xml, handler); // handler functions are callbacks

            Employee employee = handler.getEmployee();
            logger.info(employee.toString());

        } catch (Exception e) {
            logger.severe(e.getClass() + ": " + e.getLocalizedMessage());
        }

    }
}
