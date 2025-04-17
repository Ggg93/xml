package dev.gl.xml.sax;

import dev.gl.xml.employee.Employee;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author gl
 */
public class EmployeeSAXParser {

    public static void main(String[] args) {
        File xml = new File(".\\src\\main\\resources\\dev\\gl\\xml\\employee\\employee.xml");
        if (!xml.exists()) {
            System.out.println("File is not exists!");
            return;
        }

        SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
        try {
            EmployeeSAXHandler handler = new EmployeeSAXHandler();
            SAXParser parser = factory.newSAXParser();
            parser.parse(xml, handler);

            Employee employee = handler.getEmployee();
            System.out.println(employee.toString());

        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getLocalizedMessage());
        }

    }
}
