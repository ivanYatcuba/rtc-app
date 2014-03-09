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
     * Find collection of objects
     *
     * @return collection of objects
     */
    Collection<Courses> findAll();
}
