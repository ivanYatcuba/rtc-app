package net.github.rtc.app.service.impl;

import net.github.rtc.app.model.Course;
import net.github.rtc.app.model.CourseDto;
import net.github.rtc.app.resource.CoursesResource;
import net.github.rtc.app.service.CoursesService;
import net.github.rtc.app.exception.ServiceProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
     * Check course code for null
     *
     * @param code course code
     * @throws ServiceProcessingException
     */
    private void checkCode(String code) {
        if (code == null) {
            RuntimeException ex = new ServiceProcessingException("Course code can't be null");
            LOG.error("Exception: ", ex);
            throw ex;
        }
    }

    /**
     * @see CoursesService#delete(String)
     */
    @Override
    public void delete(String code) {
        checkCode(code);
        resource.delete(code);
    }

    /**
     * @see CoursesService#findByCode(String)
     */
    @Override
    public Course findByCode(String code) {
        checkCode(code);
        return resource.findByCode(code);
    }

    /**
     * @see CoursesService#create(net.github.rtc.app.model.Course)
     */
    @Override
    public Course create(Course course) {
        return resource.create(course);
    }

    /**
     * @see CoursesService#update(net.github.rtc.app.model.Course)
     */
    @Override
    public void update(Course course) {
        resource.update(course);
    }

    /**
     * @see CoursesService#findByFilter(String)
     */
    @Override
    public CourseDto findByFilter(String query) {
        return resource.findByFilter(query);
    }

    @Override
    public void publish(Course course) {
        course.setStatus("PUBLISHED");
        course.setPublishDate(new Date());
        resource.update(course);
    }
}
