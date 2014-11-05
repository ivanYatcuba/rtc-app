package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.CoursesDao;
import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.service.DateService;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl extends AbstractGenericServiceImpl<Course> implements CourseService {

    private static final String COURSE_CANNOT_BE_NULL = "course cannot be null";
    private static final int STARTING_SOON_COURSE_COUNT = 3;
    private static Logger log = LoggerFactory.getLogger(CourseServiceImpl.class.getName());
    @Autowired
    private CoursesDao coursesDao;
    @Autowired
    private DateService dateService;

    @Override
    public Class<Course> getType() {
        return Course.class;
    }

    @Override
    protected GenericDao<Course> getDao() {
        return coursesDao;
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
    public List<Course> startingSoonCourses() {
        final CourseSearchFilter searchFilter = new CourseSearchFilter();
        searchFilter.setStartDate(dateService.getCurrentDate());
        searchFilter.setStatus(CourseStatus.PUBLISHED);
        searchFilter.setPage(1);
        return coursesDao.search(searchFilter.getCriteria().addOrder(Order.asc("startDate")), 1,
          STARTING_SOON_COURSE_COUNT).getResults();
    }

}
