/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.app.service.impl;

import net.github.rtc.app.model.user.User;
import net.github.rtc.app.resource.UserResource;
import net.github.rtc.app.service.ModelService;
import net.github.rtc.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Саша
 */
@Service("userService")
public class UserServiceImpl implements ModelService<User>, UserService{


    @Autowired
    private UserResource resource;

    private static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class.getName());


    @Override
    @Transactional
    public List<User> findAll() {
        LOG.info("Loading all users from database...");
        return resource.findAll();
    }


    @Override
    @Transactional
    public void delete(User user) {
        LOG.info("Removing user  with email: " + user.getEmail());
        resource.delete(user);
    }

    @Override
    @Transactional
    public void deleteByCode(String code) {
        LOG.info("Removing user  with code: " + code);
        resource.deleteByСode(code);
    }

    @Override
    @Transactional
    public User findByCode(String code){
        LOG.info("Removing user  with code: " + code);
        return resource.findByCode(code);
    }

    @Override
    @Transactional
    public User create(User user) {
        LOG.info("Creating user: " + user);
        return resource.create(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        LOG.info("Updating user: " + user);
        PasswordEncoder encoder = new StandardPasswordEncoder();
        User userToUpdate = resource.findByCode(user.getCode());
        user.setId(userToUpdate.getId());     //todo: user already must have id
        if(user.getPassword() != userToUpdate.getPassword()){
            user.setPassword(encoder.encode(user.getPassword()));
        }
        resource.update(user);
    }
}
