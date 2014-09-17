package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.CoursesDao;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.service.ModelService;
import net.github.rtc.app.utils.datatable.CourseSearchFilter;
import net.github.rtc.app.utils.datatable.SearchResults;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
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
@Transactional
public class CourseServiceImpl implements ModelService<Course>, CourseService {
    private static Logger LOG = LoggerFactory.getLogger(CourseServiceImpl
      .class.getName());

    @Autowired
    private CoursesDao coursesDao;

    /**
     * @see net.github.rtc.app.service.CourseService#delete(String)
     */
    @Override
    public void delete(final String code) {
        LOG.debug("Removing course with code {} ", code);
        Assert.notNull(code, "code cannot be null");
        coursesDao.deleteByCode(code);
    }

    /**
     * @see net.github.rtc.app.service.CourseService#findByCode(String)
     */
    @Override
    public Course findByCode(final String code) {
        LOG.debug("Getting course with code {}", code);
        Assert.notNull(code, "code cannot be null");
        return coursesDao.findByCode(code);
    }

    /**
     * @see net.github.rtc.app.service.CourseService#create(net.github.rtc
     * .app.model.course.Course)
     */
    @Override
    public Course create(final Course course) {
        LOG.debug("Creating course {} ", course);
        Assert.notNull(course, "course cannot be null");
        course.setCode(UUID.randomUUID().toString());
        return coursesDao.create(course);
    }

    /**
     * @see net.github.rtc.app.service.CourseService#update(net.github.rtc
     * .app.model.course.Course)
     */
    @Override
    public void update(final Course course) {
        LOG.debug("Updating course: {} ", course);
        Assert.notNull(course, "course cannot be null");
        coursesDao.update(course);
    }

    @Override
    public Class<Course> getType() {
        return Course.class;
    }

    @Override
    @Transactional
    public List<Course> findAll() {
        LOG.debug("Getting all courses from database...");
        return coursesDao.findAll();
    }

    @Override
    public void publish(final Course course) {
        LOG.debug("Publishing course: {}  ", course);
        Assert.notNull(course, "course cannot be null");
        course.setStatus(CourseStatus.PUBLISHED);
        course.setPublishDate(new Date());
        coursesDao.update(course);
    }

    @Override
    @Transactional
    public SearchResults<Course> search(
      final DetachedCriteria criteria, final int start, final int max) {
        LOG.debug("Searching courses///");
        return coursesDao.search(criteria, start, max);
    }

    @Override
    public List<Course> startingSoonCourses() {
        final CourseSearchFilter searchFilter = new CourseSearchFilter();
        searchFilter.setStartDate(new Date());
        searchFilter.setStatus(CourseStatus.PUBLISHED);
        return coursesDao.search(
          searchFilter.getCriteria().addOrder(Order.asc("startDate")), 1,
          3).getResults();
    }

}
