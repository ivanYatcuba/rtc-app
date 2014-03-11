package com.springapp.mvc.courses.service.impl;

import com.springapp.mvc.courses.resource.CoursesResource;
import com.springapp.mvc.courses.exception.NullIdException;
import com.springapp.mvc.courses.model.Courses;
import com.springapp.mvc.courses.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service Implementation
 * This's a wrapper for {@link CoursesService}
 *
 * @author Vladislav Pikus
 */
@Service("coursesService")
public class CoursesServiceImpl implements CoursesService {

    private static Logger LOG = Logger.getLogger(CoursesServiceImpl.class.getName());

    @Autowired
    public void setResource(CoursesResource resource) {
        this.resource = resource;
    }

    private CoursesResource resource;

    /**
     * @return collection of courses
     * @see CoursesService
     */
    @Override
    public Collection<Courses> findAll() {
        return resource.findAll();
    }

    /**
     * @param id course ID
     * @see CoursesService
     */
    @Override
    public void delete(Integer id) {
        if (id == null) {
            LOG.log(Level.SEVERE, "Exception: ID can't be null");
            throw new NullIdException("ID can't be null");
        }
        resource.delete(id);
    }
}
