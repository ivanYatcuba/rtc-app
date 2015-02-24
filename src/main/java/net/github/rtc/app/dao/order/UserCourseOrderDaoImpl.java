package net.github.rtc.app.dao.order;

import net.github.rtc.app.dao.generic.AbstractGenericDaoImpl;
import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.model.entity.order.UserRequestStatus;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCourseOrderDaoImpl extends AbstractGenericDaoImpl<UserCourseOrder>
  implements UserCourseOrderDao {

    private static final String STATUS = "status";
    private static final String USER_CODE = "userCode";
    private static final String COURSE_CODE = "courseCode";

    @Override
    public UserCourseOrder getUserOrder(final String userCode) {
        return (UserCourseOrder) getCurrentSession().createCriteria(UserCourseOrder.class).add(Restrictions.eq(USER_CODE, userCode)).uniqueResult();
    }

    @Override
    public List<UserCourseOrder> getUserOrdersByCode(String userCode) {
        return getCurrentSession().createCriteria(UserCourseOrder.class).add(Restrictions.eq(USER_CODE, userCode)).list();
    }

    @Override
    public List<UserCourseOrder> getOrderByStatus(final UserRequestStatus status) {
        return getCurrentSession().createCriteria(UserCourseOrder.class).add(Restrictions.eq(STATUS, status)).list();
    }

    @Override
    public int getAcceptedOrdersCourseCount(String courseCode) {
        return ((Long) getCurrentSession().createCriteria(UserCourseOrder.class).
                add(Restrictions.eq(STATUS, UserRequestStatus.ACCEPTED)).
                add(Restrictions.eq("courseCode", courseCode)).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public UserCourseOrder getUserCourseOrder(String userCode, String courseCode) {
        return (UserCourseOrder)getCurrentSession().createCriteria(UserCourseOrder.class).add(Restrictions.eq(USER_CODE, userCode)).
                add(Restrictions.eq(COURSE_CODE, courseCode)).uniqueResult();
    }
}
