package net.github.rtc.app.controller.user;

import net.github.rtc.app.controller.common.MenuItem;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.user.UserCourseOrderService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
@RequestMapping("/user/courses")
public class CourseController implements MenuItem {

    private static final String ROOT = "portal/user";
    private static final String STRING_USER = "user";
    private static final String STRING_COURSE = "course";
    private static final String STRING_VALIDATION_RULES = "validationRules";
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserCourseOrderService userCourseOrderService;
    @Autowired
    private ValidationContext validationContext;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView userCourses() throws Exception {
        final User user = userService.loadUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        final UserCourseOrder currentUserCourseOrder = userCourseOrderService.getUserOrderByUserCode(user.getCode());
        if (currentUserCourseOrder == null) {
            final ModelAndView mav = new ModelAndView(ROOT + "/page/usercours");
            mav.addObject(STRING_VALIDATION_RULES, validationContext.get(UserCourseOrder.class));
            mav.addObject(STRING_USER, user);
            mav.addObject("courses", courseService.findAllPublished());
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

    @RequestMapping(value = "/position/{courseCode}", method = RequestMethod.GET)
    public @ResponseBody
    Set<CourseType> getPositions(@PathVariable String courseCode) {
        return courseService.findByCode(courseCode).getTypes();
    }

    @Override
    public String getMenuItem() {
        return STRING_COURSE;
    }
}
