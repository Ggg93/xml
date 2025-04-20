package dev.gl.xml.jaxb;

import dev.gl.xml.employee.Contact;
import dev.gl.xml.employee.ContactType;
import dev.gl.xml.employee.Employee;
import dev.gl.xml.employee.EmployeeType;
import dev.gl.xml.employee.Group;
import dev.gl.xml.employee.GroupName;
import dev.gl.xml.employee.Location;
import dev.gl.xml.utils.Logging;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Logger;

/**
 *
 * @author gl
 */
public class JAXBHandler {
    
    private static final Logger LOGGER = Logging.getLocalLogger(JAXBHandler.class);

    public static void main(String[] args) {
        
        Employee employee = createEmployee();
        new File(".\\src\\main\\resources\\jaxb_output").mkdirs();
        File outputFile = new File(".\\src\\main\\resources\\jaxb_output\\BennettBrown.xml");
        marshal(employee, outputFile);
        employee = unmarshal(outputFile);
        
        String unmarshallingResult;
        if (employee != null) {
            unmarshallingResult = employee.toString();
        } else {
            unmarshallingResult = "Something went wrong!";
        }
        LOGGER.info(unmarshallingResult);
    }

    private static Employee createEmployee() {
        Employee employee = new Employee();
        employee.setType(EmployeeType.EMPLOYEE);
        employee.setFirstName("Bennett");
        employee.setSecondName("Brown");
        employee.setDepartment("IT");
        employee.setEmploymentDate(LocalDate.of(2020, Month.JANUARY, 10));

        Group group = new Group();
        group.setLocation(Location.LONDON);
        group.setName(GroupName.COST_CONTROL);
        group.setRole("adviser");
        group.setFrom(LocalDate.of(2022, Month.MARCH, 1));
        employee.getGroups().add(group);

        Contact contact = new Contact();
        contact.setType(ContactType.PHONE);
        contact.setValue("555444333");
        employee.getContacts().add(contact);
        return employee;
    }

    private static void marshal(Employee employee, File outputFile) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(employee, outputFile);
        } catch (Exception e) {
            LOGGER.severe("Exception occured. "
                    + e.getClass() + ": " + e.getLocalizedMessage());
            e.printStackTrace(System.out);
        }

    }

    private static Employee unmarshal(File outputFile) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Employee employee = (Employee) unmarshaller.unmarshal(outputFile);
            return employee;

        } catch (Exception e) {
            LOGGER.severe("Exception occured. "
                    + e.getClass() + ": " + e.getLocalizedMessage());
            e.printStackTrace(System.out);
        }

        return null;
    }
}
