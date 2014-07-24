package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.model.user.UserRequestStatus;
import net.github.rtc.app.resource.UserCourseOrderResource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
@Repository
public class UserCourseOrderResourceImpl implements UserCourseOrderResource {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void insert(UserCourseOrder order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public UserCourseOrder findByID(long id) {
        return (UserCourseOrder)sessionFactory.getCurrentSession().get(UserCourseOrder.class, (long) id);
    }

    @Override
    public List<UserCourseOrder> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(UserCourseOrder.class).list();
    }

    @Override
    public void delete(UserCourseOrder request) {
        sessionFactory.getCurrentSession().delete(request);
    }

    @Override
    public void update(UserCourseOrder request) {
        sessionFactory.getCurrentSession().update(request);
    }

    @Override
    public UserCourseOrder getUserOrder(String code) {
        String query = "select e from UserCourseOrder e where e.userCode = :code";
        return (UserCourseOrder)sessionFactory.getCurrentSession().
                createQuery(query).setString("code", code).uniqueResult();
    }

    @Override
    public List<UserCourseOrder> getOrderByStatus(UserRequestStatus status) {
        String query = "select e from UserCourseOrder e where e.status = :status";
        return sessionFactory.getCurrentSession().
                createQuery(query).setString("status", String.valueOf(status.toString())).list();
    }
}
