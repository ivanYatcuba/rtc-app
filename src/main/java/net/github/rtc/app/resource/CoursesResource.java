package net.github.rtc.app.resource;

import net.github.rtc.app.model.CourseDto;
import net.github.rtc.app.model.Course;

/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link net.github.rtc.app.model.Course} objects
 *
 * @author Vladislav Pikus
 */
public interface CoursesResource {

    /**
     * Find a course object by code
     *
     * @param code course code
     * @return course object or null
     */
    Course findByCode(String code);

    /**
     * Delete existing course by code
     *
     * @param code course code
     */
    void delete(String code);

    /**
     * Create a new course object
     *
     * @param course new course object
     * @return course with updated fields
     */
    Course create(Course course);

    /**
     * Update a course object
     *
     * @param course course object for update
     */
    void update(Course course);

    /**
     * Find course collection by filtering param
     *
     * @param query filter query
     * @return courseDto
     */
    CourseDto findByFilter(String query);
}
