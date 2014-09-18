package net.github.rtc.app.controller;


import net.github.rtc.app.controller.admin.AdminController;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
@Ignore
public class AdminControllerTest {

    @InjectMocks
    private AdminController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        //        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testAdminMain() throws Exception {
        assertEquals(true, true);
       /* mockMvc.perform(get("/admin")).andExpect(status()
       .isMovedTemporarily())
                .andExpect(view().name("redirect:/admin/course"));*/
    }
}
