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
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Logger;

/**
 *
 * @author gl
 */
public class JAXBHandler {

    private static final Logger LOGGER = Logging.getLocalLogger(JAXBHandler.class);
    private final File source;
    private final File destination;

    public JAXBHandler(File source, File destination) {

        if (source == null && destination == null) {
            throw new IllegalArgumentException("No file exists!");
        }

        if (source != null && destination == null && !source.exists()) {
            throw new IllegalArgumentException("Source is not exist!");
        }

        if (destination != null) {

            // delete file if it already exists
            if (destination.exists()) {
                destination.delete();
            }

            try {
                String destinationFilePath = destination.getCanonicalPath();
                String destinationDirectoryPath = destinationFilePath.substring(0, destinationFilePath.lastIndexOf("\\"));
                new File(destinationDirectoryPath).mkdirs();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        this.source = source;
        this.destination = destination;
    }

    public static void main(String[] args) {

        File file = new File(".\\src\\main\\resources\\jaxb_output\\BennettBrown.xml");
        JAXBHandler handler = new JAXBHandler(file, file);

        Employee employee = createEmployee();

        handler.marshal(employee);
        employee = handler.unmarshal();

        LOGGER.info(employee.toString());
    }

    public static Employee createEmployee() {
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

    public void marshal(Employee employee) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(employee, destination);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Employee unmarshal() {
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Employee) unmarshaller.unmarshal(source);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
