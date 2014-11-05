package net.github.rtc.app.service;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.utils.datatable.search.AbstractSearchCommand;
import net.github.rtc.app.utils.datatable.search.SearchResults;

import java.util.List;

public interface CourseService extends ModelService<Course> {

    /**
     * Delete a course by code
     *
     * @param code course code
     * @throws net.github.rtc.app.exception.ServiceProcessingException if
     *                                                                 code
     *                                                                 is null
     */
    void deleteByCode(String code);

    /**
     * Find a course object by code
     *
     * @param code course code
     * @return course object
     * @throws net.github.rtc.app.exception.ServiceProcessingException if
     *                                                                 code
     *                                                                 is null
     */
    Course findByCode(String code);

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
     * Find all courses
     *
     * @return list of all courses
     */
    List<Course> findAll();

    /**
     * Set course status as published
     *
     * @param course what course?
     */
    void publish(Course course);

    SearchResults<Course> search(AbstractSearchCommand searchCommand);

    List<Course> startingSoonCourses();

}
