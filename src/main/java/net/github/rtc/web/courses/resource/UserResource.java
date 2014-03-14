/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.web.courses.resource;

import com.springapp.mvc.User;
import java.util.Collection;

/**
 *
 * @author Саша
 */
public interface UserResource {
    User findById(Integer id);

    /**
     * Find collection of courses
     *
     * @return collection of courses
     */
    Collection<User> findAll();

    /**
     * Will be delete course by ID
     *
     * @param id course ID
     */
    void delete(Integer id);

    User create(User user);

    void update(User user);
}
