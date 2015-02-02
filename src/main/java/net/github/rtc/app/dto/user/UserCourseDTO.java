package net.github.rtc.app.dto.user;

import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.user.User;

import java.util.Date;
import java.util.Set;

public class UserCourseDTO {

    public static final int HUNDRED = 100;
    private String code;
    private Integer capacity;
    private Integer acceptedOrders;
    private String name;
    private Set<CourseType> types;
    private Date startDate;
    private Date endDate;
    private CourseStatus status;
    private String description;
    private Set<User> experts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CourseType> getTypes() {
        return types;
    }

    public void setTypes(Set<CourseType> types) {
        this.types = types;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getExperts() {
        return experts;
    }

    public void setExperts(Set<User> experts) {
        this.experts = experts;
    }

    public Integer getAcceptedOrders() {
        return acceptedOrders;
    }

    public void setAcceptedOrders(Integer acceptedOrders) {
        this.acceptedOrders = acceptedOrders;
    }

    public Integer getAcceptancePercent() {
        return acceptedOrders * HUNDRED / capacity;
    }
}
