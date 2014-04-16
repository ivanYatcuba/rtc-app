package net.github.rtc.app.model;

import javax.persistence.*;
import java.util.List;

//@Entity
public class CourseExpert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private Course course;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private List<User> user;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public List<User> getUser() { return user; }
    public void setUser(List<User> user) { this.user = user;}
}
