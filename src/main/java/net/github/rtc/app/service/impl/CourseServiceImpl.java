package net.github.rtc.app.service.impl;

import net.github.rtc.app.model.course.*;
import net.github.rtc.app.resource.CoursesResource;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.exception.ServiceProcessingException;
import net.github.rtc.app.service.ModelService;
import net.github.rtc.app.utils.datatable.CourseSearchResult;
import net.github.rtc.app.utils.datatable.PageDto;
import net.github.rtc.app.utils.datatable.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Service Implementation
 * This's a wrapper for {@link net.github.rtc.app.service.CourseService}
 *
 * @author Vladislav Pikus
 * @author Dmitry Pritula
 */
@Service("coursesService")
public class CourseServiceImpl implements ModelService<Course>, CourseService {
    private static Logger LOG = LoggerFactory.getLogger(CourseServiceImpl.class.getName());

    @Autowired
    private CoursesResource resource;


    /**
     * Check course code for null
     *
     * @param code course code
     * @throws ServiceProcessingException
     */
    private void checkCode(String code) {
        LOG.info("Checking if course with code " + code + " exists");
        if (code == null) {
            RuntimeException ex = new ServiceProcessingException("Course code can't be null");
            LOG.error("Exception: ", ex);
            throw ex;
        }
    }

    /**
     * @see net.github.rtc.app.service.CourseService#delete(String)
     */
    @Override
    @Transactional
    public void delete(String code) {
        LOG.debug("Removing course with code {} ", code);
        Assert.notNull(code, "code cannot be null");
        checkCode(code);
        resource.deleteByCode(code);
    }

    /**
     * @see net.github.rtc.app.service.CourseService#findByCode(String)
     */
    @Override
    @Transactional
    public Course findByCode(String code) {
        LOG.info("Getting course with code " + code);
        checkCode(code);
        return resource.findByCode(code);
    }

    /**
     * @see net.github.rtc.app.service.CourseService#create(net.github.rtc.app.model.course.Course)
     */
    @Override
    @Transactional
    public Course create(Course course) {
        LOG.info("Creating course " + course);
        course.setCode(UUID.randomUUID().toString());
        return resource.create(course);
    }

    /**
     * @see net.github.rtc.app.service.CourseService#update(net.github.rtc.app.model.course.Course)
     */
    @Override
    @Transactional
    public void update(Course course) {
        LOG.info("Updating course: " + course);
        resource.update(course);
    }

    @Override
    public Class<Course> getType() {
        return Course.class;
    }

    /**
     * @see net.github.rtc.app.service.CourseService#( net.github.rtc.app.utils.datatable.SearchFilter )
     */
    @Override
    @Transactional
    public CourseSearchResult findByFilter(SearchFilter filter) {
        LOG.info("Searching courses with filter: " + filter);
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
        LOG.info("Getting all courses from database...");
        return (List) resource.findAll();
    }

    @Override
    @Transactional
    public void publish(Course course) {
        LOG.info("Publishing course " + course);
        course.setStatus(CourseStatus.PUBLISHED);
        course.setPublishDate(new Date());
        resource.update(course);
    }
}