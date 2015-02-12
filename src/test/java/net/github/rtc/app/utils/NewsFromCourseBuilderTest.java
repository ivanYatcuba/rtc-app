package net.github.rtc.app.utils;

import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.course.CourseType;
import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.model.entity.news.NewsStatus;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.date.DateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.when;


@RunWith(BlockJUnit4ClassRunner.class)
public class NewsFromCourseBuilderTest {

    public static final String TEST_STRING = "test string";

    @Mock
    private DateService dateService;
    @Mock
    private StringFromTemplateBuilder stringFromTemplateBuilder;
    @InjectMocks
    private NewsFromCourseBuilder newsFromCourseBuilder = new NewsFromCourseBuilder();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testNewsCreation() {
        when(stringFromTemplateBuilder.build()).thenReturn(TEST_STRING);
        when(stringFromTemplateBuilder.setTemplate(any(String.class))).thenReturn(stringFromTemplateBuilder);
        when(stringFromTemplateBuilder.setTemplateParams(anyMap())).thenReturn(stringFromTemplateBuilder);

        final User author = getAuthor();
        final Course course = getBaCourse();
        final Date creationDate = new Date();
        final News news = newsFromCourseBuilder.setCourse(course).setAuthor(author).
                setCreationDate(creationDate).build();

        assertEquals(news.getAuthor(), author);
        assertEquals(creationDate, news.getCreateDate());
        assertEquals(NewsStatus.DRAFT, news.getStatus());
        assertEquals(TEST_STRING, news.getTitle());
        assertEquals(TEST_STRING, news.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewsCreationWrongArgumentCourse() {
        newsFromCourseBuilder.setCourse(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNewsCreationWrongArgumentAuthor() {
        newsFromCourseBuilder.setAuthor(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNewsCreationWrongArgumentCreationDate() {
        newsFromCourseBuilder.setCreationDate(null);
    }

    private User getAuthor() {
        final User user = new User();
        user.setName("name");
        user.setSurname("surname");
        user.setMiddleName("middlename");
        return user;
    }

    private Course getBaCourse() {
        final Course course =  new Course();
        course.setName("BAtestCourse");
        final Set<CourseType> courseTypes = new HashSet<>();
        courseTypes.add(CourseType.BA);
        course.setTypes(courseTypes);
        return course;
    }
}
