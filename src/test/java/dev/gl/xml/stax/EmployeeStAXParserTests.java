package dev.gl.xml.stax;

import dev.gl.xml.employee.Contact;
import dev.gl.xml.employee.ContactType;
import dev.gl.xml.employee.Employee;
import dev.gl.xml.employee.Group;
import dev.gl.xml.employee.GroupName;
import dev.gl.xml.employee.Location;
import dev.gl.xml.utils.ExamplesStorage;
import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
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
public class EmployeeStAXParserTests {

    private static EmployeeStAXParser parser;
    private static Employee employee;

    @BeforeAll
    static void init() {
        File file = ExamplesStorage.getFile(ExamplesStorage.TEST_EMPLOYEE_XML_PATH);
        parser = new EmployeeStAXParser(file);
    }

    @Test
    @Order(1)
    void employeeExists() {
        employee = parser.parseEmployee();
        assumeTrue(employee != null, "Employee should exist");
    }
    
    @Test
    void testFirstName() {
        assertEquals("Oscar", employee.getFirstName(), "FirstName should be \"Oscar\"");
    }

    @Test
    void testEmploymentDate() {
        assertEquals(LocalDate.of(2024, Month.JUNE, 1), employee.getEmploymentDate(),
                "EmploymentDate should be 2024.06.01");
    }

    @Test
    void testResignationDate() {
        assertNull(employee.getResignationDate(), "Resignation date should be null");
    }

    @Test
    void testGroupsNumber() {
        assertEquals(2, employee.getGroups().size(), "There should be two groups");
    }

    @Test
    void testGroupContent() {
        Group group = employee.getGroups().get(0);
        assertAll("Check all content of the first group",
                () -> assertEquals(Location.LIVERPOOL, group.getLocation()),
                () -> assertEquals(GroupName.BUDGETING_COMMON, group.getName()),
                () -> assertEquals("Participant", group.getRole()),
                () -> assertEquals(LocalDate.of(2024, Month.JUNE, 1), group.getFrom()),
                () -> assertEquals(LocalDate.of(2025, Month.FEBRUARY, 28), group.getTo()));
    }

    @Test
    void testContactsNumber() {
        assertAll("Check contacts completeness",
                () -> assertEquals(3, employee.getContacts().size(), "There should be three contacts"),
                () -> assertEquals(1, employee.getContactsByType().get(ContactType.PHONE).size()),
                () -> assertEquals(1, employee.getContactsByType().get(ContactType.EMAIL).size()),
                () -> assertEquals(1, employee.getContactsByType().get(ContactType.FACEBOOK).size()));
    }

    @Test
    void testContactsContect() {
        Map<ContactType, List<Contact>> contactsByType = employee.getContactsByType();

        Contact phone = contactsByType.get(ContactType.PHONE).get(0);
        Contact email = contactsByType.get(ContactType.EMAIL).get(0);
        Contact facebook = contactsByType.get(ContactType.FACEBOOK).get(0);

        assertAll("Check all three contact types",
                () -> assertEquals("123456789", phone.getValue()),
                () -> assertEquals("o_adams@my_test_company.qazqaz", email.getValue()),
                () -> assertEquals("https://www.facebook.com/oscar_adams_liverpool_my_test_company/", facebook.getValue()));
    }
}
