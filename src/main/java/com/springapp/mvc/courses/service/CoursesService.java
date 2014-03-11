package com.springapp.mvc.courses.service;

import com.springapp.mvc.courses.model.Courses;

import java.util.Collection;

/**
 * Service Interface
 * Provides operations with {@link Courses} objects
 *
 * @author Vladislav Pikus
 */
public interface CoursesService {
    /**
     * Find collection of courses
     *
     * @return collection of courses
     */
    Collection<Courses> findAll();

    /**
     * Will be delete course by ID
     * If ID is null then will be throw {@link com.springapp.mvc.courses.exception.NullIdException}
     *
     * @param id course ID
     */
    void delete(Integer id);
}
