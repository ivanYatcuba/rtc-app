package net.github.rtc.app.controller;

import net.github.rtc.app.controller.common.WelcomeController;
import net.github.rtc.app.model.CourseDto;
import net.github.rtc.app.model.SearchFilter;
import net.github.rtc.app.service.CoursesService;
import org.junit.Before;
import org.junit.Ignore;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by ivan on 01.04.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
@Ignore
public class WelcomeControllerTest {
    @InjectMocks
    private WelcomeController controller;


    @Mock
    private CoursesService coursesService;
    @Mock
    private CourseDto courseDto;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testAdminMain() throws Exception {

        Map<String, String> map = new HashMap<String, String>();
        map.put("maxResult", "3");

        SearchFilter searchFilter = new SearchFilter();
        Date date = new Date();
        String formattedDate = new SimpleDateFormat("dd.MM.yyyy").format(date);
        searchFilter.setStartDate(formattedDate);
        when(coursesService.findByFilter(searchFilter.createQuery(map).byDate().toString())).thenReturn(new CourseDto());
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(model().attribute("content", "content/welcomeContent"))
                .andExpect(view().name("welcome/welcomeLayout"));
    }

}
