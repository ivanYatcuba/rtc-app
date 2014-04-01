package net.github.rtc.web.user.service;

import net.github.rtc.web.user.model.User;



/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link net.github.rtc.web.courses.model.Courses} objects
 *
 * @author Vladislav Pikus
 */
public interface UserService {


    User findById(Integer id);


}
