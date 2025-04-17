package dev.gl.xml.employee;

/**
 *
 * @author gl
 */
public class Contact {

    private ContactType type;
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
