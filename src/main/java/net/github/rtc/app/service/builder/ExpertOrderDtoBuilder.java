package net.github.rtc.app.service.builder;

import net.github.rtc.app.model.dto.user.ExpertOrderDTO;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.model.entity.order.UserCourseOrder;

public class ExpertOrderDtoBuilder {

    private ExpertOrderDTO orderDTO = new ExpertOrderDTO();

    public ExpertOrderDtoBuilder buildOrderFields(UserCourseOrder order) {
        orderDTO.setOrderCode(order.getCode());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setOrderDate(order.getRequestDate());

        return this;
    }

    public ExpertOrderDtoBuilder buildCourseFields(Course course) {
        orderDTO.setCourseCode(course.getCode());
        orderDTO.setCourseName(course.getName());
        orderDTO.setCourseStartDate(course.getStartDate());
        orderDTO.setCourseEndDate(course.getEndDate());
        orderDTO.setCourseCapacity(course.getCapacity());
        return this;
    }

    public ExpertOrderDtoBuilder buildUserFields(User user) {
        orderDTO.setUserCode(user.getCode());
        orderDTO.setUserName(user.getFullName());
        orderDTO.setUserPhoto(user.getPhoto());
        return this;
    }

    public ExpertOrderDtoBuilder buildAcceptedOrders(int acceptedOrders) {
        orderDTO.setCourseAcceptedOrders(acceptedOrders);
        return this;
    }

    public ExpertOrderDTO get() {
        return orderDTO;
    }
}
