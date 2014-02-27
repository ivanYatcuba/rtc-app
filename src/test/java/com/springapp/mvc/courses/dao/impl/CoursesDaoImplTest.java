package com.springapp.mvc.courses.dao.impl;

import com.springapp.mvc.courses.dao.CoursesDao;
import com.springapp.mvc.courses.model.Courses;
import org.junit.Test;

/**
 * @author Vladislav Pikus
 */
//todo Make with spring-test
public class CoursesDaoImplTest {

    //todo Make autowired
    private CoursesDao dao = new CoursesDaoImpl();

    //todo This is only example
    @Test
    public void testFindById() throws Exception {
        Courses courses = dao.findById(5);
        System.out.print(courses.toString());
    }
}
