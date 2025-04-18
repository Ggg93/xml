package dev.gl.xml.employee;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

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
