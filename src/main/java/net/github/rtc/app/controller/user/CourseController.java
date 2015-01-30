package net.github.rtc.app.controller.user;

import net.github.rtc.app.controller.common.MenuItem;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.user.UserCourseOrderService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;
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
    private static final String STRING_COURSES = "courses";
    private static final int COURSES_PER_PAGE = 9;

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserCourseOrderService userCourseOrderService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView userCourses() throws Exception {
        final ModelAndView mav = new ModelAndView(ROOT + "/course/courses");
        return mav;
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

    @RequestMapping(value = "/courseTable", method = RequestMethod.POST)
    public @ResponseBody ModelAndView getCourseTable(@ModelAttribute("courseFilter") final CourseSearchFilter courseFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + "/course/courseTable");
        final SearchResults<Course> results = courseService.search(courseFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_COURSES, results.getResults());
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

    @ModelAttribute("courseTypes")
    public CourseType[] getTypes() {
        return CourseType.values();
    }

    @ModelAttribute("courseFilter")
    public CourseSearchFilter getCourseSearchFilter() {
        final CourseSearchFilter filter = new CourseSearchFilter();
        filter.setPerPage(COURSES_PER_PAGE);
        return filter;
    }
}
