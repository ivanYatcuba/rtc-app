package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.UserRequest;
import net.github.rtc.app.resource.UserRequestResource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
@Repository
public class UserRequestResourceImpl implements UserRequestResource {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void insert(UserRequest request) {
        sessionFactory.getCurrentSession().save(request);
    }

    @Override
    public UserRequest findByID(long id) {
        return (UserRequest)sessionFactory.getCurrentSession().get(UserRequest.class, (long) id);
    }

    @Override
    public List<UserRequest> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(UserRequest.class).list();
    }

    @Override
    public void delete(UserRequest request) {
        sessionFactory.getCurrentSession().delete(request);
    }

    @Override
    public void update(UserRequest request) {
        sessionFactory.getCurrentSession().update(request);
    }
}
