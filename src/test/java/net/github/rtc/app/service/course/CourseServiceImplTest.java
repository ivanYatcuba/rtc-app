package net.github.rtc.app.service.course;

import net.github.rtc.app.dao.course.CourseDao;
import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.model.dto.SearchResults;
import net.github.rtc.app.model.dto.filter.AbstractSearchFilter;
import net.github.rtc.app.model.dto.filter.CourseSearchFilter;
import net.github.rtc.app.model.dto.user.UserCourseDto;
import net.github.rtc.app.model.entity.AbstractPersistenceObject;
import net.github.rtc.app.model.entity.activity.ActivityEntity;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.course.CourseStatus;
import net.github.rtc.app.model.entity.course.CourseType;
import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.builder.NewsBuilder;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.generic.AbstractCrudEventsServiceTest;
import net.github.rtc.app.service.generic.GenericService;
import net.github.rtc.app.service.news.NewsService;
import net.github.rtc.app.service.order.UserCourseOrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CourseServiceImplTest extends AbstractCrudEventsServiceTest {


    @Mock
    private NewsBuilder newsBuilder;
    @Mock
    NewsService newsService;
    @Mock
    private DateService dateService;
    @Mock
    private CourseDao courseDao;
    @Mock
    private UserCourseOrderService orderService;
    @InjectMocks
    private CourseServiceImpl courseService;


    @Before
    public void mockDateService() {
        when(dateService.getCurrentDate()).thenReturn(new Date());
    }

    @Test
    public void testCreate() {
        Course testEntity = (Course) getTestEntity();

        doAnswer(new Answer() {
            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                return invocation.getArguments()[0]; // courseDao.create(SomeCourse) -> SomeCourse
            }
        }).when(courseDao).create(any(Course.class));

        when(newsService.create(any(Course.class))).thenReturn(new News());

        assertEquals(testEntity, courseService.create(testEntity));

        courseService.create(testEntity,true,false);
        assertCourseIsPublish(testEntity,true);

        testEntity = (Course) getTestEntity();
        courseService.create(testEntity,false,false);
        assertCourseIsPublish(testEntity,false);

        verify(newsService, times(0)).create(any(Course.class));

        testEntity = (Course) getTestEntity();
        courseService.create(testEntity,true,true);
        assertCourseIsPublish(testEntity,true);

        testEntity = (Course) getTestEntity();
        courseService.create(testEntity,false,true);
        assertCourseIsPublish(testEntity,false);

        verify(newsService, times(2)).create(any(Course.class));
    }

    @Test
    public void testUpdate(){
        Course testEntity = (Course) getTestEntity();

        doAnswer(new Answer() {
            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                return invocation.getArguments()[0];
            }
        }).when(courseDao).update(any(Course.class));

        when(newsService.create(any(Course.class))).thenReturn(new News());

        assertEquals(testEntity, courseService.update(testEntity));

        courseService.update(testEntity, true, false);
        assertCourseIsPublish(testEntity,true);

        testEntity = (Course) getTestEntity();
        courseService.update(testEntity, false, false);
        assertCourseIsPublish(testEntity,false);

        verify(newsService, times(0)).create(any(Course.class));

        testEntity = (Course) getTestEntity();
        courseService.update(testEntity, true, true);
        assertCourseIsPublish(testEntity,true);

        testEntity = (Course) getTestEntity();
        courseService.update(testEntity, false, true);
        assertCourseIsPublish(testEntity,false);

        verify(newsService, times(2)).create(any(Course.class));
    }

    private void assertCourseIsPublish(Course testEntity, boolean isPublish) {
        assertEquals(testEntity.getStatus(), isPublish ? CourseStatus.PUBLISHED : CourseStatus.DRAFT);
        if (isPublish) {
            assertNotNull(testEntity.getPublishDate());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPublishCodeNull() throws Exception {
        courseService.publish(null,false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPublishCourseNotFound() throws Exception {
        when(courseDao.findByCode(CODE)).thenReturn(null);
        courseService.publish(CODE,false);
    }


    @Test
    public void testPublish() throws Exception {
        final Course testEntity = (Course) getTestEntity();
        doAnswer(new Answer() {
            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                return invocation.getArguments()[0];
            }
        }).when(courseDao).update(any(Course.class));

        when(courseDao.findByCode(CODE)).thenReturn(testEntity);


        courseService.publish(CODE,false);
        assertCourseIsPublish(testEntity, true);
        verify(newsService, times(0)).create(any(Course.class));

        testEntity.setStatus(CourseStatus.DRAFT);
        courseService.publish(CODE, true);
        assertCourseIsPublish(testEntity, true);
        verify(newsService, times(1)).create(any(Course.class));
    }

    @Test
    public void testArchive() throws Exception {
        final Course testEntity = (Course) getTestEntity();
        doAnswer(new Answer() {
            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                return invocation.getArguments()[0];
            }
        }).when(courseDao).update(any(Course.class));

        when(courseDao.findByCode(CODE)).thenReturn(testEntity);


        courseService.archive(CODE);
        assertEquals(testEntity.getStatus(), CourseStatus.ARCHIVED);
        verify(newsService, times(0)).create(any(Course.class));
    }

    @Test
    public void testGetUserCourseDtoByCode() throws Exception {
        final int orderCount = 10;
        final Course testEntity = (Course) getTestEntity();
        Set<User> experts = new HashSet<>();
        experts.add(new User());
        testEntity.setExperts(experts);
        when(orderService.getAcceptedOrdersCount(CODE)).thenReturn(orderCount);
        when(orderService.findByUserCodeAndCourseCode(anyString(), eq(CODE) )).thenReturn(null);
        when(courseDao.findByCode(CODE)).thenReturn(testEntity);

        final UserCourseDto courseDto = courseService.getUserCourseDtoByCode(CODE);

        assertCourseDto(courseDto, testEntity, orderCount, false);
    }


    @Test
    public void testSearchUserCourses() throws Exception {
        final Course testEntity = (Course) getTestEntity();

        final SearchResults<Course> courseList = new SearchResults<>();
        courseList.setResults(Arrays.asList(testEntity));
        when(courseDao.search(any(AbstractSearchFilter.class))).thenReturn(courseList);

        final int orderCount = 10;
        Set<User> experts = new HashSet<>();
        experts.add(new User());
        testEntity.setExperts(experts);

        when(orderService.getAcceptedOrdersCount(CODE)).thenReturn(orderCount);
        when(orderService.findByUserCodeAndCourseCode(anyString(), eq(CODE) )).thenReturn(null);
        when(courseDao.findByCode(CODE)).thenReturn(testEntity);

        final UserCourseDto courseDto = courseService.getUserCourseDtoByCode(CODE);

        final SearchResults<UserCourseDto> results = new SearchResults<>();
        results.setResults(Arrays.asList(courseDto));

        assertSearchResults(results, courseService.searchUserCourses((CourseSearchFilter) getSearchFilter()));

    }

    private void assertCourseDto(UserCourseDto expected, UserCourseDto actual) {
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getCapacity(), actual.getCapacity());
        assertTrue(actual.getAcceptedOrders().equals(expected.getAcceptedOrders()));
        assertEquals(expected.getName(), actual.getName());
        assertArrayEquals(expected.getTypes().toArray(), actual.getTypes().toArray());
        assertEquals(expected.getStartDate(),actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
        assertEquals(expected.getStatus(),actual.getStatus());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertArrayEquals(expected.getExperts().toArray(), actual.getExperts().toArray());
        assertEquals(expected.getCurrentUserAssigned(),actual.getCurrentUserAssigned());
    }
    private void assertCourseDto(UserCourseDto courseDto, Course testEntity, int orderCount, boolean isUserAssigned) {
        assertEquals(courseDto.getCode(),testEntity.getCode());
        assertEquals(courseDto.getCapacity(),testEntity.getCapacity());
        assertTrue(new Integer(orderCount).equals(courseDto.getAcceptedOrders()));
        assertEquals(courseDto.getName(),testEntity.getName());
        assertArrayEquals(courseDto.getTypes().toArray(),testEntity.getTypes().toArray());
        assertEquals(courseDto.getStartDate(),testEntity.getStartDate());
        assertEquals(courseDto.getEndDate(),testEntity.getEndDate());
        assertEquals(courseDto.getStatus(),testEntity.getStatus());
        assertEquals(courseDto.getDescription(),testEntity.getDescription());
        assertArrayEquals(courseDto.getExperts().toArray(), testEntity.getExperts().toArray());
        assertEquals(courseDto.getCurrentUserAssigned(),isUserAssigned);
    }


    private void  assertSearchResults(SearchResults<UserCourseDto> sr1, SearchResults<UserCourseDto> sr2) {
        assertEquals(sr1.getResults().size(), sr2.getResults().size());

        final Iterator<UserCourseDto> it1 = sr1.getResults().iterator();
        final Iterator<UserCourseDto> it2 = sr2.getResults().iterator();

        while (it1.hasNext()){
            assertCourseDto(it1.next(),it2.next());
        }
    }


    @Override
    protected GenericService<AbstractPersistenceObject> getGenericService() {
        return (GenericService) courseService;
    }

    @Override
    protected GenericDao<AbstractPersistenceObject> getGenericDao() {
        return (GenericDao) courseDao;
    }

    @Override
    protected ActivityEntity trueActivityEntity() {
        return ActivityEntity.COURSE;
    }

    @Override
    protected AbstractPersistenceObject getTestEntity() {
        final Course course = new Course();
        course.setName("Test Course");
        course.setCapacity(1);
        course.setTypes(new HashSet<>(Arrays.asList(CourseType.BA, CourseType.QA)));
        course.setEndDate(new Date());
        course.setStartDate(new Date());
        course.setCode(CODE);
        return course;
    }

    @Override
    protected AbstractSearchFilter getSearchFilter() {
        return new CourseSearchFilter();
    }

}