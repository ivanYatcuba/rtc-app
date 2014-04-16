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

    @Column
    private User user;

    @Column
    private Course course;

    public long getId() { return id; }
    public void setId(long id) { this.id = id;}

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user;}

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
