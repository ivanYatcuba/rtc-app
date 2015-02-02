package net.github.rtc.app.dao.impl;

import net.github.rtc.app.dao.UserCourseOrderDao;
import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.model.user.UserRequestStatus;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCourseOrderDaoImpl extends AbstractGenericDaoImpl<UserCourseOrder>
  implements UserCourseOrderDao {

    private static final String STATUS = "status";

    @Override
    public UserCourseOrder getUserOrder(final String userCode) {
        return (UserCourseOrder) getCurrentSession().createCriteria(UserCourseOrder.class).add(Restrictions.eq("userCode", userCode)).uniqueResult();
    }

    @Override
    public List<UserCourseOrder> getOrderByStatus(final UserRequestStatus status) {
        return getCurrentSession().createCriteria(UserCourseOrder.class).add(Restrictions.eq(STATUS, status)).list();
    }

    @Override
    public List<UserCourseOrder> getOrderByExpert(String expertCode) {
        //todo: better implementation?
        final String query = "from UserCourseOrder ord where ord.courseCode in "
                + "(select course.code from Course course join course.experts experts  where experts.code = '" + expertCode + "')";
        return getCurrentSession().createQuery(query).list();
    }

    @Override
    public int getAcceptedOrdersForCourse(String courseCode) {
        return ((Long) getCurrentSession().createCriteria(UserCourseOrder.class).
                add(Restrictions.eq(STATUS, UserRequestStatus.ACCEPTED)).
                add(Restrictions.eq("courseCode", courseCode)).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
}
