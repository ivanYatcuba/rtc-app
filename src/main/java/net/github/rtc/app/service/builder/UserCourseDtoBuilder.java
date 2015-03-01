package net.github.rtc.app.service.builder;

import net.github.rtc.app.model.dto.user.UserCourseDto;
import net.github.rtc.app.model.entity.course.Course;

/**
 * Object that helps to build UserCourseDto
 * @see net.github.rtc.app.model.dto.user.UserCourseDto
 */
public class UserCourseDtoBuilder {

    private UserCourseDto courseDto = new UserCourseDto();

    /**
     * Instantiates a new builder.
     * @param course the course on what UserCourseDto is based
     */
    public UserCourseDtoBuilder(Course course) {
        buildCourseFields(course);
    }

    /**
     * Initialise course fields in dto
     * @param course the course that contains required fields
     * @return this object
     */
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

    /**
     * Initialise accepted orders count in dto
     * @param acceptedOrdersCount the accepted orders count
     * @return this object
     */
    public UserCourseDtoBuilder buildAcceptedOrdersCount(int acceptedOrdersCount) {
        courseDto.setAcceptedOrders(acceptedOrdersCount);
        return this;
    }

    /**
     * Initialise currentUserAssigned field
     * @param currentUserAssigned  true if current user is assigned for course
     * @return this object
     */
    public UserCourseDtoBuilder buildCurrentUserAssigned(boolean currentUserAssigned) {
        courseDto.setCurrentUserAssigned(currentUserAssigned);
        return this;
    }

    /**
     * Return current prebuilt UserCourseDto object
     * @return the result dto
     */
    public UserCourseDto build() {
        return courseDto;
    }

}
