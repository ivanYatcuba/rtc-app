/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.app.resource.impl;

//import net.github.rtc.app.impl.*;
import net.github.rtc.app.model.User;
import net.github.rtc.app.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Саша
 */
@Component("userDao")
public class UserResourseImpl implements UserResource{
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String hostUserUrl;

    public void setHostUserUrl(String hostUserUrl) {
        this.hostUserUrl = hostUserUrl;
    }

    public String getHostUserUrl() {
        return hostUserUrl;
    }

    /**
     * @param code course ID
     * @return null if not found or course's object if found
     * @see net.github.rtc.app.resource.CoursesResource
     */
    @Override
    public User findById(String code) {
        User user = null;
        try{
            user = restTemplate.getForObject(hostUserUrl + "{code}", User.class, code);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return user;
       
    }

    /**
     * @return collection of courses
     * @see net.github.rtc.app.resource.CoursesResource
     */
    @Override
    public Collection<User> findAll() {
        return Arrays.asList(restTemplate.getForObject(hostUserUrl + "viewAll", User[].class));
    }
    
    /**
     * @param code user code
     * @see net.github.rtc.app.resource.CoursesResource
     */
    @Override
    public void delete(String code) {
        restTemplate.delete(hostUserUrl + "{code}", code);
    }

    @Override
    public User create(User user) {
        PasswordEncoder encoder = new StandardPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return restTemplate.postForObject(hostUserUrl, user, User.class);
    }

    @Override
    public void update(User user) {
        restTemplate.postForObject(hostUserUrl + "update", user,User.class);
    }

    @Override
    public User findByEmail(String email) {
        return restTemplate.getForObject(hostUserUrl + "login/{email}", User.class, email);
    }
}
