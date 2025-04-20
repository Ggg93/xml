package dev.gl.xml.dom;

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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author gl
 */
public class EmployeeDOMParser {

    public static void main(String[] args) {
        Logging.initLogger();
        Logger logger = Logger.getLogger(EmployeeDOMParser.class.getCanonicalName());
        
        File file = new File(ExamplesStorage.EMPLOYEE_XML_PATH);
        if (!file.exists()) {
            logger.severe("File does not exist");
            return;
        }

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            List<Employee> employees = new ArrayList<>();

            NodeList persons = doc.getElementsByTagName("Person");
            for (int i = 0; i < persons.getLength(); i++) {
                Employee employee = new Employee();
                employees.add(employee);

                Node person = persons.item(i);
                parsePerson(person, employee);
            }

            for (Employee employee : employees) {
                logger.info(employee.toString());
            }
        } catch (Exception e) {
            logger.severe("Exception occured: "
                    + e.getClass() + ": " + e.getLocalizedMessage());
            e.printStackTrace(System.out);
        }

    }

    private static void parsePerson(Node person, Employee employee) {
        NamedNodeMap attributes = person.getAttributes();
        Node attribute = attributes.getNamedItem("Type");
        if (attribute != null) {
            employee.setType(EmployeeType.valueOf(attribute.getNodeValue().toUpperCase()));
        }

        NodeList personChildElements = person.getChildNodes();
        for (int i = 0; i < personChildElements.getLength(); i++) {
            Node node = personChildElements.item(i);
            String nodeValue = node.getTextContent();

            switch (node.getNodeName()) {
                case "FirstName":
                    employee.setFirstName(nodeValue);
                    break;
                case "SecondName":
                    employee.setSecondName(nodeValue);
                    break;
                case "Department":
                    employee.setDepartment(nodeValue);
                    break;
                case "EmploymentDate":
                    try {
                        employee.setEmploymentDate(LocalDate.parse(nodeValue));
                    } catch (Exception e) {
                    }
                    break;
                case "ResignationDate":
                    try {
                        employee.setResignationDate(LocalDate.parse(nodeValue));
                    } catch (Exception e) {
                    }
                    break;
                case "Groups":
                    parseGroupsContainer(node, employee);
                    break;
                case "Contacts":
                    parseContactsContainer(node, employee);
                    break;
            }
        }
    }

    private static void parseGroupsContainer(Node groupsContainer, Employee employee) {
        NodeList groups = groupsContainer.getChildNodes();
        for (int i = 0; i < groups.getLength(); i++) {
            Node groupNode = groups.item(i);

            if (groupNode.getNodeName().equals("Group")) {
                Group group = new Group();
                employee.getGroups().add(group);

                NamedNodeMap attributes = groupNode.getAttributes();
                Node attribute = attributes.getNamedItem("Location");
                if (attribute != null) {
                    group.setLocation(Location.valueOf(attribute.getNodeValue().toUpperCase()));
                }
                attribute = attributes.getNamedItem("Name");
                if (attribute != null) {
                    group.setName(GroupName.valueOf(attribute.getNodeValue().toUpperCase()));
                }

                parseGroup(group, groupNode);
            }
        }
    }

    private static void parseGroup(Group group, Node groupNode) {
        NodeList groupElements = groupNode.getChildNodes();
        for (int i = 0; i < groupElements.getLength(); i++) {
            Node elementOfGroup = groupElements.item(i);
            String nodeName = elementOfGroup.getNodeName();
            String nodeValue = elementOfGroup.getTextContent();

            switch (nodeName) {
                case "Role":
                    group.setRole(nodeValue);
                    break;
                case "From":
                    try {
                        group.setFrom(LocalDate.parse(nodeValue));
                    } catch (Exception e) {
                    }
                    break;
                case "To":
                    try {
                        group.setTo(LocalDate.parse(nodeValue));
                    } catch (Exception e) {
                    }
                    break;
            }
        }
    }

    private static void parseContactsContainer(Node contactsContainer, Employee employee) {
        NodeList contacts = contactsContainer.getChildNodes();

        for (int i = 0; i < contacts.getLength(); i++) {
            Node contactNode = contacts.item(i);

            if (contactNode.getNodeName().equals("Contact")) {
                Contact contact = new Contact();
                employee.getContacts().add(contact);

                NamedNodeMap attributes = contactNode.getAttributes();
                Node type = attributes.getNamedItem("Type");
                if (type != null) {
                    contact.setType(ContactType.valueOf(type.getTextContent().toUpperCase()));
                }

                parseContact(contact, contactNode);
            }
        }
    }

    private static void parseContact(Contact contact, Node contactNode) {
        NodeList contactNodeElements = contactNode.getChildNodes();
        for (int i = 0; i < contactNodeElements.getLength(); i++) {
            Node contactElement = contactNodeElements.item(i);

            if (contactElement.getNodeName().equals("Value")) {
                contact.setValue(contactElement.getTextContent());
            }
        }
    }
}
