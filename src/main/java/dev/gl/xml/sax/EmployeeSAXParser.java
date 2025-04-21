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
    private final File file;

    public EmployeeSAXParser(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("Input file is not exist!");
        }

        this.file = file;
    }

    public static void main(String[] args) {

        File file = ExamplesStorage.getFile(ExamplesStorage.EMPLOYEE_XML_PATH);

        EmployeeSAXParser parser = new EmployeeSAXParser(file);
        Employee employee = parser.parseEmployee();
        LOGGER.info(employee.toString());
    }

    public Employee parseEmployee() {

        try {
            SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
            SAXParser parser = factory.newSAXParser();
            EmployeeSAXHandler handler = new EmployeeSAXHandler();

            parser.parse(file, handler); // handler functions are callbacks
            return handler.getEmployee();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
