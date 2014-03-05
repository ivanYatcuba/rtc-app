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
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * This is DAO class
 *
 * @author Vladislav Pikus
 */
//todo May be, need make as component
@Repository("coursesDao")
public class CoursesDaoImpl implements CoursesDao {

    public CoursesDaoImpl() {
        ClientConfig cfg = new DefaultClientConfig();
        cfg.getClasses().add(JacksonJsonProvider.class);
        service = Client.create(cfg).resource("http://146.185.176.193:8079/method/");
    }

    private WebResource service;

    public void setService(WebResource service) {
        this.service = service;
    }

    @Override
    public Courses findById(Integer id) {
        return service.path(String.format("courses/%s", id)).get(Courses.class);
    }

    /**
     * Find all courses
     *
     * @return
     */
    @Override
    public Collection<Courses> findAll() {
        GenericType<Collection<Courses>> type = new GenericType<Collection<Courses>>(){};
        ClientResponse clientResponse = service.path("/courses").get(ClientResponse.class);
        return clientResponse.getEntity(type);
    }
}
