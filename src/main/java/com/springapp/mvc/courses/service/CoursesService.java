package com.springapp.mvc.courses.service;

import com.springapp.mvc.courses.model.Courses;

import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
public interface CoursesService {
    /**
     * Find all courses
     *
     * @return
     */
    Collection<Courses> findAll();
}
