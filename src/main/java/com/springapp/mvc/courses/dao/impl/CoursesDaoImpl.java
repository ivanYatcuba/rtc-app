package com.springapp.mvc.courses.dao.impl;

import com.springapp.mvc.courses.dao.CoursesDao;
import com.springapp.mvc.courses.model.Courses;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Repository;

import javax.ws.rs.core.MediaType;

/**
 * This is DAO class
 *
 * @author Vladislav Pikus
 */
//todo May be, need make as component
@Repository("coursesDao")
public class CoursesDaoImpl implements CoursesDao {

    private WebResource service = Client.create().resource("http://146.185.176.193:8079/method/");

    public void setService(WebResource service) {
        this.service = service;
    }

    @Override
    public Courses findById(Integer id) {
        String str = String.format("courses/%s", id);
        return service.path(str).get(Courses.class);
    }
}
