package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.CoursesDao;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.service.DateService;
import net.github.rtc.app.service.ModelService;
import net.github.rtc.app.utils.datatable.search.AbstractSearchCommand;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
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

    private static final String CODE_CANNOT_BE_NULL = "code cannot be null";
    private static final String COURSE_CANNOT_BE_NULL = "course cannot be null";
    private static final int STARTING_SOON_COURSE_COUNT = 3;
    private static Logger log = LoggerFactory.getLogger(CourseServiceImpl
            .class.getName());

    @Autowired
    private CoursesDao coursesDao;

    @Autowired
    private DateService dateService;

    /**
     * @see net.github.rtc.app.service.CourseService#delete(String)
     */
    @Override
    public void delete(final String code) {
        log.debug("Removing course with code {} ", code);
        Assert.notNull(code, CODE_CANNOT_BE_NULL);
        coursesDao.deleteByCode(code);
    }

    /**
     * @see net.github.rtc.app.service.CourseService#findByCode(String)
     */
    @Override
    public Course findByCode(final String code) {
        log.debug("Getting course with code {}", code);
        Assert.notNull(code, CODE_CANNOT_BE_NULL);
        return coursesDao.findByCode(code);
    }

    /**
     * @see net.github.rtc.app.service.CourseService#create(net.github.rtc.app.model.course.Course)
     */
    @Override
    public Course create(final Course course) {
        log.debug("Creating course {} ", course);
        Assert.notNull(course, COURSE_CANNOT_BE_NULL);
        course.setCode(UUID.randomUUID().toString());
        return coursesDao.create(course);
    }

    /**
     * @see net.github.rtc.app.service.CourseService#update(net.github.rtc.app.model.course.Course)
     */
    @Override
    public void update(final Course course) {
        log.debug("Updating course: {} ", course);
        Assert.notNull(course, COURSE_CANNOT_BE_NULL);
        coursesDao.update(course);
    }

    @Override
    public Class<Course> getType() {
        return Course.class;
    }

    @Override
    @Transactional
    public List<Course> findAll() {
        log.debug("Getting all courses from database...");
        return coursesDao.findAll();
    }

    @Override
    public void publish(final Course course) {
        log.debug("Publishing course: {}  ", course);
        Assert.notNull(course, COURSE_CANNOT_BE_NULL);
        course.setStatus(CourseStatus.PUBLISHED);
        course.setPublishDate(dateService.getCurrentDate());
        coursesDao.update(course);
    }

    @Override
    @Transactional
    public SearchResults<Course> search(
            final DetachedCriteria criteria, final int start, final int max) {
        log.debug("Searching courses");
        return coursesDao.search(criteria, start, max);
    }

    @Override
    @Transactional
    public SearchResults<Course> search(AbstractSearchCommand searchCommand) {
        log.debug("Searching courses///");
        return coursesDao.search(searchCommand);
    }

    @Override
    public List<Course> startingSoonCourses() {
        final CourseSearchFilter searchFilter = new CourseSearchFilter();
        searchFilter.setStartDate(dateService.getCurrentDate());
        searchFilter.setStatus(CourseStatus.PUBLISHED);
        searchFilter.setPage(1);
        return coursesDao.search(searchFilter.getCriteria().addOrder(Order.asc("startDate")), 1, STARTING_SOON_COURSE_COUNT)
                .getResults();
    }

}
