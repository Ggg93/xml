package dev.gl.xml.jaxb;

import dev.gl.xml.employee.Employee;
import java.io.File;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author gl
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JAXBHandlerTests {

    private static JAXBHandler handler;
    private static Employee employee;
    private static File file = new File(".\\src\\main\\resources\\jaxb_output\\BennettBrown.xml");

    @BeforeAll
    static void init() {
        handler = new JAXBHandler(file, file);
        employee = JAXBHandler.createEmployee();
    }

    @Test
    @Order(1)
    void testMarshall() {
        handler.marshal(employee);
        employee = null;
        assumeTrue(file.exists(), "File should exist");
    }
    
    @Test
    @Order(2)
    void testThatEmployeeIsNullBeforeUnmarshalling() {
        assumeTrue(employee == null);
    }

    @Test
    @Order(3)
    void testUnmarshall() {
        employee = handler.unmarshal();
        assumeTrue(employee != null, "Employee should exist");
    }
}
