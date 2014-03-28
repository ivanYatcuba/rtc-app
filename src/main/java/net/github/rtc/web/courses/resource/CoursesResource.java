package net.github.rtc.web.courses.resource;

import net.github.rtc.web.courses.model.Courses;

import java.util.Collection;
import java.util.Map;

/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link Courses} objects
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
    Courses findByCode(String code);

    /**
     * Find collection of courses
     *
     * @return collection of courses
     */
    Collection<Courses> findAll();

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

    int getCount();
}
