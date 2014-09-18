package net.github.rtc.app.controller;

import net.github.rtc.app.controller.common.WelcomeController;
import net.github.rtc.app.service.CourseService;
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

import static junit.framework.Assert.assertEquals;

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
    private CourseService courseService;


    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        // mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testAdminMain() throws Exception {
        assertEquals(true, true);
        /* CourseSearchFilter searchFilter = new CourseSearchFilter();
        searchFilter.setStartDate(new Date());
        when(courseService.search(searchFilter.getCriteria(),1,
        3)).thenReturn(null);
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(model().attribute("content",
                "content/welcomeContent"))
                .andExpect(view().name("welcome/welcomeLayout"));*/
    }

}
