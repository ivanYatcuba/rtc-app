package net.github.rtc.app.service;

import net.github.rtc.app.model.UserCourseOrder;
import net.github.rtc.app.model.UserRequestStatus;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
public interface UserCourseOrderService {
    void insert(UserCourseOrder request);
    UserCourseOrder getUserOrder(long id);
    List<UserCourseOrder> getAll();
    void update(UserCourseOrder request);
    void delete(UserCourseOrder request);
    UserCourseOrder getUserOrderByUserCode(String code);
    List<UserCourseOrder> getOrderByStatus(UserRequestStatus status);

}
