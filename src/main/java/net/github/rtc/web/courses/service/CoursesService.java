package net.github.rtc.web.courses.service;

import net.github.rtc.web.courses.model.Courses;
import net.github.rtc.web.courses.model.CoursesDTO;

//import com.springapp.mvc.User;

/**
 * Service Interface
 * Provides operations with {@link Courses} objects
 *
 * @author Vladislav Pikus
 * @author Dmitry Pritula
 */
public interface CoursesService {

    /**
     * Delete a course by code
     *
     * @param code course code
     * @throws net.github.rtc.web.exception.ServiceProcessingException
     *          if code is null
     */
    void delete(String code);

    /**
     * Find a course object by code
     *
     * @param code course code
     * @return course object
     * @throws net.github.rtc.web.exception.ServiceProcessingException
     *          if code is null
     */
    Courses findByCode(String code);

    /**
     * Create a new course object
     *
     * @param course new course object
     * @return course with updated fields
     */
    Courses create(Courses course);

    /**
     * Update a course object
     *
     * @param course course object for update
     */
    void update(Courses course);

    /**
     * Find course collection by filtering param
     *
     * @param query filter query
     * @return courseDTO
     */
    CoursesDTO findByFilter(String query);

    /**
     * Set course status as published
     * @param course  what course?
     */
    void publish(Courses course);
}
