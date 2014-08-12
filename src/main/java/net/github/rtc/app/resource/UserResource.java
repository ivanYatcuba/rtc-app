package net.github.rtc.app.resource;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;

/**
 *
 * @author Саша
 */
public interface UserResource extends GenericResource<User> {

    /**
     * Find user collection by email param
     *
     * @param email email param
     * @return user with correct email
     */
    User findByEmail(String email);

    Role getRoleByType(RoleType type);

    void createRole(RoleType type);
}
