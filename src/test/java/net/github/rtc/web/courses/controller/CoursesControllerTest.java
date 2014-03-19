package net.github.rtc.web.courses.controller;

import net.github.rtc.web.courses.model.Author;
import net.github.rtc.web.courses.model.Courses;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Vladislav Pikus
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
public class CoursesControllerTest {

    @Mock
    private CoursesService mockService;

    @InjectMocks
    private CoursesController controller;

    private Courses course;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        course = new Courses("codeTest", "nameTest", "DEV", new Author("Vasya", "Pupkin", "vasia@gmail.com"), DateTime.now().toDate(), DateTime.now().toDate());
    }

    @Test
    public void testIndex() throws Exception {
        Collection<Courses> courses = Arrays.asList(course);
        when(mockService.findAll()).thenReturn(courses);
        mockMvc.perform(get("/admin/courses")).andExpect(status().isOk())
                .andExpect(model().attribute("courses", courses))
                .andExpect(view().name("admin/courses/courses"));
        verify(mockService, times(1)).findAll();
        verifyNoMoreInteractions(mockService);
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 5;
        mockMvc.perform(get("/admin/courses/delete/{id}", id))
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/admin/courses"));
        verify(mockService, times(1)).delete(id);
        verifyNoMoreInteractions(mockService);
    }

    @Test
    public void testSingle() throws Exception {
        Courses testCourse = course;
        testCourse.setId(5);
        Integer id = testCourse.getId();
        when(mockService.findById(id)).thenReturn(testCourse);
        mockMvc.perform(get("/admin/courses/{id}", id)).
                andExpect(status().isOk()).
                andExpect(model().attribute("course", testCourse)).
                andExpect(view().name("admin/courses/course"));
        verify(mockService, times(1)).findById(id);
        verifyNoMoreInteractions(mockService);
    }
}
