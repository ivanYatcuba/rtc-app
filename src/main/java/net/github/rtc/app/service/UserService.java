/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.app.service;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @author Саша
 */
public interface UserService {

    List<User> findAll();

    void delete(User user);

    void deleteByCode(String code);

    User findByCode(String code);

    User create(User user);

    void update(User user);

    void createRole(RoleType type);

    Role getRoleByType(RoleType type);

    List<User> getUserByRole(RoleType type);

    SearchResults<User> search(DetachedCriteria criteria, int start, int max);

    User loadUserByUsername(String email);

    void setUserStatusForRemoval(String userCode);

    void setUserStatusActive(String userCode);
}
