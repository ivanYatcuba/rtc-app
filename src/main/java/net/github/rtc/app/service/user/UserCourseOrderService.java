package net.github.rtc.app.service.user;

import net.github.rtc.app.model.dto.user.ExpertOrderDTO;
import net.github.rtc.app.model.entity.user.UserCourseOrder;
import net.github.rtc.app.model.entity.user.UserRequestStatus;
import net.github.rtc.app.service.GenericService;

import java.util.List;

public interface UserCourseOrderService extends GenericService<UserCourseOrder> {

    UserCourseOrder getUserOrderByUserCode(String userCode);

    List<UserCourseOrder> getOrderByStatus(UserRequestStatus status);

    List<ExpertOrderDTO> getOrderByExpertCode(String expertCode);

    int getAcceptedOrdersForCourse(String courseCode);

    boolean changeOrderStatus(final UserRequestStatus userRequestStatus, final String orderCode);

}
