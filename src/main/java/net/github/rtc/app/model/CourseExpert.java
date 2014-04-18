package net.github.rtc.app.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class CourseExpert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private long id_course;

    @Column
    @ElementCollection
    private List<Long> id_user;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getCourse() { return id_course; }
    public void setCourse(long id_course) { this.id_course = id_course; }

    public List<Long> getUser() { return id_user; }
    public void setUser(List<Long> id_user) { this.id_user = id_user;}
}
