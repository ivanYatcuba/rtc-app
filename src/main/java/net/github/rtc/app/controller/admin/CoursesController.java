package net.github.rtc.app.controller.admin;

import net.github.rtc.app.controller.common.ResourceNotFoundException;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.utils.propertyeditors.CustomExpertsEditor;
import net.github.rtc.app.utils.propertyeditors.CustomTagsEditor;
import net.github.rtc.app.utils.propertyeditors.CustomTypeEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private DateService dateService;

    /**
     * Processes the request to delete by id
     * Url example: "/delete/1". Parse by pattern: "/delete/{courseId}"
     * If all is well, we get redirected to "course"
     *
     * @param courseCode course ID
     * @return redirect to "/admin/courses"
     */
    @RequestMapping(value = "/delete/{courseCode}", method = RequestMethod.GET)
    public String delete(@PathVariable final String courseCode) {
        final Course course = courseService.findByCode(courseCode);
        if (course.getStatus() != CourseStatus.PUBLISHED) {
            courseService.deleteByCode(courseCode);
        }
        return REDIRECT + STRING_ADMIN;
    }

    @RequestMapping(value = "/publish/{courseCode}", method = RequestMethod.POST)//todo: get
    public String publish(@PathVariable final String courseCode, @RequestParam(required = false) final boolean
      ifCreateNews) {
        final Course course = courseService.findByCode(courseCode);
        courseService.publish(course);
        if (ifCreateNews) {
            courseService.createNews(course, getCurrentUser());
        }
        return REDIRECT + STRING_ADMIN;
    }

    /**
     * Process the request to get details about course by selected code
     * URL example: "/1". Parse by pattern: "/{code}"
     * if success go to view "admin/courses/course")
     *
     * @param courseCode course code
     * @param newsJustCreated true if course published and news just created
     * @return modelAndView("admin/courses/course")
     */
    @RequestMapping(value = VIEW + "{courseCode}", method = RequestMethod.GET)
    public ModelAndView single(@PathVariable final String courseCode, @RequestParam(required = false) boolean
      newsJustCreated) {
        final ModelAndView mav = new ModelAndView(ROOT + DETAILS_VIEW);
        final Course course = courseService.findByCode(courseCode);

        if (courseService.isNotFound(course)) {
            throw new ResourceNotFoundException();
        }

        mav.addObject(STRING_COURSE, course);
        mav.addObject("newsInfo", newsJustCreated);
        return mav;
    }

    /**
     * Process the request to get create course form
     *
     * @return modelAndView("admin/courses/layout")
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        final ModelAndView mav = new ModelAndView(ROOT + CREATE_VIEW);
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(Course.class));
        return mav;
    }

    /**
     * Process the request to post entered course in the form
     *
     * @param course course object
     * @return if all is OK the redirect to view new course or return to edit
     * course
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
      @ModelAttribute(STRING_COURSE) final Course course,
      @RequestParam(required = false) final boolean ifPublish,
        @RequestParam(required = false) final boolean ifCreateNews) {
        course.setStatus(ifPublish ? CourseStatus.PUBLISHED : CourseStatus.DRAFT);
        course.setPublishDate(dateService.getCurrentDate());
        courseService.create(course);
        if (ifCreateNews) {
            courseService.createNews(course, getCurrentUser());
            return REDIRECT1 + VIEW + course.getCode() + SHOW_MSG_NEWS_CREATED;
        }
        return REDIRECT1 + VIEW + course.getCode();
    }

    /**
     * Process the request to get edit course form
     *
     * @return modelAndView("admin/courses/layout")
     */
    @RequestMapping(value = "/{courseCode}/update", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable final String courseCode) {
        final ModelAndView mav = new ModelAndView(ROOT + UPDATE_VIEW);
        final Course returnCourse = courseService.findByCode(courseCode);
        mav.getModelMap().addAttribute(STRING_COURSE, returnCourse);
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(Course.class));
        return mav;
    }

    /**
     * Process the request to post entered course in the form
     *
     * @param course course object
     * @return if all is OK the redirect to view course or return to edit course
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
      @ModelAttribute(STRING_COURSE) final Course course,
      @RequestParam(value = "ifPublish", required = false) final boolean ifPublish,
      @RequestParam(required = false) final boolean ifCreateNews) {
        course.setStatus(ifPublish ? CourseStatus.PUBLISHED : CourseStatus.DRAFT);
        course.setPublishDate(dateService.getCurrentDate());
        courseService.update(course);
        if (ifCreateNews) {
            courseService.createNews(course, getCurrentUser());
            return REDIRECT1 + VIEW + course.getCode() + SHOW_MSG_NEWS_CREATED;
        }
        return REDIRECT1 + VIEW + course.getCode();
    }

    /**
     * Binding course conditions for entry into the form conclusions
     *
     * @param binder
     */
    @InitBinder(STRING_COURSE)
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "tags", new CustomTagsEditor());
        binder.registerCustomEditor(List.class, STRING_TYPES, new CustomTypeEditor());
        binder.registerCustomEditor(Set.class, STRING_EXPERTS, new CustomExpertsEditor(userService));
    }

    @InitBinder(STRING_FILTER_COURSE)
    public void initFilterBinder(final WebDataBinder binder) {
        initBinder(binder);
    }

    /**
     * Prepare collection categories as model attribute
     *
     * @return collection categories
     */
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

    @ModelAttribute("currentUserName")
    public String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        return userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @ModelAttribute(STRING_FILTER_COURSE)
    public CourseSearchFilter getFilterCourse() {
        return new CourseSearchFilter();
    }

    /**
     * Prepare course as model attribute
     *
     * @return course object
     */
    @ModelAttribute(value = STRING_COURSE)
    public Course getCommandObject() {
        return new Course();
    }

    @Override
    public String getMenuItem() {
        return STRING_COURSE;
    }



}



