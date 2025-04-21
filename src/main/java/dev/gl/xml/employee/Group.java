package dev.gl.xml.employee;

import dev.gl.xml.jaxb.LocalDateJAXBAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 *
 * @author gl
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Group {

    @XmlAttribute(name = "Location")
    private Location location;
    
    @XmlAttribute(name = "Name")
    private GroupName name;
    
    @XmlElement(name = "Role")
    private String role;
    
    @XmlElement(name = "From")
    @XmlJavaTypeAdapter(value = LocalDateJAXBAdapter.class)
    private LocalDate from;
    
    @XmlElement(name = "To")
    @XmlJavaTypeAdapter(value = LocalDateJAXBAdapter.class)
    private LocalDate to;

    @Override
    public String toString() {
        return "Group{"
                + "location=" + location
                + ", name=" + name
                + ", role=" + role
                + ", from=" + from
                + ", to=" + to
                + '}';
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public GroupName getName() {
        return name;
    }

    public void setName(GroupName name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

}
