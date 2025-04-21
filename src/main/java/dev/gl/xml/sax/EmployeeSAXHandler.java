package dev.gl.xml.sax;

import dev.gl.xml.employee.Contact;
import dev.gl.xml.employee.ContactType;
import dev.gl.xml.employee.Employee;
import dev.gl.xml.employee.EmployeeType;
import dev.gl.xml.employee.Group;
import dev.gl.xml.employee.GroupName;
import dev.gl.xml.employee.Location;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author gl
 */
public class EmployeeSAXHandler extends DefaultHandler {

    private static Logger logger = Logger.getLogger(EmployeeSAXHandler.class.getCanonicalName());
    private Employee employee;
    private Group group;
    private Contact contact;
    private String currentElement;
    private EmployeeXMLSection documentSection;

    @Override
    public void startDocument() throws SAXException {
        logger.info("Parsing started");
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("Parsing finished");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        var attributeValue = "";

        switch (currentElement) {
            case "Person":
                documentSection = EmployeeXMLSection.PERSON;

                employee = new Employee();
                attributeValue = attributes.getValue("Type");
                employee.setType(EmployeeType.valueOf(attributeValue.toUpperCase()));
                break;
            case "Group":
                documentSection = EmployeeXMLSection.GROUP;

                group = new Group();
                employee.getGroups().add(group);

                // get group's location
                attributeValue = attributes.getValue("Location");
                group.setLocation(Location.valueOf(attributeValue.toUpperCase()));

                // get group's name
                attributeValue = attributes.getValue("Name");
                group.setName(GroupName.valueOf(attributeValue.toUpperCase()));
                break;

            case "Contact":
                documentSection = EmployeeXMLSection.CONTACT;

                contact = new Contact();
                employee.getContacts().add(contact);

                attributeValue = attributes.getValue("Type");
                if (attributeValue != null) {
                    ContactType contactType = ContactType.valueOf(attributeValue.toUpperCase());
                    contact.setType(contactType);

                    // fill Map<ContactType, List<Contact>>
                    List<Contact> contactsByType = employee.getContactsByType()
                            .getOrDefault(contactType, new ArrayList<>());
                    contactsByType.add(contact);
                    employee.getContactsByType().put(contactType, contactsByType);
                }

                break;
        }
    }

    /**
     * do nothing for now
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        var value = new String(ch, start, length);

        switch (documentSection) {
            case PERSON:
                parsePerson(value);
                break;
            case GROUP:
                parseGroup(value);
                break;
            case CONTACT:
                parseContact(value);
                break;
        }
        currentElement = "";
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        logger.warning("EmployeeSAXHandler warning: " + e.getLocalizedMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        logger.severe("EmployeeSAXHandler error: " + e.getLocalizedMessage());
    }

    public Employee getEmployee() {
        return employee;
    }

    private void parsePerson(String value) {
        switch (currentElement) {
            case "FirstName":
                employee.setFirstName(value);
                break;
            case "SecondName":
                employee.setSecondName(value);
                break;
            case "Department":
                employee.setDepartment(value);
                break;
            case "EmploymentDate":
                try {
                    employee.setEmploymentDate(LocalDate.parse(value));
                } catch (DateTimeParseException e) {
                }
                break;
            case "ResignationDate":
                try {
                    employee.setResignationDate(LocalDate.parse(value));
                } catch (DateTimeParseException e) {
                }
                break;
        }
    }

    private void parseGroup(String value) {
        switch (currentElement) {
            case "Role":
                group.setRole(value);
                break;
            case "From":
                try {
                    group.setFrom(LocalDate.parse(value));
                } catch (DateTimeParseException e) {
                }
                break;
            case "To":
                try {
                    group.setTo(LocalDate.parse(value));
                } catch (DateTimeParseException e) {
                }
                break;
        }
    }

    private void parseContact(String value) {
        if (currentElement.equals("Value")) {
            contact.setValue(value);
        }
    }

}
