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
     * Search
     * @param searchFilter object that contains the params of the search
     * @return Search result that contains  DTOs
     * @see net.github.rtc.app.model.dto.user.ExpertOrderDTO
     */
    SearchResults<ExpertOrderDTO> searchOrderForExpert(OrderSearchFilter searchFilter);

    /**
     * Get number of accepted orders for specified course
     * @param courseCode code of the course for what operation will be performed
     * @return number of orders with status ACCEPTED
     */
    int getAcceptedOrdersCount(String courseCode);

    /**
     * Set new status for order
     * @param orderCode code of the order for what operation will be performed
     * @param userRequestStatus new status of the order
     * @return if operation was completed successfully
     */
    boolean changeOrderStatus(final String orderCode, final UserRequestStatus userRequestStatus);


    /**
     * Create order for course on specified position
     * @param courseCode code of the course for what order will be created
     * @param position position for what user wanna be applied on course
     * @return created order
     */
    UserCourseOrder create(String courseCode, CourseType position);

    /**
     * Get order with specified user code and course code
     * @param userCode user code for order
     * @param courseCode course code for order
     * @return order with mentioned params
     */
    UserCourseOrder getUserCourseOrder(String userCode, String courseCode);
}
