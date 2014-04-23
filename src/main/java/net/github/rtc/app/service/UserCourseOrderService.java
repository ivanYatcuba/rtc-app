package net.github.rtc.app.service;

import net.github.rtc.app.model.UserCourseOrder;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
public interface UserCourseOrderService {
    void insert(UserCourseOrder request);
    UserCourseOrder getUserRequest(long id);
    List<UserCourseOrder> getAll();
    void update(UserCourseOrder request);
    void delete(UserCourseOrder request);
}
