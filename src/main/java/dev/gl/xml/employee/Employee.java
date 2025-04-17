package dev.gl.xml.employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gl
 */
public class Employee {

    private EmployeeType type;
    private String firstName;
    private String secondName;
    private String department;
    private LocalDate employmentDate;
    private LocalDate resignationDate;
    private List<Group> groups;
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
