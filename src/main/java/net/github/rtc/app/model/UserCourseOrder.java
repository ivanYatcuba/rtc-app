package net.github.rtc.app.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ivan on 15.04.14.
 */
@Entity
public class UserCourseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private long userId;

    @Column
    private String courseCode;

    @Column
    private Date requestDate;

    @Column
    private Date responseDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Column
    @Enumerated(EnumType.STRING)
    private TraineePosition position;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRequestStatus status;

    public UserCourseOrder(long userId, String courseCode, Date requestDate, Date responseDate,
                           Speciality speciality, UserRequestStatus status, TraineePosition position) {
        this.userId = userId;
        this.courseCode = courseCode;
        this.requestDate = requestDate;
        this.responseDate = responseDate;
        this.speciality = speciality;
        this.status = status;
        this.position = position;
    }

    public UserCourseOrder() {}


    public long getId() { return id; }
    public void setId(long id) { this.id = id;}

    public long getUser() { return userId; }
    public void setUser(long id) { this.userId = userId;}

    public String getCourse() { return courseCode; }
    public void setCourse(String course_code) { this.courseCode = course_code; }

    public Speciality getSpeciality() { return speciality; }
    public void setSpeciality(Speciality speciality) { this.speciality = speciality; }

    public Date getResponseDate() { return responseDate; }
    public void setResponseDate(Date responseDate) { this.responseDate = responseDate; }

    public Date getRequestDate() {return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }

    public UserRequestStatus getStatus() { return status; }
    public void setStatus(UserRequestStatus status) { this.status = status; }

    public TraineePosition getPosition() { return position; }
    public void setPosition(TraineePosition position) { this.position = position; }
}
