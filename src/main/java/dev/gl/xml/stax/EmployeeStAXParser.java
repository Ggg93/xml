package dev.gl.xml.stax;

import dev.gl.xml.employee.Contact;
import dev.gl.xml.employee.ContactType;
import dev.gl.xml.employee.Employee;
import dev.gl.xml.employee.EmployeeType;
import dev.gl.xml.employee.Group;
import dev.gl.xml.employee.GroupName;
import dev.gl.xml.employee.Location;
import dev.gl.xml.utils.ExamplesStorage;
import dev.gl.xml.utils.Logging;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author gl
 */
public class EmployeeStAXParser {

    private static final Logger LOGGER = Logging.getLocalLogger(EmployeeStAXParser.class);
    private final File file;

    public EmployeeStAXParser(File file) {

        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("Input file is not exist!");
        }

        this.file = file;
    }

    public static void main(String[] args) {

        File file = ExamplesStorage.getFile(ExamplesStorage.EMPLOYEE_XML_PATH);

        EmployeeStAXParser parser = new EmployeeStAXParser(file);
        Employee employee = parser.parseEmployee();
        LOGGER.info(employee.toString());
    }

    public Employee parseEmployee() {

        Employee employee = null;

        try {
            InputStream is = new FileInputStream(file);
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLEventReader reader = factory.createXMLEventReader(is);

            Group group = null;
            Contact contact = null;

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartDocument()) {
                    employee = new Employee();
                    continue;
                }
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    Attribute attribute;

                    switch (startElement.getName().getLocalPart()) {
                        case "Person":
                            attribute = startElement.getAttributeByName(new QName("Type"));
                            if (attribute != null) {
                                employee.setType(EmployeeType.valueOf(attribute.getValue().toUpperCase()));
                            }
                            break;
                        case "FirstName":
                            event = reader.nextEvent();
                            employee.setFirstName(event.asCharacters().getData());
                            break;
                        case "SecondName":
                            event = reader.nextEvent();
                            employee.setSecondName(event.asCharacters().getData());
                            break;
                        case "Department":
                            event = reader.nextEvent();
                            employee.setDepartment(event.asCharacters().getData());
                            break;
                        case "EmploymentDate":
                            event = reader.nextEvent();
                            try {
                                LocalDate date = LocalDate.parse(event.asCharacters().getData());
                                employee.setEmploymentDate(date);
                            } catch (Exception e) {
                            }
                            break;
                        case "ResignationDate":
                            event = reader.nextEvent();
                            try {
                                LocalDate date = LocalDate.parse(event.asCharacters().getData());
                                employee.setResignationDate(date);
                            } catch (Exception e) {
                            }
                            break;
                        case "Group":
                            group = new Group();
                            employee.getGroups().add(group);

                            attribute = startElement.getAttributeByName(new QName("Location"));
                            if (attribute != null) {
                                group.setLocation(Location.valueOf(attribute.getValue().toUpperCase()));
                            }

                            attribute = startElement.getAttributeByName(new QName("Name"));
                            if (attribute != null) {
                                group.setName(GroupName.valueOf(attribute.getValue().toUpperCase()));
                            }
                            break;
                        case "Role":
                            event = reader.nextEvent();
                            group.setRole(event.asCharacters().getData());
                            break;
                        case "From":
                            event = reader.nextEvent();
                            try {
                                LocalDate date = LocalDate.parse(event.asCharacters().getData());
                                group.setFrom(date);
                            } catch (Exception e) {
                            }
                            break;
                        case "To":
                            event = reader.nextEvent();
                            try {
                                LocalDate date = LocalDate.parse(event.asCharacters().getData());
                                group.setTo(date);
                            } catch (Exception e) {
                            }
                            break;
                        case "Contact":
                            contact = new Contact();
                            employee.getContacts().add(contact);

                            attribute = startElement.getAttributeByName(new QName("Type"));
                            if (attribute != null) {
                                ContactType contactType = ContactType.valueOf(attribute.getValue().toUpperCase());
                                contact.setType(contactType);

                                // fill Map<ContactType, List<Contact>>
                                List<Contact> contactsByType = employee.getContactsByType()
                                        .getOrDefault(contactType, new ArrayList<>());
                                contactsByType.add(contact);
                                employee.getContactsByType().put(contactType, contactsByType);
                            }
                            break;
                        case "Value":
                            event = reader.nextEvent();
                            contact.setValue(event.asCharacters().getData());
                            break;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

}
