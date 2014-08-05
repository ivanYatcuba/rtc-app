/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.user.User;
import net.github.rtc.app.resource.UserResource;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        //todo: remove try-catch
        User user = null;
        try{
            //todo: useless variable
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
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
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
        //todo: useless variable
        String query = "select user from User user where user.email = :email";
        return (User)sessionFactory.getCurrentSession().
                createQuery(query).setString("email", email).uniqueResult();
    }

    @Override
    public void deleteByСode(String code) {
        //todo: useless variable
        String query = "select user from User user where user.code = :code";
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().
                createQuery(query).setString("code", code).uniqueResult());
    }
}
