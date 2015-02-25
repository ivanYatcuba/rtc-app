package net.github.rtc.app.service.builder;

import net.github.rtc.app.model.dto.user.ExpertOrderDto;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.model.entity.order.UserCourseOrder;

public class ExpertOrderDtoBuilder {

    private ExpertOrderDto orderDto = new ExpertOrderDto();

    public ExpertOrderDtoBuilder buildOrderFields(UserCourseOrder order) {
        orderDto.setOrderCode(order.getCode());
        orderDto.setStatus(order.getStatus());
        orderDto.setOrderDate(order.getRequestDate());

        return this;
    }

    public ExpertOrderDtoBuilder buildCourseFields(Course course) {
        orderDto.setCourseCode(course.getCode());
        orderDto.setCourseName(course.getName());
        orderDto.setCourseStartDate(course.getStartDate());
        orderDto.setCourseEndDate(course.getEndDate());
        orderDto.setCourseCapacity(course.getCapacity());
        return this;
    }

    public ExpertOrderDtoBuilder buildUserFields(User user) {
        orderDto.setUserCode(user.getCode());
        orderDto.setUserName(user.getSurnameName());
        orderDto.setUserPhoto(user.getPhoto());
        return this;
    }

    public ExpertOrderDtoBuilder buildAcceptedOrders(int acceptedOrders) {
        orderDto.setCourseAcceptedOrders(acceptedOrders);
        return this;
    }

    public ExpertOrderDto get() {
        return orderDto;
    }
}
