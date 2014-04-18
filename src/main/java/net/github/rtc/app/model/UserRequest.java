package net.github.rtc.app.model;

import javax.persistence.*;

/**
 * Created by ivan on 15.04.14.
 */
//@Entity
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long id_user;
    private long id_course;

    public long getId() { return id; }
    public void setId(long id) { this.id = id;}

    public long getUser() { return id_user; }
    public void setUser(long id) { this.id_user = id_user;}

    public long getCourse() { return id_course; }
    public void setCourse(long id_course) { this.id_course = id_course; }
}
