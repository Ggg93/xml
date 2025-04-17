package dev.gl.xml.employee;

import java.time.LocalDate;

/**
 *
 * @author gl
 */
public class Group {

    private Location location;
    private GroupName name;
    private String role;
    private LocalDate from;
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
