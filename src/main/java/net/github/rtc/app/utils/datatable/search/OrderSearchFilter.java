package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.entity.course.CourseType;
import net.github.rtc.app.model.entity.user.UserRequestStatus;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import java.util.Date;

public class OrderSearchFilter extends AbstractSearchCommand {

    private CourseType courseType;
    private Date orderDate;
    private UserRequestStatus status;

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
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

    @Override
    public Order order() {
        return null;
    }

    @Override
    public DetachedCriteria getCriteria() {
        return null;
    }
}
