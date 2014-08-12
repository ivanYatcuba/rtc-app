package net.github.rtc.app.resource;

import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.model.user.UserRequestStatus;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
public interface UserCourseOrderResource extends GenericResource<UserCourseOrder> {
    UserCourseOrder getUserOrder(String userCode);
    List<UserCourseOrder> getOrderByStatus(UserRequestStatus status);
}
