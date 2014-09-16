package net.github.rtc.app.model.user;

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
    private String userCode;

    @Column
    private String courseCode;

    @Column
    private Date requestDate;

    @Column
    private Date responseDate;

    @Column
    @Enumerated(EnumType.STRING)
    private TraineePosition position;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRequestStatus status;

    @Column
    private String reason;

    public UserCourseOrder(String userCode, String courseCode, Date requestDate, Date responseDate,
                           UserRequestStatus status, TraineePosition position) {
        this.userCode = userCode;
        this.courseCode = courseCode;
        this.requestDate = requestDate;
        this.responseDate = responseDate;
        this.status = status;
        this.position = position;
    }

    public UserCourseOrder() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public UserRequestStatus getStatus() {
        return status;
    }

    public void setStatus(UserRequestStatus status) {
        this.status = status;
    }

    public TraineePosition getPosition() {
        return position;
    }

    public void setPosition(TraineePosition position) {
        this.position = position;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
