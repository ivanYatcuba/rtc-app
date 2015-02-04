package net.github.rtc.app.service.impl.course;

import net.github.rtc.app.dao.CoursesDao;
import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.dto.user.UserCourseDTO;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.impl.genericservise.AbstractCRUDEventsService;
import net.github.rtc.app.service.user.UserCourseOrderService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.CourseNewsCreator;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl extends AbstractCRUDEventsService<Course> implements CourseService {

    private static final String COURSE_CANNOT_BE_NULL = "course cannot be null";
    private static Logger log = LoggerFactory.getLogger(CourseServiceImpl.class.getName());
    @Autowired
    private UserCourseOrderService orderService;
    @Autowired
    private CoursesDao coursesDao;
    @Autowired
    private DateService dateService;
    @Autowired
    private CourseNewsCreator courseNewsCreator;
    @Autowired
    private UserService userService;

    @Override
    public Class<Course> getType() {
        return Course.class;
    }

    @Override
    protected GenericDao<Course> getDao() {
        return coursesDao;
    }

    @Override
    public void publish(boolean ifCreateNews, String courseCode) {
        final Course course = findByCode(courseCode);
        log.debug("Publishing course: {}  ", course);
        Assert.notNull(course, COURSE_CANNOT_BE_NULL);
        course.setStatus(CourseStatus.PUBLISHED);
        course.setPublishDate(dateService.getCurrentDate());
        coursesDao.update(course);
        if (ifCreateNews) {
           createNews(course, userService.getAuthorizedUser());
        }

    }

    @Override
    public void archive(String courseCode) {
        final Course course = findByCode(courseCode);
        log.debug("Archiving course: {}  ", course);
        Assert.notNull(course, COURSE_CANNOT_BE_NULL);
        course.setStatus(CourseStatus.ARCHIVED);
        coursesDao.update(course);
    }

    @Override
    public SearchResults<UserCourseDTO> searchCoursesForUser(boolean withArchived, CourseSearchFilter filter) {
        if (withArchived) {
            filter.getStatus().add(CourseStatus.ARCHIVED);
        }
        final SearchResults<Course> results = search(filter);
        final Mapper mapper = new DozerBeanMapper();
        final List<UserCourseDTO> newResults = new ArrayList<>();
        for (Course course: results.getResults()) {
            final UserCourseDTO userCourseDTO = new UserCourseDTO();
            userCourseDTO.setAcceptedOrders(orderService.getAcceptedOrdersForCourse(course.getCode()));
            mapper.map(course, userCourseDTO);
            newResults.add(userCourseDTO);
        }
        final SearchResults<UserCourseDTO> newSearchResults = new SearchResults<>();
        newSearchResults.setPage(results.getPage());
        newSearchResults.setPerPage(results.getPerPage());
        newSearchResults.setTotalResults(results.getTotalResults());
        newSearchResults.setResults(newResults);
        return newSearchResults;
    }

    @Override
    public void saveCourse(boolean ifPublish, boolean ifCreateNews, Course course, boolean doUpdate) {
        course.setStatus(ifPublish ? CourseStatus.PUBLISHED : CourseStatus.DRAFT);
        course.setPublishDate(dateService.getCurrentDate());
        if (doUpdate) {
            update(course);
        } else {
            create(course);
        }
        if (ifCreateNews) {
            createNews(course, userService.getAuthorizedUser());
        }
    }


    @Override
    public void deleteByCode(String code) {
        final Course course = findByCode(code);
        if (course.getStatus() != CourseStatus.PUBLISHED) {
            super.deleteByCode(code);
        }
    }

    @Override
    public void createNews(final Course course, final User author) {
        courseNewsCreator.createNews(course, author);
    }
}
