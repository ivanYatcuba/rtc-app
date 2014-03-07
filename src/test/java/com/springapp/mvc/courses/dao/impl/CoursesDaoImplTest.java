package com.springapp.mvc.courses.dao.impl;

import com.springapp.mvc.courses.dao.CoursesDao;
import com.springapp.mvc.courses.model.Author;
import com.springapp.mvc.courses.model.Courses;
import com.springapp.mvc.courses.model.Tags;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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
       /// ((CoursesDaoImpl)dao).setRestTemplate(new RestTemplate()) ;
        //Courses courses = ;
       // System.out.print(dao.findAll().toString());
    }
}
