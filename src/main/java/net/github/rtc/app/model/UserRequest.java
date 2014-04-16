package net.github.rtc.app.model;

<<<<<<< HEAD
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
=======
/**
 * Created by ivan on 15.04.14.
 */
public class UserRequest {
    private long id;
    private User user;
>>>>>>> remotes/origin/expert
    private Course course;

    public long getId() { return id; }
    public void setId(long id) { this.id = id;}

    public User getUser() { return user; }
<<<<<<< HEAD
    public void setUser(User user) { this.user = user;}

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
=======

    public void setUser(User user) { this.user = user;}

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
>>>>>>> remotes/origin/expert
}
