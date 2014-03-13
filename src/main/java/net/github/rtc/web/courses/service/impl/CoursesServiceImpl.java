package net.github.rtc.web.courses.service.impl;

import net.github.rtc.web.courses.exception.ServiceProcessingException;
import net.github.rtc.web.courses.resource.CoursesResource;
import net.github.rtc.web.courses.model.Courses;
import net.github.rtc.web.courses.service.CoursesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Service Implementation
 * This's a wrapper for {@link CoursesService}
 *
 * @author Vladislav Pikus
 * @author Dmitry Pritula
 */
@Service("coursesService")
public class CoursesServiceImpl implements CoursesService {

    private static Logger LOG = LoggerFactory.getLogger(CoursesServiceImpl.class.getName());

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
            RuntimeException ex = new ServiceProcessingException("ID can't be null");
            LOG.error("Exception: ", ex);
            throw ex;
        }
        resource.delete(id);
    }

    /**
     * @return link of courses
     * @see CoursesService
     */
    @Override
    public Courses findById(Integer id){
        return resource.findById(id);
    }

    @Override
    public Courses create(Courses course) {
        return resource.create(course);
    }

    @Override
    public void update(Courses course) {
        resource.update(course);
    }
}
