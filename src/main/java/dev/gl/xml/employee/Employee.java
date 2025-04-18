package dev.gl.xml.employee;

import dev.gl.xml.stax.LocalDateJAXBAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gl
 */
@XmlRootElement(name = "Person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {

    @XmlAttribute(name = "Type")
    private EmployeeType type;
    
    @XmlElement(name = "FirstName")
    private String firstName;
    
    @XmlElement(name = "SecondName")
    private String secondName;
    
    @XmlElement(name = "Department")
    private String department;
    
    @XmlElement(name = "EmploymentDate")
    @XmlJavaTypeAdapter(value = LocalDateJAXBAdapter.class)
    private LocalDate employmentDate;
    
    @XmlElement(name = "ResignationDate")
    @XmlJavaTypeAdapter(value = LocalDateJAXBAdapter.class)
    private LocalDate resignationDate;
    
    @XmlElementWrapper(name = "Groups")
    private List<Group> groups;
    
    @XmlElementWrapper(name = "Contacts")
    @XmlElement(name = "Contact")
    private List<Contact> contacts;

    public Employee() {
        groups = new ArrayList<>();
        contacts = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Employee{"
                + "type=" + type
                + ", firstName=" + firstName
                + ", secondName=" + secondName
                + ", department=" + department
                + ", employmentDate=" + employmentDate
                + ", resignationDate=" + resignationDate
                + ", groups=" + groups
                + ", contacts=" + contacts
                + '}';
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public LocalDate getResignationDate() {
        return resignationDate;
    }

    public void setResignationDate(LocalDate resignationDate) {
        this.resignationDate = resignationDate;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}
