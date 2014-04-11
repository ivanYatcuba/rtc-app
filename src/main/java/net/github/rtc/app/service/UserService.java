/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.app.service;

import net.github.rtc.app.model.User;
import java.util.Collection;

/**
 *
 * @author Саша
 */
public interface UserService {

    Collection<User> findAll();

    /**
     * Will be delete course by ID
     * If ID is null then will be throw {@link net.github.rtc.app.exception.ServiceProcessingException}
     *
     * @param id course ID
     */
    void delete(Integer id);

    User findById(Integer id);

    User create(User user);

    void update(User user);
}