package net.github.rtc.web.courses.service;

import net.github.rtc.web.courses.model.Courses;
//import com.springapp.mvc.User;

import java.util.Collection;
import java.util.Map;

/**
 * Service Interface
 * Provides operations with {@link Courses} objects
 *
 * @author Vladislav Pikus
 * @author Dmitry Pritula
 */
public interface CoursesService {
    /**
     * Find collection of courses
     *
     * @return collection of courses
     */
    Collection<Courses> findAll();

    /**
     * Delete a course by code
     *
     * @param code course code
     * @throws net.github.rtc.web.courses.exception.ServiceProcessingException
     *          if code is null
     */
    void delete(String code);

    /**
     * Find a course object by code
     *
     * @param code course code
     * @return course object
     * @throws net.github.rtc.web.courses.exception.ServiceProcessingException
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
     * @param filter filter map
     * @return course collection
     */
    Collection<Courses> findByFilter(Map<String, String> filter);
}
