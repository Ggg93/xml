package dev.gl.xml.employee;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.Objects;

/**
 *
 * @author gl
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact {

    @XmlAttribute(name = "Type")
    private ContactType type;
    @XmlElement(name = "Value")
    private String value;

    @Override
    public String toString() {
        return "Contact{"
                + "type=" + type
                + ", value=" + value
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + Objects.hashCode(this.value);
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
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return this.type == other.type;
    }
    
    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
