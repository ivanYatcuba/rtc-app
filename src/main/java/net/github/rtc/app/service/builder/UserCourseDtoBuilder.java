package net.github.rtc.app.service.builder;

import net.github.rtc.app.model.dto.user.UserCourseDTO;
import net.github.rtc.app.model.entity.course.Course;

public class UserCourseDtoBuilder {

    private UserCourseDTO courseDTO = new UserCourseDTO();

    public UserCourseDtoBuilder(Course course) {
        buildCourseFields(course);
    }

    public UserCourseDtoBuilder buildCourseFields(Course course) {
        courseDTO.setCapacity(course.getCapacity());
        courseDTO.setCode(course.getCode());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setEndDate(course.getEndDate());
        courseDTO.setExperts(course.getExperts());
        courseDTO.setName(course.getName());
        courseDTO.setStartDate(course.getStartDate());
        courseDTO.setStatus(course.getStatus());
        courseDTO.setTypes(course.getTypes());
        return this;
    }

    public UserCourseDtoBuilder buildAcceptedOrdersCount(int acceptedOrdersCount) {
        courseDTO.setAcceptedOrders(acceptedOrdersCount);
        return this;
    }

    public UserCourseDtoBuilder buildCurrentUserAssigned(boolean currentUserAssigned) {
        courseDTO.setCurrentUserAssigned(currentUserAssigned);
        return this;
    }

    public UserCourseDTO build() {
        return courseDTO;
    }

}
