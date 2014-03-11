package com.springapp.mvc.courses.resource;

import com.springapp.mvc.courses.model.Courses;

import java.util.Collection;

/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link Courses} objects
 *
 * @author Vladislav Pikus
 */
public interface CoursesResource {
    Courses findById(Integer id);

    /**
     * Find collection of courses
     *
     * @return collection of courses
     */
    Collection<Courses> findAll();

    /**
     * Will be delete course by ID
     *
     * @param id course ID
     */
    void delete(Integer id);
}
