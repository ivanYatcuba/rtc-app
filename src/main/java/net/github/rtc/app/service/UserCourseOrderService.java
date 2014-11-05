package net.github.rtc.app.service;

import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.model.user.UserRequestStatus;

import java.util.List;

public interface UserCourseOrderService {

    UserCourseOrder create(UserCourseOrder request);

    UserCourseOrder findByCode(String code);

    List<UserCourseOrder> findAll();

    void update(UserCourseOrder request);

    void deleteByCode(String code);

    UserCourseOrder getUserOrderByUserCode(String userCode);

    List<UserCourseOrder> getOrderByStatus(UserRequestStatus status);

}
