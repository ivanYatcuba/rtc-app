package net.github.rtc.app.controller.user;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.TraineePosition;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.service.DateService;
import net.github.rtc.app.service.UserCourseOrderService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import net.github.rtc.app.utils.propertyeditors.CustomTypeEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    private static final String ROOT = "portal/user";
    private static final String STRING_USER = "user";
    private static final String STRING_REDIRECT = "redirect:/";
    private static final int START_SEARCH = 3;
    private static final String STRING_COURSE = "course";
    private static final String STRING_USER_COURSES = "userCourses";
    private static final String STRING_VALIDATION_RULES = "validationRules";
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserCourseOrderService userCourseOrderService;
    @Autowired
    private ValidationContext validationContext;
    @Autowired
    private DateService dateService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView user() {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/userdataview");
        final User user = userService.loadUserByUsername(
          SecurityContextHolder.getContext().getAuthentication().getName());
        mav.addObject(STRING_USER, user);
        return mav;
    }

    /**
     * Process the request to get edit user form
     *
     * @return modelAndView(user/layout)
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit() {
        final User user = userService.loadUserByUsername(
          SecurityContextHolder.getContext().getAuthentication().getName());
        final ModelAndView mav = new ModelAndView(ROOT + "/page/edituser");
        mav.getModelMap().addAttribute(STRING_USER, user);
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(User.class));
        return mav;
    }

    /**
     * Process the request to post entered user in the form
     *
     * @param user course object
     * @return if all is OK the redirect to view user details
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(
      @ModelAttribute(STRING_USER) final User user) {
        user.setAuthorities(Arrays.asList(userService.getRoleByType(RoleType.ROLE_USER)));
        userService.update(user);
        final Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(request);
        return new ModelAndView(STRING_REDIRECT + "user/view");
    }

    @RequestMapping(value = "/userCourses", method = RequestMethod.GET)
    public ModelAndView userCourses() throws Exception {
        final User user = userService.loadUserByUsername(
          SecurityContextHolder.getContext().getAuthentication().getName());
        final UserCourseOrder currentUserCourseOrder = userCourseOrderService.getUserOrderByUserCode(user.getCode());
        if (currentUserCourseOrder == null) {
            final ModelAndView mav = new ModelAndView(ROOT + "/page/usercours");
            mav.addObject(STRING_VALIDATION_RULES, validationContext.get(UserCourseOrder.class));
            final CourseSearchFilter courseSearchFilter = new CourseSearchFilter();
            courseSearchFilter.setStatus(CourseStatus.PUBLISHED);
            mav.addObject(STRING_USER, user);
            mav.addObject("courses", courseService.search(courseSearchFilter).getResults());
            return mav;
        } else {
            final ModelAndView mav = new ModelAndView(ROOT + "/page/courseorder");
            final Course orderedCourse = courseService.findByCode(currentUserCourseOrder.getCourseCode());
            mav.addObject(STRING_USER, user);
            mav.addObject("orderStatus", currentUserCourseOrder.getStatus());
            mav.addObject(STRING_COURSE, orderedCourse);
            return mav;
        }
    }

    @RequestMapping(value = "/courseDetails/{courseCode}", method = RequestMethod.GET)
    public ModelAndView courseDetails(@PathVariable final String courseCode) {
        final ModelAndView mav = new ModelAndView("portal/user/page/courseDetail");
        mav.addObject(STRING_COURSE, courseService.findByCode(courseCode));
        mav.addObject(STRING_USER,
          userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return mav;
    }

    @RequestMapping(value = "/sendOrder", method = RequestMethod.POST)
    public ModelAndView sendCourseOrder(@ModelAttribute("order") final UserCourseOrder myCourse) {
        final ModelAndView mav = new ModelAndView("redirect:/user/userCourses");
        final User user = userService.loadUserByUsername(
          SecurityContextHolder.getContext().getAuthentication().getName());
        myCourse.setUserCode(user.getCode());
        userCourseOrderService.create(myCourse);
        return mav;
    }

    /**
     * Binding user conditions for entry into the form conclusions
     *
     * @param binder
     */
    @InitBinder(STRING_USER)
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Collection.class, new CustomTypeEditor());
    }

    /**
     * Prepare user as model attribute
     *
     * @return user object
     */
    @ModelAttribute(value = STRING_USER)
    public User getCommandObject() {
        return new User();
    }

    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @ModelAttribute("order")
    public UserCourseOrder getObject() {
        return new UserCourseOrder();
    }

    @ModelAttribute("positions")
    public List<String> getPositions() {
        return TraineePosition.findAll();
    }
}
