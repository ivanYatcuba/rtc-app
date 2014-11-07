package net.github.rtc.app.controller.user;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.service.DateService;
import net.github.rtc.app.service.UserCourseOrderService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.util.converter.ValidationContext;
import org.junit.Before;
import org.junit.Ignore;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
public class UserControllerTest {

    private static final String EMAIL = "vasya@mail.ru";

    private static final String NAME = "vasya";

    private static final String SURNAME = "pupkin";

    private static final String CURRENT_USER = "?currentUser=vasya%40mail.ru&positions=TESTER&" +
            "positions=DEVELOPER&positions=BUSINESS_ANALYST";

    private static final String COURSE_CODE = "X";

    @InjectMocks
    private UserController controller;

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
    public void testUserView() throws Exception {
        mockMvc.perform(get("/user/view"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testEditUser() throws Exception {
        when(validationContext.get(User.class)).thenReturn("validationContext");
        mockMvc.perform(get("/user/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("validationRules"));
    }

    @Test
    @Ignore
    public void testUpdateUser() throws Exception {
        mockMvc.perform(post("/user/update").sessionAttr("user", user))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user/view" + CURRENT_USER));
    }

    @Ignore
    @Test
    public void testUserCoursesIfNull() throws Exception {
//        final CourseSearchFilter courseSearchFilter = new CourseSearchFilter();
//        courseSearchFilter.setStatus(CourseStatus.PUBLISHED);
//        SearchResults<Course> results = new SearchResults<>();
//        results.setResults(new ArrayList<Course>());
//        when(courseService.search(courseSearchFilter)).thenReturn(new SearchResults<Course>());
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

    @Ignore
    @Test
    public void testCourseDetails() throws Exception {
        when(courseService.findByCode(COURSE_CODE)).thenReturn(new Course());
        mockMvc.perform(get("/courseDetails/{courseCode}", COURSE_CODE))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user", "course"));
    }

    @Test
    public void testSendCourseOrder() throws Exception {
        mockMvc.perform(post("/user/sendOrder").sessionAttr("order", new UserCourseOrder()))
                .andExpect(status().isFound());
    }
}

