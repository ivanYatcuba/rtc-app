package com.springapp.mvc.courses.dao.impl;

import com.springapp.mvc.courses.dao.CoursesDao;
import com.springapp.mvc.courses.model.Courses;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.HttpMethod;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * This is DAO class
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

    private static String URL  = "http://146.185.176.193:8079/method/";

    @Override
    public Courses findById(Integer id) {
        return restTemplate.getForObject(URL + "courses/{id}", Courses.class, id);
    }

    /**
     * Find all courses
     *
     * @return
     */
    @Override
    public Collection<Courses> findAll() {
        return Arrays.asList(restTemplate.getForObject(URL + "courses", Courses[].class));
    }
}
