package com.springapp.mvc.courses.resource.impl;

import com.springapp.mvc.courses.resource.CoursesResource;
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
public class CoursesResourceImpl implements CoursesResource {

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
     * @param id course ID
     * @return null if not found or course's object if found
     * @see com.springapp.mvc.courses.resource.CoursesResource
     */
    @Override
    public Courses findById(Integer id) {
        return restTemplate.getForObject(hostUrl + "courses/{id}", Courses.class, id);
    }

    /**
     * @return collection of courses
     * @see com.springapp.mvc.courses.resource.CoursesResource
     */
    @Override
    public Collection<Courses> findAll() {
        return Arrays.asList(restTemplate.getForObject(hostUrl + "courses", Courses[].class));
    }

    /**
     * @param id course ID
     * @see com.springapp.mvc.courses.resource.CoursesResource
     */
    @Override
    public void delete(Integer id) {
        restTemplate.delete(hostUrl + "courses/{id}", id);
    }
}
