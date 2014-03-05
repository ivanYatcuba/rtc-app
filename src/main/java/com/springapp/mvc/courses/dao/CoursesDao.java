package com.springapp.mvc.courses.dao;

import com.springapp.mvc.courses.model.Courses;

import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
public interface CoursesDao {
    Courses findById(Integer id);

    /**
     * Find all courses
     *
     * @return
     */
    Collection<Courses> findAll();
}
