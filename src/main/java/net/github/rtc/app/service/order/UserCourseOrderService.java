package net.github.rtc.app.service.order;

import net.github.rtc.app.model.dto.user.ExpertOrderDTO;
import net.github.rtc.app.model.entity.user.UserCourseOrder;
import net.github.rtc.app.model.entity.user.UserRequestStatus;
import net.github.rtc.app.service.generic.GenericService;
import net.github.rtc.app.utils.datatable.search.OrderSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;

import java.util.List;

public interface UserCourseOrderService extends GenericService<UserCourseOrder> {

    UserCourseOrder getUserOrderByUserCode(String userCode);

    List<UserCourseOrder> getOrderByStatus(UserRequestStatus status);

   SearchResults<ExpertOrderDTO> searchOrderForExpert(OrderSearchFilter searchFilter);

    int getAcceptedOrdersForCourse(String courseCode);

    boolean changeOrderStatus(final UserRequestStatus userRequestStatus, final String orderCode);

}
