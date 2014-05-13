package net.github.rtc.app.resource;

import net.github.rtc.app.model.User;
import java.util.Collection;

/**
 *
 * @author Саша
 */
public interface UserResource {
    User findById(String code);

    /**
     * Find collection of courses
     *
     * @return collection of courses
     */
    Collection<User> findAll();

    /**
     * Will be delete course by ID
     *
     * @param code course ID
     */
    void delete(String code);

    User create(User user);

    void update(User user);

    User findByEmail(String email);
}
