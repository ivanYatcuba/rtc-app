/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.app.service.impl;

import net.github.rtc.app.model.User;
import net.github.rtc.app.resource.UserResource;
import net.github.rtc.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 *
 * @author Саша
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    //private static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserResource resource;

    @Override
    @Transactional
    public Collection<User> findAll() {
        return resource.findAll();
    }


    @Override
    @Transactional
    public void delete(User user) {
        resource.delete(user);
    }

    @Override
    @Transactional
    public void deleteByCode(String code) {
        resource.deleteByСode(code);
    }

    @Override
    @Transactional
    public User findByCode(String code){
        return resource.findByCode(code);
    }

    @Override
    @Transactional
    public User create(User user) {
        return resource.create(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        PasswordEncoder encoder = new StandardPasswordEncoder();
        User userToUpdate = resource.findByCode(user.getCode());
        user.setId(userToUpdate.getId());
        if(user.getPassword() != userToUpdate.getPassword()){
            user.setPassword(encoder.encode(user.getPassword()));
        }
        resource.update(user);
    }
}
