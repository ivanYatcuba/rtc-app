/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.User;
import net.github.rtc.app.model.UserCourseOrder;
import net.github.rtc.app.resource.UserResource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

/**
 *
 * @author Саша
 */
@Repository
public class UserResourseImpl implements UserResource{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User findByCode(String code) {
        User user = null;
        try{
            String query = "select user from User user where user.code = :code";
            return (User)sessionFactory.getCurrentSession().
                    createQuery(query).setString("code", code).uniqueResult();

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return user;
       
    }

    @Override
    public Collection<User> findAll() {
        return  sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }


    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public User create(User user) {
        PasswordEncoder encoder = new StandardPasswordEncoder();
        user.setCode(UUID.randomUUID().toString());
        user.setPassword(encoder.encode(user.getPassword()));
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    public User findByEmail(String email) {
        String query = "select user from User user where user.email = :email";
        return (User)sessionFactory.getCurrentSession().
                createQuery(query).setString("email", email).uniqueResult();
    }

    @Override
    public void deleteByСode(String code) {
        String query = "select user from User user where user.code = :code";
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().
                createQuery(query).setString("code", code).uniqueResult());
    }
}
