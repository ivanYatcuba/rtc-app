package net.github.rtc.app.service.order;

import net.github.rtc.app.model.dto.user.ExpertOrderDTO;
import net.github.rtc.app.model.entity.course.CourseType;
import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.model.entity.order.UserRequestStatus;
import net.github.rtc.app.service.generic.GenericService;
import net.github.rtc.app.model.dto.filter.OrderSearchFilter;
import net.github.rtc.app.model.dto.SearchResults;

import java.util.List;

/**
 * The service class that is responsible for operation on UserCourseOrder model class
 * @see net.github.rtc.app.model.entity.order.UserCourseOrder
 */
public interface UserCourseOrderService extends GenericService<UserCourseOrder> {

    /**
     * Get a single order for specified user
     * @param userCode
     * @return
     */
    UserCourseOrder getUserOrderByUserCode(String userCode);

    /**
     * Get all orders for specified user
     * @param userCode
     * @return
     */
    List<UserCourseOrder> getUserOrdersByCode(String userCode);

    /**
     * Search
     * @param searchFilter object that contains the params of the search
     * @return Search result that contains  DTOs
     * @see net.github.rtc.app.model.dto.user.ExpertOrderDTO
     */
    SearchResults<ExpertOrderDTO> searchOrderForExpert(OrderSearchFilter searchFilter);

    /**
     * Get number of accepted orders for specified course
     * @param courseCode
     * @return number of orders with status ACCEPTED
     */
    int getAcceptedOrdersCount(String courseCode);

    /**
     * Set new status for order
     * @param orderCode
     * @param userRequestStatus
     * @return if operation was completed successfully
     */
    boolean changeOrderStatus(final String orderCode, final UserRequestStatus userRequestStatus);


    /**
     * Create order for course on specified position
     * @param courseCode
     * @param position
     * @return
     */
    UserCourseOrder create(String courseCode, CourseType position);
}
