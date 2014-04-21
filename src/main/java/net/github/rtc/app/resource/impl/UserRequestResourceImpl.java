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
    public UserRequest findByID(long id) {
        return null;
    }

    @Override
    public List<UserRequest> getAll() {
        return null;
    }

    @Override
    public void delete(UserRequest request) {

    }

    @Override
    public void update(UserRequest request) {

    }
}
