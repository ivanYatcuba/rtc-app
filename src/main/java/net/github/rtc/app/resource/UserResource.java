package net.github.rtc.app.resource;

import net.github.rtc.app.model.User;
import java.util.Collection;

/**
 *
 * @author Саша
 */
public interface UserResource {
    User findByCode(String code);

    /**
     * Find collection of users
     *
     * @return collection of users
     */
    Collection<User> findAll();

    /**
     * Will be delete user by code
     *
     * @param user user to delete
     */
    void delete(User user);

    /**
     * Create a new user object
     *
     * @param user new user object
     * @return user with updated fields
     */
    User create(User user);

    /**
     * Update a course object
     *
     * @param user user object for update
     */
    void update(User user);

    /**
     * Find user collection by email param
     *
     * @param email email param
     * @return user with correct email
     */
    User findByEmail(String email);

    /**
     * Delete user by code
     *
     * @param code user`s code param
     */
    void deleteByСode(String code);
}
