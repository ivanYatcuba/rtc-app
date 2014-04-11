/*package net.github.rtc.web.courses.controller;

import net.github.rtc.web.courses.model.Author;
import net.github.rtc.web.courses.model.Courses;
import net.github.rtc.app.service.CategoryService;
import net.github.rtc.web.courses.service.CoursesService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;*/

/**
 * @author Vladislav Pikus
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
public class CoursesControllerTest {

    @Mock
    private CoursesService mockService;

    @Mock
    private CategoryService mockCategoryService;

    @InjectMocks
    private CoursesController controller;

    private Course course;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        course = new Course("codeTest", "nameTest", "DEV", new Author("Vasya", "Pupkin", "vasia@gmail.com"), DateTime.now().toDate(), DateTime.now().toDate());
    }

    @Test
    public void testIndex() throws Exception {
        Collection<Course> courses = Arrays.asList(course);
        when(mockService.findAll()).thenReturn(courses);
        mockMvc.perform(get("/admin/courses")).andExpect(status().isOk())
                .andExpect(model().attribute("courses", courses))
                .andExpect(view().name("admin/courses/layout"));
        verify(mockService, times(1)).findAll();
        verifyNoMoreInteractions(mockService);
    }

    @Test
    public void testDelete() throws Exception {
        String code = "fd29a957-01e0-4219-bbba-36188aa949fa";
        mockMvc.perform(get("/admin/courses/delete/{code}", code))
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/admin/courses"));
        verify(mockService, times(1)).delete(code);
        verifyNoMoreInteractions(mockService);
    }

    @Test
    public void testSingle() throws Exception {
        Course testCourse = course;
        testCourse.setCode("fd29a957-01e0-4219-bbba-36188aa949fa");
        String code = testCourse.getCode();
        when(mockService.findByCode(code)).thenReturn(testCourse);
        mockMvc.perform(get("/admin/courses/{code}", code)).
                andExpect(status().isOk()).
                andExpect(model().attribute("course", testCourse)).
                andExpect(view().name("admin/courses/course"));
        verify(mockService, times(1)).findByCode(code);
        verifyNoMoreInteractions(mockService);
    }
}*/
