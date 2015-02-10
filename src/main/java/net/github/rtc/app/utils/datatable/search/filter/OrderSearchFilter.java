package net.github.rtc.app.utils.datatable.search.filter;

import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.course.CourseType;
import net.github.rtc.app.model.entity.user.UserCourseOrder;
import net.github.rtc.app.model.entity.user.UserRequestStatus;
import org.hibernate.criterion.*;

import java.util.Date;

public class OrderSearchFilter extends AbstractSearchCommand {

    private static final String EXPERTS = "experts";
    private static final String POSITION = "position";
    private static final String REQUEST_DATE = "requestDate";
    private static final String STATUS = "status";
    private static final String COURSE_CODE = "courseCode";
    private static final String CODE = "code";

    private CourseType courseType;
    private Date orderDate;
    private UserRequestStatus status;
    private char dateMoreLessEq;
    private String expertCode;

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

    public char getDateMoreLessEq() {
        return dateMoreLessEq;
    }

    public void setDateMoreLessEq(char dateMoreLessEq) {
        this.dateMoreLessEq = dateMoreLessEq;
    }

    public UserRequestStatus getStatus() {
        return status;
    }

    public void setStatus(UserRequestStatus status) {
        this.status = status;
    }

    public String getExpertCode() {
        return expertCode;
    }

    public void setExpertCode(String expertCode) {
        this.expertCode = expertCode;
    }

    @Override
    public Order order() {
        return Order.asc(REQUEST_DATE);
    }

    @Override
    public DetachedCriteria getCriteria() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(UserCourseOrder.class);
        if (courseType != null) {
            criteria.add(Restrictions.eq(POSITION, courseType));
        }
        if (orderDate != null) {
            switch (dateMoreLessEq) {
                case '>':
                    final Date fromDate = orderDate;
                    criteria.add(Restrictions.gt(REQUEST_DATE, fromDate));
                    break;
                case '=':
                    final Date toDate = orderDate;
                    criteria.add(Restrictions.between(REQUEST_DATE, orderDate, toDate));
                    break;
                case '<':
                    criteria.add(Restrictions.lt(REQUEST_DATE, orderDate));
                    break;
                default:
                    break;
            }
        }

        if (status != null) {
            criteria.add(Restrictions.eq(STATUS, status));
        }

        if (expertCode != null && !("").equals(expertCode)) {
            final DetachedCriteria subQuery = DetachedCriteria.forClass(Course.class);
            subQuery.createAlias(EXPERTS, EXPERTS);
            final Conjunction experts = Restrictions.conjunction();
            experts.add(Restrictions.eq(EXPERTS + "." + CODE, expertCode));
            subQuery.add(experts);
            subQuery.setProjection(Projections.property(CODE));
            criteria.add(Property.forName(COURSE_CODE).in(subQuery));
        }
        return criteria;
    }
}
