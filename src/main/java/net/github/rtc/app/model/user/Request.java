package net.github.rtc.app.model.user;

/**
 * Created by ivan on 24.04.14.
 */
public class Request {
    private long orderId;
    private String traineeName;
    private String reason;
    private String courseName;
    private String speciality;

    public Request() {
    }

    public Request(Integer orderId, String traineeName, String reason, String courseName, String speciality) {
        this.orderId = orderId;
        this.traineeName = traineeName;
        this.reason = reason;
        this.courseName = courseName;
        this.speciality = speciality;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
