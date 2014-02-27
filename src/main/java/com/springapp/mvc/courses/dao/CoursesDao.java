package com.springapp.mvc.courses.dao;

import com.springapp.mvc.courses.model.Courses;

/**
 * @author Vladislav Pikus
 */
public interface CoursesDao {
    Courses findById(Integer id);
}
