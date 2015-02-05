package net.github.rtc.app.dao;

import net.github.rtc.app.model.entity.user.Role;
import net.github.rtc.app.model.entity.user.RoleType;
import net.github.rtc.app.model.entity.user.User;

import java.util.List;

/**
 * @author Саша
 */
public interface UserDao extends GenericDao<User> {

    /**
     * Find user collection by email param
     *
     * @param email email param
     * @return user with correct email
     */
    User findByEmail(String email);

    Role getRoleByType(RoleType type);

    Role createRole(RoleType type);

    List<User> getUsersByType(RoleType type);

    void deletingUser();

}
