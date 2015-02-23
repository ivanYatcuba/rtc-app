package net.github.rtc.app.service.course;

import net.github.rtc.app.model.dto.user.UserCourseDTO;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.service.generic.GenericService;
import net.github.rtc.app.service.generic.ModelService;
import net.github.rtc.app.utils.datatable.search.filter.CourseSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;

/**
 * The service class that is responsible for operation on Course model class
 * @see net.github.rtc.app.model.entity.course.Course
 */
public interface CourseService extends ModelService<Course>, GenericService<Course> {

    /**
     * Publish course and possibly create news with it
     * @param courseCode
     * @param isNewsCreated flag that determines if news needs to be created
     */
    void publish(String courseCode, final boolean isNewsCreated);

    /**
     * Set course status to archived and update
     * @param courseCode
     */
    void archive(String courseCode);

    /**
     * Search courses for user and present it as DTO
     * @param filter
     * @return
     */
    SearchResults<UserCourseDTO> searchCoursesForUser(CourseSearchFilter filter);

    /**
     * Get a single course for user and present it as DTO
     * @param code
     * @return
     */
    UserCourseDTO getUserCourseDTObyCode(String code);

    /**
     * Create course and possibly publish and create news about it
     * @param course
     * @param isPublished flag that determines if news needs to be published
     * @param newsCreated flag that determines if news needs to be created
     */
    void create(Course course, final boolean isPublished, final boolean newsCreated);

    /**
     * Update course and possibly publish and create news about it
     * @param course
     * @param isPublished flag that determines if news needs to be published
     * @param newsCreated flag that determines if news needs to be created
     */
    void update(Course course, final boolean isPublished, final boolean newsCreated);

    /**
     * Add user as a participant of a course
     * @param courseCode
     * @param userCode
     */
    void addParticipant(String courseCode, String userCode);

    /**
     * Remove user from course participants
     * @param courseCode
     * @param userCode
     */
    void deleteParticipant(String courseCode, String userCode);
}
