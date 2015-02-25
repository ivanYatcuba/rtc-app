package net.github.rtc.app.service.builder;

import net.github.rtc.app.model.dto.user.UserCourseDto;
import net.github.rtc.app.model.entity.course.Course;

public class UserCourseDtoBuilder {

    private UserCourseDto courseDto = new UserCourseDto();

    public UserCourseDtoBuilder(Course course) {
        buildCourseFields(course);
    }

    public UserCourseDtoBuilder buildCourseFields(Course course) {
        courseDto.setCapacity(course.getCapacity());
        courseDto.setCode(course.getCode());
        courseDto.setDescription(course.getDescription());
        courseDto.setEndDate(course.getEndDate());
        courseDto.setExperts(course.getExperts());
        courseDto.setName(course.getName());
        courseDto.setStartDate(course.getStartDate());
        courseDto.setStatus(course.getStatus());
        courseDto.setTypes(course.getTypes());
        return this;
    }

    public UserCourseDtoBuilder buildAcceptedOrdersCount(int acceptedOrdersCount) {
        courseDto.setAcceptedOrders(acceptedOrdersCount);
        return this;
    }

    public UserCourseDtoBuilder buildCurrentUserAssigned(boolean currentUserAssigned) {
        courseDto.setCurrentUserAssigned(currentUserAssigned);
        return this;
    }

    public UserCourseDto build() {
        return courseDto;
    }

}
