package net.github.rtc.app.dao.impl;

import net.github.rtc.app.dao.UserCourseOrderDao;
import net.github.rtc.app.model.entity.user.UserCourseOrder;
import net.github.rtc.app.model.entity.user.UserRequestStatus;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCourseOrderDaoImpl extends AbstractGenericDaoImpl<UserCourseOrder>
  implements UserCourseOrderDao {

    private static final String STATUS = "status";
    private static final String USER_CODE = "userCode";

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
    public int getAcceptedOrdersForCourse(String courseCode) {
        return ((Long) getCurrentSession().createCriteria(UserCourseOrder.class).
                add(Restrictions.eq(STATUS, UserRequestStatus.ACCEPTED)).
                add(Restrictions.eq("courseCode", courseCode)).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
}
