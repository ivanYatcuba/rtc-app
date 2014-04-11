package net.github.rtc.app.resource;

import net.github.rtc.app.model.User;
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

    User findByEmail(String email);
}
