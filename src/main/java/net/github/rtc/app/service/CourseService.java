package net.github.rtc.app.service;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.utils.datatable.CourseSearchResult;
import net.github.rtc.app.utils.datatable.SearchFilter;

import java.util.List;

//import com.springapp.mvc.User;

/**
 * Service Interface
 * Provides operations with {@link net.github.rtc.app.model.course.Course} objects
 *
 * @author Vladislav Pikus
 * @author Dmitry Pritula
 */
public interface CourseService {

    /**
     * Delete a course by code
     *
     * @param code course code
     * @throws net.github.rtc.app.exception.ServiceProcessingException
     *          if code is null
     */
    void delete(String code);

    /**
     * Find a course object by code
     *
     * @param code course code
     * @return course object
     * @throws net.github.rtc.app.exception.ServiceProcessingException
     *          if code is null
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
     * Find course collection by filtering param
     *
     * @param filter filter query
     * @return courseDTO
     */
    CourseSearchResult findByFilter(SearchFilter filter);

    /**
     * Find all courses
     *
     * @return list of all courses
     */
    List<Course> findAll();

    /**
     * Set course status as published
     * @param course  what course?
     */
    void publish(Course course);


}
