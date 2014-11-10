/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.app.service;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends ModelService<User>, UserDetailsService, GenericService<User> {

    void createRole(RoleType type);

    Role getRoleByType(RoleType type);

    List<User> getUserByRole(RoleType type);

    User loadUserByUsername(String email);

    void markUserForRemoval(String userCode);

    void restoreUser(String userCode);

    void activateUser(String userCode);

    void inactivateUser(String userCode);

    void deleteUsersMarkedForRemoval();
}
