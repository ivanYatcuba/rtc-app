package net.github.rtc.app.utils;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.news.NewsService;
import net.github.rtc.app.service.date.DateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;


@RunWith(BlockJUnit4ClassRunner.class)
public class CourseNewsCreatorTest {

    @Mock
    private NewsService newsService;
    @Mock
    private DateService dateService;
    @InjectMocks
    private CourseNewsCreator courseNewsCreator = new CourseNewsCreator();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testNewsCreationBa() {
        final User author = getAuthor();
        final Course course = getBaCourse();
        final News news = courseNewsCreator.createNews(course, author);
        assertEquals(news.getAuthor(), author);
        //assertEquals(news.getTitle(), "The course \"" + course.getName() +"\"\n"); todo
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
