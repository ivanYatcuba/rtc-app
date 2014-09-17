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

    public UserCourseOrder(
      final String userCode,
      final String courseCode,
      final Date requestDate,
      final Date responseDate,
      final UserRequestStatus status,
      final TraineePosition position) {
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

    public void setId(final long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(final String userCode) {
        this.userCode = userCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(final String courseCode) {
        this.courseCode = courseCode;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(final Date responseDate) {
        this.responseDate = responseDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(final Date requestDate) {
        this.requestDate = requestDate;
    }

    public UserRequestStatus getStatus() {
        return status;
    }

    public void setStatus(final UserRequestStatus status) {
        this.status = status;
    }

    public TraineePosition getPosition() {
        return position;
    }

    public void setPosition(final TraineePosition position) {
        this.position = position;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }
}
