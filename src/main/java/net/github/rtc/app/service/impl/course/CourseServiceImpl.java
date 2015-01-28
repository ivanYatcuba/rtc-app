package net.github.rtc.app.service.impl.course;

import net.github.rtc.app.dao.CoursesDao;
import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.model.activity.ActivityEntity;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.impl.AbstractGenericServiceImpl;
import net.github.rtc.app.utils.CourseNewsCreator;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import net.github.rtc.app.utils.events.DeleteEntityEvent;
import net.github.rtc.app.utils.events.NewEntityEvent;
import net.github.rtc.app.utils.events.UpdateEntityEvent;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl extends AbstractGenericServiceImpl<Course> implements CourseService, ApplicationEventPublisherAware {

    private static final String COURSE_CANNOT_BE_NULL = "course cannot be null";
    private static final int STARTING_SOON_COURSE_COUNT = 3;
    private static Logger log = LoggerFactory.getLogger(CourseServiceImpl.class.getName());
    @Autowired
    private CoursesDao coursesDao;
    @Autowired
    private DateService dateService;
    @Autowired
    private CourseNewsCreator courseNewsCreator;

    private ApplicationEventPublisher publisher;

    @Override
    public Class<Course> getType() {
        return Course.class;
    }

    @Override
    protected GenericDao<Course> getDao() {
        return coursesDao;
    }

    @Override
    public Course create(Course course) {
        final NewEntityEvent entityEvent = new NewEntityEvent(this, course.getLogDetail(), ActivityEntity.COURSE);
        publisher.publishEvent(entityEvent);
        return super.create(course);
    }

    @Override
    public Course update(Course course) {
        final UpdateEntityEvent entityEvent = new UpdateEntityEvent(this, course.getLogDetail(), ActivityEntity.COURSE);
        publisher.publishEvent(entityEvent);
        return super.update(course);
    }

    @Override
    public void deleteByCode(String code) {
        final Course course = findByCode(code);
        final DeleteEntityEvent entityEvent = new DeleteEntityEvent(this, course.getLogDetail(), ActivityEntity.COURSE);
        publisher.publishEvent(entityEvent);
        super.deleteByCode(code);
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
        return coursesDao.search(searchFilter.getCriteria(), 1,
          STARTING_SOON_COURSE_COUNT, Order.asc("startDate")).getResults();
    }

    @Override
    public List<Course> findAllPublished() {
        final CourseSearchFilter courseSearchFilter = new CourseSearchFilter();
        courseSearchFilter.setStatus(CourseStatus.PUBLISHED);
        return coursesDao.findAll(courseSearchFilter.getCriteria());
    }

    @Override
    public void createNews(final Course course, final User author) {
        courseNewsCreator.createNews(course, author);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
