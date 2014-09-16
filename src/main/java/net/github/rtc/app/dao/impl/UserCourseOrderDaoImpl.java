package net.github.rtc.app.dao.impl;

import net.github.rtc.app.dao.UserCourseOrderDao;
import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.model.user.UserRequestStatus;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
@Repository
public class UserCourseOrderDaoImpl extends GenericDaoImpl<UserCourseOrder> implements UserCourseOrderDao {

    @Override
    public UserCourseOrder getUserOrder(String userCode) {
        return (UserCourseOrder) getCurrentSession().createCriteria(UserCourseOrder.class).
                add(Restrictions.eq("userCode", userCode)).uniqueResult();
    }

    @Override
    public List<UserCourseOrder> getOrderByStatus(UserRequestStatus status) {
        return getCurrentSession().createCriteria(UserCourseOrder.class).
                add(Restrictions.eq("status", status)).list();
    }
}
