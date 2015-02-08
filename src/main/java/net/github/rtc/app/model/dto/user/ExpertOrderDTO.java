package net.github.rtc.app.model.dto.user;

import net.github.rtc.app.model.entity.user.UserRequestStatus;

import java.util.Date;

public class ExpertOrderDTO {

    private String userCode;
    private String userName;
    private String userPhoto;

    private String courseCode;
    private String courseName;
    private Date courseStartDate;
    private Date courseEndDate;
    private int courseCapacity;
    private int courseAcceptedOrders;

    private String orderCode;
    private Date orderDate;
    private UserRequestStatus status;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(Date courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public Date getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(Date courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public int getCourseCapacity() {
        return courseCapacity;
    }

    public void setCourseCapacity(int courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    public int getCourseAcceptedOrders() {
        return courseAcceptedOrders;
    }

    public void setCourseAcceptedOrders(int courseAcceptedOrders) {
        this.courseAcceptedOrders = courseAcceptedOrders;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public UserRequestStatus getStatus() {
        return status;
    }

    public void setStatus(UserRequestStatus status) {
        this.status = status;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
