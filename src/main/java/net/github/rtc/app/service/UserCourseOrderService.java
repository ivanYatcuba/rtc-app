package net.github.rtc.app.service;

import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.model.user.UserRequestStatus;

import java.util.List;

public interface UserCourseOrderService extends GenericService<UserCourseOrder> {

    UserCourseOrder getUserOrderByUserCode(String userCode);

    List<UserCourseOrder> getOrderByStatus(UserRequestStatus status);

}
