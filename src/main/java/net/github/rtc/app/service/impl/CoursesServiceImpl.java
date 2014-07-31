package net.github.rtc.app.service.impl;

import net.github.rtc.app.model.course.*;
import net.github.rtc.app.resource.CoursesResource;
import net.github.rtc.app.service.CoursesService;
import net.github.rtc.app.exception.ServiceProcessingException;
import net.github.rtc.app.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Service Implementation
 * This's a wrapper for {@link CoursesService}
 *
 * @author Vladislav Pikus
 * @author Dmitry Pritula
 */
@Service("coursesService")
public class CoursesServiceImpl implements ModelService<Course>, CoursesService {

    private static Logger LOG = LoggerFactory.getLogger(CoursesServiceImpl.class.getName());

    @Autowired
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
    @Transactional
    public void delete(String code) {
        checkCode(code);
        resource.delete(code);
    }

    /**
     * @see CoursesService#findByCode(String)
     */
    @Override
    @Transactional
    public Course findByCode(String code) {
        checkCode(code);
        return resource.findByCode(code);
    }

    /**
     * @see CoursesService#create(net.github.rtc.app.model.course.Course)
     */
    @Override
    @Transactional
    public Course create(Course course) {
        return resource.create(course);
    }

    /**
     * @see CoursesService#update(net.github.rtc.app.model.course.Course)
     */
    @Override
    @Transactional
    public void update(Course course) {
        Course courseToUpdate = resource.findByCode(course.getCode());
        course.setId(courseToUpdate.getId());  //todo: course already must have id at this point
        resource.update(course);
    }

    /**
     * @see CoursesService#( net.github.rtc.app.model.course.SearchFilter )
     */
    @Override
    @Transactional
    public CourseSearchResult findByFilter(SearchFilter filter) {
        Integer total = resource.getCount(filter);
        PageDto pageDto = new PageDto.Builder(total).page(filter.getPageNumber()).maxResult(filter.getMaxResult())
                .build();
        CourseSearchResult result = new CourseSearchResult.Builder().courses(resource.findByCriteria(filter, pageDto))
                .totalCount(total)
                .limit(pageDto.getMaxResult())
                .offset(pageDto.getFirstResult()).build();
        return result;
    }

    @Override
    @Transactional
    public List<Course> findAll() {
        return resource.findAll();
    }

    @Override
    @Transactional
    public void publish(Course course) {
        course.setStatus("PUBLISHED");
        course.setPublishDate(new Date());
        resource.update(course);
    }
}
