package net.github.rtc.app.resource;

import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.model.user.UserRequestStatus;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
public interface UserCourseOrderResource {
    void insert(UserCourseOrder request);
    UserCourseOrder findByID(long id);
    List<UserCourseOrder> getAll();
    void delete(UserCourseOrder request);
    void update(UserCourseOrder request);
    UserCourseOrder getUserOrder(String code);
    List<UserCourseOrder> getOrderByStatus(UserRequestStatus status);
}
