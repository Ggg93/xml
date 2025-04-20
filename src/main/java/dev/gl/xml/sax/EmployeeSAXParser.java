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

    private static final Logger LOGGER = Logging.getLocalLogger(EmployeeSAXParser.class);

    public static void main(String[] args) {
        
        File file = ExamplesStorage.getFile(ExamplesStorage.EMPLOYEE_XML_PATH);

        SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
        try {
            EmployeeSAXHandler handler = new EmployeeSAXHandler();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, handler); // handler functions are callbacks

            Employee employee = handler.getEmployee();
            LOGGER.info(employee.toString());

        } catch (Exception e) {
            LOGGER.severe(e.getClass() + ": " + e.getLocalizedMessage());
        }

    }
}
