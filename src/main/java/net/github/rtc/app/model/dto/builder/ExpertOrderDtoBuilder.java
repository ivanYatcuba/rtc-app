package net.github.rtc.app.model.dto.builder;

import net.github.rtc.app.model.dto.user.ExpertOrderDTO;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.model.entity.user.UserCourseOrder;

public class ExpertOrderDtoBuilder {

    private UserCourseOrder order;
    private Course course;
    private User user;
    private int acceptedOrders;

    public ExpertOrderDtoBuilder setOrder(UserCourseOrder order) {
        this.order = order;
        return this;
    }

    public ExpertOrderDtoBuilder setCourse(Course course) {
        this.course = course;
        return this;
    }

    public ExpertOrderDtoBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public ExpertOrderDtoBuilder setAcceptedOrders(int acceptedOrders) {
        this.acceptedOrders = acceptedOrders;
        return this;
    }

    public ExpertOrderDTO build() {
        if (order == null || course == null || user == null) {
            throw new NullPointerException();
        }
        final ExpertOrderDTO orderDTO = new ExpertOrderDTO();
        orderDTO.setCourseCode(course.getCode());
        orderDTO.setCourseName(course.getName());
        orderDTO.setCourseStartDate(course.getStartDate());
        orderDTO.setCourseEndDate(course.getEndDate());
        orderDTO.setCourseCapacity(course.getCapacity());
        orderDTO.setCourseAcceptedOrders(acceptedOrders);

        orderDTO.setUserCode(user.getCode());
        orderDTO.setUserName(user.getFullName());
        orderDTO.setUserPhoto(user.getPhoto());

        orderDTO.setOrderCode(order.getCode());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setOrderDate(order.getRequestDate());

        return orderDTO;
    }
}
