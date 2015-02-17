package net.github.rtc.app.model.dto.user;

import net.github.rtc.app.model.entity.order.UserRequestStatus;

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
        return courseStartDate == null ? null : new Date(courseStartDate.getTime());
    }

    public void setCourseStartDate(Date courseStartDate) {
        if (courseStartDate != null) {
            this.courseStartDate = new Date(courseStartDate.getTime());
        }
    }

    public Date getCourseEndDate() {
        return courseEndDate == null ? null : new Date(courseEndDate.getTime());
    }

    public void setCourseEndDate(Date courseEndDate) {
        if (courseEndDate != null) {
            this.courseEndDate = new Date(courseEndDate.getTime());
        }
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
        return orderDate == null ? null : new Date(orderDate.getTime());
    }

    public void setOrderDate(Date orderDate) {
        if (orderDate != null) {
            this.orderDate = new Date(orderDate.getTime());
        }
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
