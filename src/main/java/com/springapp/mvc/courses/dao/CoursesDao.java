package com.springapp.mvc.courses.dao;

import com.springapp.mvc.courses.model.Courses;

import java.util.Collection;

/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link Courses} objects
 *
 * @author Vladislav Pikus
 */
public interface CoursesDao {
    Courses findById(Integer id);

    /**
     * Find collection of objects
     *
     * @return collection of objects
     */
    Collection<Courses> findAll();

    /**
     * Will be delete course object by ID
     *
     * @param id course ID
     */
    void delete(Integer id);
}
