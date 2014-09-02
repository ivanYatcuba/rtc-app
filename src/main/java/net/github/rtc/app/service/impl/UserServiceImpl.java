/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.UserDao;
import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.ModelService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.search.SearchCriteria;
import net.github.rtc.app.utils.search.SearchResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Саша
 */
@Service("userService")
public class UserServiceImpl implements ModelService<User>, UserService{


    @Autowired
    private UserDao resource;

    private static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class.getName());


    @Override
    @Transactional
    public List<User> findAll() {
        LOG.info("Loading all users from database...");
        return (List)resource.findAll();
    }


    @Override
    @Transactional
    public void delete(User user) {
        LOG.info("Removing user  with email: " + user.getEmail());
        resource.deleteByCode(user.getCode());
    }

    @Override
    @Transactional
    public void deleteByCode(String code) {
        LOG.info("Removing user  with code: " + code);
        resource.deleteByCode(code);
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
        user.setCode(UUID.randomUUID().toString());
        PasswordEncoder encoder = new StandardPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return resource.create(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        LOG.info("Updating user: " + user);
        PasswordEncoder encoder = new StandardPasswordEncoder();
        User userToUpdate = resource.findByCode(user.getCode());
        if(user.getPassword() != userToUpdate.getPassword()){
            user.setPassword(encoder.encode(user.getPassword()));
        }
        resource.update(user);
    }

    @Override
    public Class<User> getType() {
        return User.class;
    }

    @Override
    @Transactional
    public void createRole(RoleType type) {
        LOG.info("Creating user role with type: " + type);
        resource.createRole(type);
    }

    @Override
    @Transactional
    public Role getRoleByType(RoleType type) {
        LOG.info("Getting user role with type: " + type);
        return resource.getRoleByType(type);
    }

    @Override
    @Transactional
    public List<User> getUserByRole(RoleType type) {
        LOG.info("Getting user list with type: " + type);
        return resource.getUserByType(type);
    }

    @Override
    @Transactional
    public SearchResults<User> search(SearchCriteria<User> userSearchCriteria) {
        return resource.search(userSearchCriteria);
    }
}
