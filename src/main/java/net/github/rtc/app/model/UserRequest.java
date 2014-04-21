package net.github.rtc.app.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ivan on 15.04.14.
 */
@Entity
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private long id_user;

    @Column
    private String course_code;

    @Column
    private Date requestDate;

    @Column
    private Date responseDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRequestStatus status;

    public UserRequest(long id_user, String course_code, Date requestDate, Date responseDate,
                       Speciality speciality, UserRequestStatus status) {
        this.id_user = id_user;
        this.course_code = course_code;
        this.requestDate = requestDate;
        this.responseDate = responseDate;
        this.speciality = speciality;
        this.status = status;
    }

    public UserRequest() {}


    public long getId() { return id; }
    public void setId(long id) { this.id = id;}

    public long getUser() { return id_user; }
    public void setUser(long id) { this.id_user = id_user;}

    public String getCourse() { return course_code; }
    public void setCourse(String course_code) { this.course_code = course_code; }

    public Speciality getSpeciality() { return speciality; }
    public void setSpeciality(Speciality speciality) { this.speciality = speciality; }

    public Date getResponseDate() { return responseDate; }
    public void setResponseDate(Date responseDate) { this.responseDate = responseDate; }

    public Date getRequestDate() {return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }

    public UserRequestStatus getStatus() { return status; }
    public void setStatus(UserRequestStatus status) { this.status = status; }
}
