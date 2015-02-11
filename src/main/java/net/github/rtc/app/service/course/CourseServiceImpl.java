package net.github.rtc.app.service.course;

import net.github.rtc.app.dao.CoursesDao;
import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.model.dto.user.UserCourseDTO;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.course.CourseStatus;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.generic.AbstractCRUDEventsService;
import net.github.rtc.app.service.news.NewsService;
import net.github.rtc.app.service.order.UserCourseOrderService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.AuthorizedUserProvider;
import net.github.rtc.app.utils.datatable.search.SearchResultsBuilder;
import net.github.rtc.app.utils.datatable.search.SearchResultsMapper;
import net.github.rtc.app.utils.datatable.search.filter.CourseSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.apache.commons.lang3.Validate;
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
           newsService.createNewsFromCourse(course, AuthorizedUserProvider.getAuthorizedUser());
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
    public SearchResults<UserCourseDTO> searchCoursesForUser(boolean withArchived, CourseSearchFilter filter) {
        if (withArchived) {
            filter.getStatus().add(CourseStatus.ARCHIVED);
        }
        final SearchResultsBuilder<Course, UserCourseDTO> courseDTOSearchResultsBuilder = new SearchResultsBuilder<>();
        return courseDTOSearchResultsBuilder.setSearchResultsToTransform(search(filter)).
                setSearchResultsMapper(getCourseToCourseDtoMapper()).build();
    }

    @Override
    public UserCourseDTO getUserCourseDTObyCode(String code) {
        final UserCourseDTO userCourseDTO = new UserCourseDTO();
        final Course course = coursesDao.findByCode(code);
        final Mapper mapper = new DozerBeanMapper();
        userCourseDTO.setAcceptedOrders(orderService.getAcceptedOrdersForCourse(course.getCode()));
        mapper.map(course, userCourseDTO);
        return userCourseDTO;
    }

    @Override
    public void create(boolean published, boolean newsCreated, Course course) {
        setCourseStatusAndPublishDate(published, course);
        create(course);
        if (newsCreated) {
            newsService.createNewsFromCourse(course, AuthorizedUserProvider.getAuthorizedUser());
        }
    }

    @Override
    public void update(boolean published, boolean newsCreated, Course course) {
        setCourseStatusAndPublishDate(published, course);
        update(course);
        if (newsCreated) {
            newsService.createNewsFromCourse(course, AuthorizedUserProvider.getAuthorizedUser());
        }
    }

    @Override
    public void deleteByCode(String code) {
        final Course course = findByCode(code);
        if (course.getStatus() != CourseStatus.PUBLISHED) {
            super.deleteByCode(code);
        }
    }

    private void setCourseStatusAndPublishDate(boolean published, Course course) {
        course.setStatus(published ? CourseStatus.PUBLISHED : CourseStatus.DRAFT);
        if (published) {
            course.setPublishDate(dateService.getCurrentDate());
        }
    }

    @Override
    public void addParticipant(String courseCode, String userCode) {
        Validate.notNull(courseCode);
        Validate.notNull(userCode);
        User user = userService.findByCode(userCode);
        Course course = findByCode(courseCode);
        if (course != null && user != null) {
            course.getParticipants().add(user);
            super.update(course);
        }
    }

    @Override
    public void deleteParticipant(String courseCode, String userCode) {

    }

    private SearchResultsMapper<Course, UserCourseDTO> getCourseToCourseDtoMapper() {
            return new SearchResultsMapper<Course, UserCourseDTO>() {
            @Override
            public List<UserCourseDTO> map(List<Course> searchResults) {
                final List<UserCourseDTO> outputResults = new ArrayList<>();
                for (Course course: searchResults) {
                    final Mapper mapper = new DozerBeanMapper();
                    final UserCourseDTO userCourseDTO = new UserCourseDTO();
                    userCourseDTO.setAcceptedOrders(orderService.getAcceptedOrdersForCourse(course.getCode()));
                    mapper.map(course, userCourseDTO);
                    outputResults.add(userCourseDTO);
                }
                return outputResults;
            }
        };
    }
}
