/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.app.service.impl;

//import net.github.rtc.app.service.impl.*;
import net.github.rtc.app.exception.ServiceProcessingException;
import net.github.rtc.app.model.User;
import net.github.rtc.app.resource.UserResource;
import net.github.rtc.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 *
 * @author Саша
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    private static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    @Autowired
    public void setResource(UserResource resource) {
        this.resource = resource;
    }

    private UserResource resource;

    /**
     * @return collection of courses
     * @see UserService
     */
    @Override
    public Collection<User> findAll() {
        return resource.findAll();
    }

    /**
     * @param code course ID
     * @see UserService
     */
    @Override
    public void delete(String code) {
        if (code == null) {
            RuntimeException ex = new ServiceProcessingException("ID can't be null");
            LOG.error("Exception: ", ex);
            throw ex;
        }
        resource.delete(code);
    }

    /**
     * @return link of courses
     * @see UserService
     */
    @Override
    public User findByCode(String code){
        return resource.findById(code);
    }

    @Override
    public User create(User user) {
        return resource.create(user);
    }

    @Override
    public void update(User user) {
        resource.update(user);
    }
}
