package net.github.rtc.app.service.course;

import net.github.rtc.app.dao.courses.CoursesDao;
import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.service.builder.UserCourseDtoBuilder;
import net.github.rtc.app.model.dto.user.UserCourseDTO;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.course.CourseStatus;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.generic.AbstractCRUDEventsService;
import net.github.rtc.app.service.news.NewsService;
import net.github.rtc.app.service.order.UserCourseOrderService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.service.security.AuthorizedUserProvider;
import net.github.rtc.app.service.builder.SearchResultsBuilder;
import net.github.rtc.app.service.builder.SearchResultsMapper;
import net.github.rtc.app.model.dto.filter.CourseSearchFilter;
import net.github.rtc.app.model.dto.SearchResults;
import org.springframework.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl extends AbstractCRUDEventsService<Course> implements CourseService {

    private static final String COURSE_CANNOT_BE_NULL = "course cannot be null";
    private static final String COURSE_CODE_CANNOT_BE_NULL = "course code cannot be null";
    private static final Logger LOG = LoggerFactory.getLogger(CourseServiceImpl.class.getName());

    @Autowired
    private UserCourseOrderService orderService;
    @Autowired
    private CoursesDao coursesDao;
    @Autowired
    private DateService dateService;
    @Autowired
    private NewsService newsService;
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
    public void publish(String courseCode, boolean isNewsCreated) {
        Assert.notNull(courseCode, COURSE_CODE_CANNOT_BE_NULL);
        final Course course = findByCode(courseCode);
        Assert.notNull(course, COURSE_CANNOT_BE_NULL);
        LOG.debug("Publishing course: {}  ", course.getCode());
        course.setStatus(CourseStatus.PUBLISHED);
        course.setPublishDate(dateService.getCurrentDate());
        coursesDao.update(course);
        if (isNewsCreated) {
           newsService.createNewsFromCourse(course);
        }
    }

    @Override
    public void archive(String courseCode) {
        Assert.notNull(courseCode, COURSE_CODE_CANNOT_BE_NULL);
        final Course course = findByCode(courseCode);
        Assert.notNull(course, COURSE_CANNOT_BE_NULL);
        LOG.debug("Archiving course: {}  ", course.getCode());
        course.setStatus(CourseStatus.ARCHIVED);
        coursesDao.update(course);
    }

    @Override
    public SearchResults<UserCourseDTO> searchCoursesForUser(CourseSearchFilter filter) {
        final SearchResultsBuilder<Course, UserCourseDTO> courseDTOSearchResultsBuilder = new SearchResultsBuilder<>();
        return courseDTOSearchResultsBuilder.setSearchResultsToTransform(search(filter)).
                setSearchResultsMapper(getCourseToCourseDtoMapper()).build();
    }

    @Override
    public UserCourseDTO getUserCourseDTObyCode(String code) {
        final Course course = findByCode(code);
        return new UserCourseDtoBuilder(course).
                buildAcceptedOrdersCount(orderService.getAcceptedOrdersCount(code)).
                buildCurrentUserAssigned(isUserAssignedForCourse(code)).build();
    }

    @Override
    public void create(Course course, boolean published, boolean newsCreated) {
        if (published) {
            publish(course);
        }
        create(course);
        if (newsCreated) {
            newsService.createNewsFromCourse(course);
        }
    }

    @Override
    public void update(Course course, boolean published, boolean newsCreated) {
        if (published) {
            publish(course);
        }
        update(course);
        if (newsCreated) {
            newsService.createNewsFromCourse(course);
        }
    }

    @Override
    public void deleteByCode(String code) {
        final Course course = findByCode(code);
        if (course.getStatus() != CourseStatus.PUBLISHED) {
            super.deleteByCode(code);
        }
    }

    private void publish(Course course) {
        course.setStatus(CourseStatus.PUBLISHED);
        course.setPublishDate(dateService.getCurrentDate());
    }

    @Override
    public void addParticipant(String courseCode, String userCode) {
        Assert.notNull(courseCode);
        Assert.notNull(userCode);
        final User user = userService.findByCode(userCode);
        final Course course = findByCode(courseCode);
        if (course != null && user != null) {
            course.getParticipants().add(user);
            super.update(course);
        }
    }

    @Override
    public void deleteParticipant(String courseCode, String userCode) {

    }

    private boolean isUserAssignedForCourse(String courseCode) {
        final String code = AuthorizedUserProvider.getAuthorizedUser().getCode();
        final List<UserCourseOrder> userCourseOrders = orderService.getUserOrdersByCode(code);
        for (UserCourseOrder o : userCourseOrders) {
            if (o.getUserCode().equals(code) && o.getCourseCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    private SearchResultsMapper<Course, UserCourseDTO> getCourseToCourseDtoMapper() {
            return new SearchResultsMapper<Course, UserCourseDTO>() {
            @Override
            public List<UserCourseDTO> map(List<Course> searchResults) {
                final List<UserCourseDTO> outputResults = new ArrayList<>();
                for (Course course: searchResults) {
                    final UserCourseDtoBuilder dtoBuilder = new UserCourseDtoBuilder(course);
                    outputResults.add(dtoBuilder.
                            buildAcceptedOrdersCount(orderService.getAcceptedOrdersCount(course.getCode())).build());
                }
                return outputResults;
            }
        };
    }
}
