package net.github.rtc.app.model.dto.builder;

import net.github.rtc.app.model.dto.user.UserCourseDTO;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.service.order.UserCourseOrderService;
import net.github.rtc.app.utils.AuthorizedUserProvider;
import org.springframework.beans.factory.annotation.Autowired;

public class UserCourseDtoBuilder {

    @Autowired
    private UserCourseOrderService userCourseOrderService;

    private Course course;
    private int acceptedOrdersCount;
    private boolean currentUserAssigned;

    public UserCourseDtoBuilder setCourse(Course course) {
        this.course = course;
        return this;
    }

    public UserCourseDtoBuilder setAcceptedOrdersCount(int acceptedOrdersCount) {
        this.acceptedOrdersCount = acceptedOrdersCount;
        return this;
    }

    public UserCourseDtoBuilder setCurrentUserAssigned(boolean currentUserAssigned) {
        this.currentUserAssigned = currentUserAssigned;
        return this;
    }

    public UserCourseDTO build() {
        final UserCourseDTO courseDTO = new UserCourseDTO();
        courseDTO.setAcceptedOrders(acceptedOrdersCount);
        courseDTO.setCapacity(course.getCapacity());
        courseDTO.setCode(course.getCode());
        courseDTO.setCurrentUserAssigned(currentUserAssigned);
        courseDTO.setDescription(course.getDescription());
        courseDTO.setEndDate(course.getEndDate());
        courseDTO.setExperts(course.getExperts());
        courseDTO.setName(course.getName());
        courseDTO.setStartDate(course.getStartDate());
        courseDTO.setStatus(course.getStatus());
        courseDTO.setTypes(course.getTypes());
        return courseDTO;
    }

}