package net.github.rtc.app.dao.order;

import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.model.entity.order.UserRequestStatus;

import java.util.List;

public interface UserCourseOrderDao extends GenericDao<UserCourseOrder> {
    UserCourseOrder getUserOrder(String userCode);

    List<UserCourseOrder> getUserOrdersByCode(String userCode);

    List<UserCourseOrder> getOrderByStatus(UserRequestStatus status);

    int getAcceptedOrdersCourseCount(String courseCode);
}
