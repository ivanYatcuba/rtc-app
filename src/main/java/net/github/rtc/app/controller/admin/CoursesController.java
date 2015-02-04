package net.github.rtc.app.controller.admin;

import net.github.rtc.app.controller.common.MenuItem;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.propertyeditors.CustomExpertsEditor;
import net.github.rtc.app.utils.propertyeditors.CustomTagsEditor;
import net.github.rtc.app.utils.propertyeditors.CustomTypeEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Controller for {@link net.github.rtc.app.model.course.Course}
 *
 * @author Vladislav Pikus
 */
@Controller("coursesController")
@RequestMapping("admin/course")
public class CoursesController implements MenuItem {

    private static final String STRING_COURSE = "course";
    private static final String STRING_TYPES = "types";
    private static final String STRING_STATUSES = "statuses";
    private static final String STRING_FILTER_COURSE = "filterCourse";
    private static final String REDIRECT = "redirect:/";
    private static final String STRING_ADMIN = "admin";
    private static final String STRING_VALIDATION_RULES = "validationRules";
    private static final String STRING_EXPERTS = "experts";
    private static final String VIEW = "view/";
    private static final String REDIRECT1 = "redirect: ";
    private static final String ROOT = "portal/admin";
    private static final String UPDATE_VIEW = "/course/courseUpdate";
    private static final String CREATE_VIEW = "/course/courseCreate";
    private static final String DETAILS_VIEW = "/course/courseDetails";

    private static final String SHOW_MSG_NEWS_CREATED = "?newsJustCreated=true";;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private ValidationContext validationContext;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        final ModelAndView mav = new ModelAndView(ROOT + CREATE_VIEW);
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(Course.class));
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute(STRING_COURSE) final Course course,
                       @RequestParam(required = false) final boolean ifPublish,
                       @RequestParam(required = false) final boolean ifCreateNews) {
        courseService.saveCourse(ifPublish, ifCreateNews, course, false);
        if (ifCreateNews) {
            return REDIRECT1 + VIEW + course.getCode() + SHOW_MSG_NEWS_CREATED;
        }
        return REDIRECT1 + VIEW + course.getCode();
    }

    @RequestMapping(value = VIEW + "{courseCode}", method = RequestMethod.GET)
    public ModelAndView single(@PathVariable final String courseCode,
                               @RequestParam(required = false) boolean newsJustCreated) {
        final ModelAndView mav = new ModelAndView(ROOT + DETAILS_VIEW);
        mav.addObject(STRING_COURSE, courseService.findByCode(courseCode));
        mav.addObject("newsInfo", newsJustCreated);
        return mav;
    }

    @RequestMapping(value = "/{courseCode}/update", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable final String courseCode) {
        final ModelAndView mav = new ModelAndView(ROOT + UPDATE_VIEW);
        mav.addObject(STRING_COURSE, courseService.findByCode(courseCode));
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(Course.class));
        return mav;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute(STRING_COURSE) final Course course,
                         @RequestParam(required = false) final boolean ifPublish,
                         @RequestParam(required = false) final boolean ifCreateNews) {
        courseService.saveCourse(ifPublish, ifCreateNews, course, true);
        if (ifCreateNews) {
            return REDIRECT1 + VIEW + course.getCode() + SHOW_MSG_NEWS_CREATED;
        }
        return REDIRECT1 + VIEW + course.getCode();
    }

    @RequestMapping(value = "/delete/{courseCode}", method = RequestMethod.GET)
    public String delete(@PathVariable final String courseCode) {
        courseService.deleteByCode(courseCode);
        return REDIRECT + STRING_ADMIN;
    }

    @RequestMapping(value = "/publish/{courseCode}", method = RequestMethod.GET)
    public String publish(@PathVariable final String courseCode,
                          @RequestParam(required = false) final boolean ifCreateNews) {
        courseService.publish(ifCreateNews, courseCode);
        return REDIRECT + STRING_ADMIN;
    }

    @RequestMapping(value = "/archive/{courseCode}", method = RequestMethod.GET)
    public String archive(@PathVariable final String courseCode) {
        courseService.archive(courseCode);
        return REDIRECT + STRING_ADMIN;
    }

    @ModelAttribute(value = STRING_COURSE)
    public Course getCommandObject() {
        return new Course();
    }

    @ModelAttribute("categories")
    public Map<String, String> getCategories() {
        final Map<String, String> categories = new HashMap<>();
        for (CourseType type : CourseType.findAll()) {
            categories.put(type.name(), type.toString());
        }
        return categories;
    }

    @ModelAttribute(STRING_EXPERTS)
    public Map<String, String> getExpertUsers() {
        final Map<String, String> expertMap = new HashMap<>();
        for (User u : userService.getUserByRole(RoleType.ROLE_EXPERT)) {
            expertMap.put(u.getCode(), u.toString());
        }
        return expertMap;
    }
    @ModelAttribute(STRING_STATUSES)
    public Collection<String> getStatuses() {
        return CourseStatus.findAll();
    }

    @InitBinder(STRING_COURSE)
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "tags", new CustomTagsEditor());
        binder.registerCustomEditor(List.class, STRING_TYPES, new CustomTypeEditor());
        binder.registerCustomEditor(Set.class, STRING_EXPERTS, new CustomExpertsEditor(userService));
    }

    @Override
    public String getMenuItem() {
        return STRING_COURSE;
    }
}



