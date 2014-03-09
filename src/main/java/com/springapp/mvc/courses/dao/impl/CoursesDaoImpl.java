package com.springapp.mvc.courses.dao.impl;

import com.springapp.mvc.courses.dao.CoursesDao;
import com.springapp.mvc.courses.model.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

/**
 * Data Access Object Implementation
 * All queries are built using {@link RestTemplate}
 *
 * @author Vladislav Pikus
 */
@Repository("coursesDao")
public class CoursesDaoImpl implements CoursesDao {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String hostUrl;

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    /**
     * Find a course's object
     *
     * @param id course ID
     * @return null if not found or course's object if found
     */
    @Override
    public Courses findById(Integer id) {
        return restTemplate.getForObject(hostUrl + "courses/{id}", Courses.class, id);
    }

    /**
     * Find collection of objects
     *
     * @return collection of objects
     */
    @Override
    public Collection<Courses> findAll() {
        return Arrays.asList(restTemplate.getForObject(hostUrl + "courses", Courses[].class));
    }

    /**
     * Will be delete course object by ID
     *
     * @param id course ID
     */
    @Override
    public void delete(Integer id) {
        restTemplate.delete(hostUrl + "courses/{id}", id);
    }
}
