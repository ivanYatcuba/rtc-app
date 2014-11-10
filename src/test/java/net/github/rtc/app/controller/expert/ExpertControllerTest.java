package net.github.rtc.app.controller.expert;

import net.github.rtc.app.model.user.Request;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.service.DateService;
import net.github.rtc.app.service.UserCourseOrderService;
import net.github.rtc.app.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
public class ExpertControllerTest {

    private static final String EMAIL = "vasya@mail.ru";

    private static final String ORDER_CODE = "X";

    private static final String CURRENT_USER = "?currentUserName=vasya%40mail.ru";

    private static final String NAME = "vasya";

    private static final String SURNAME = "pupkin";

    @InjectMocks
    private ExpertController controller;

    @Mock
    private CourseService courseService;
    @Mock
    private UserCourseOrderService userCourseOrderService;
    @Mock
    private UserService userService;
    @Mock
    private DateService dateService;

    private User user;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.user = new User();
        user.setEmail(EMAIL);
        user.setName(NAME);
        user.setSurname(SURNAME);
        Authentication auth = new UsernamePasswordAuthenticationToken(user,null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testExpertCourses() throws Exception {
        when(userService.loadUserByUsername(EMAIL)).thenReturn(user);
        mockMvc.perform(get("/expert/requests"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("requests", new ArrayList<Request>()))
                .andExpect(model().attribute("user", user));
    }

    @Test
    public void testUserCourses() throws Exception {
        mockMvc.perform(get("/expert/all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("courses"));
    }

    @Test
    public void testAcceptRequest() throws Exception {
        when(userCourseOrderService.findByCode(ORDER_CODE)).thenReturn(new UserCourseOrder());
        mockMvc.perform(get("/expert/accept/{orderId}", ORDER_CODE))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/expert/requests" + CURRENT_USER));
    }

    @Test
    public void testDeclineRequest() throws Exception {
        when(userCourseOrderService.findByCode(ORDER_CODE)).thenReturn(new UserCourseOrder());
        mockMvc.perform(get("/expert/decline/{orderId}", ORDER_CODE))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/expert/requests" + CURRENT_USER));
    }

}
