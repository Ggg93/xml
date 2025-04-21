package dev.gl.xml.employee;

import dev.gl.xml.jaxb.LocalDateJAXBAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.location);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.role);
        hash = 29 * hash + Objects.hashCode(this.from);
        hash = 29 * hash + Objects.hashCode(this.to);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Group other = (Group) obj;
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (this.location != other.location) {
            return false;
        }
        if (this.name != other.name) {
            return false;
        }
        if (!Objects.equals(this.from, other.from)) {
            return false;
        }
        return Objects.equals(this.to, other.to);
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
