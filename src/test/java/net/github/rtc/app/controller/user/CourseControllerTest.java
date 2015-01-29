package net.github.rtc.app.controller.user;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.user.UserCourseOrderService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.util.converter.ValidationContext;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore //temporary, really
public class CourseControllerTest {

    private static final String EMAIL = "vasya@mail.ru";

    private static final String NAME = "vasya";

    private static final String SURNAME = "pupkin";

    private static final String CURRENT_USERNAME = "?currentUserName=vasya%40mail.ru";

    private static final String COURSE_CODE = "X";

    @InjectMocks
    private ProfileController controller;

    @Mock
    private UserService userService;
    @Mock
    private CourseService courseService;
    @Mock
    private UserCourseOrderService userCourseOrderService;
    @Mock
    private ValidationContext validationContext;
    @Mock
    private DateService dateService;
    @Mock
    private AuthenticationManager authenticationManager;

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
        when(userService.loadUserByUsername(EMAIL)).thenReturn(user);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testUserCoursesIfNull() throws Exception {
        when(validationContext.get(UserCourseOrder.class)).thenReturn("validationContext");
        mockMvc.perform(get("/user/userCourses"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user", "courses"))
                .andExpect(model().attributeExists("validationRules"));
    }

    @Test
    public void testUserCourses() throws Exception {
        UserCourseOrder order = new UserCourseOrder();
        order.setCourseCode("code");
        when(userCourseOrderService.getUserOrderByUserCode(user.getCode())).thenReturn(order);
        when(courseService.findByCode(order.getCourseCode())).thenReturn(new Course());
        mockMvc.perform(get("/user/userCourses"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user", "course", "orderStatus"));
    }

    @Test
    public void testCourseDetails() throws Exception {
        when(courseService.findByCode(COURSE_CODE)).thenReturn(new Course());
        mockMvc.perform(get("/user/courseDetails/{courseCode}", COURSE_CODE))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user", "course"));
    }

    @Test
    public void testSendCourseOrder() throws Exception {
        mockMvc.perform(post("/user/sendOrder").sessionAttr("order", new UserCourseOrder()))
                .andExpect(status().isFound());
    }

    @Test
    public void testCoursePosition() throws Exception {
        when(courseService.findByCode(COURSE_CODE)).thenReturn(new Course());
        mockMvc.perform(get("/user/position/{courseCode}", COURSE_CODE))
                .andExpect(status().isOk());
    }
}
