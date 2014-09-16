package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.datatable.Page;
import net.github.rtc.app.utils.propertyeditors.CustomTagsEditor;
import net.github.rtc.app.utils.Paginator;
import net.github.rtc.app.utils.datatable.FilterSettings;
import net.github.rtc.app.utils.datatable.SearchCriteria;
import net.github.rtc.app.utils.datatable.SearchResults;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller for {@link net.github.rtc.app.model.course.Course}
 *
 * @author Vladislav Pikus
 */
@Controller("coursesController")
@RequestMapping("admin/course")
public class CoursesController {

    private static final String ROOT = "portal/admin";
    private static final String ROOT_MODEL = "course";

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private Paginator paginator;
    @Autowired
    private ValidationContext validationContext;


    //Filtering settings
    private static final FilterSettings courseFilterSettings;

    static {
        FilterSettings settings = new FilterSettings(Course.class);
        settings.addOption("name", SearchCriteria.RestrictionStrategy.EQ);
        settings.addOption("startDate", SearchCriteria.RestrictionStrategy.GE);
        settings.addOption("status", SearchCriteria.RestrictionStrategy.EQ);
        settings.addOption("tags", SearchCriteria.RestrictionStrategy.EQ,
                Arrays.asList(new String[]{"value"}), SearchCriteria.JunctionStrategy.CONJUNCTION);
        settings.addOption("experts", SearchCriteria.RestrictionStrategy.EQ,
                Arrays.asList(new String[]{"email"}), SearchCriteria.JunctionStrategy.CONJUNCTION);
        courseFilterSettings = settings;
    }

    /**
     * Processes the request to view all courses page
     *
     * @return modelAndView("admin/courses/courses")
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView mav = new ModelAndView(ROOT + "/page/listcontent");
        Course sampleCourse = new Course();
        sampleCourse.setStatus(null);
        paginator.setSettings(courseFilterSettings);
        paginator.setFilterTemplate(sampleCourse);
        switchPage(mav, 1);
        mav.addObject("statuses", getStats());
        return mav;
    }


    /**
     * Process the request to get view about course by custom filter
     *
     * @return modelAndView("admin/courses/courses")
     */
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public ModelAndView navigate(@PathVariable int page) throws Exception {
        ModelAndView mav = new ModelAndView(ROOT + "/page/listcontent");
        switchPage(mav, page);
        return mav;
    }

    /**
     * Process the request to get view about course by custom filter
     *
     * @return modelAndView("admin/courses/courses")
     */
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public ModelAndView filter(@ModelAttribute(ROOT_MODEL) Course courseTemplate,
                               @RequestParam(value = "expertList", required = false) List<String> expertList) throws Exception {
        ModelAndView mav = new ModelAndView(ROOT + "/page/listcontent");
        if (expertList != null) {
            courseTemplate.setExperts(bindExperts(expertList));
        }
        if (courseTemplate.getName().equals("")) {
            courseTemplate.setName(null);
        }
        paginator.setFilterTemplate(courseTemplate);
        switchPage(mav, 1);
        return mav;
    }

    /**
     * Processes the request to delete by id
     * Url example: "/delete/1". Parse by pattern: "/delete/{courseId}"
     * If all is well, we get redirected to "course"
     *
     * @param courseCode course ID
     * @return redirect to "/admin/courses"
     */
    @RequestMapping(value = "/delete/{courseCode}", method = RequestMethod.GET)
    public String delete(@PathVariable String courseCode) {
        courseService.delete(courseCode);
        return "redirect:/" + "admin";
    }

    @RequestMapping(value = "/publish/{courseCode}", method = RequestMethod.GET)
    public String publish(@PathVariable String courseCode) {
        courseService.publish(courseService.findByCode(courseCode));
        return "redirect:/" + "admin";
    }

    /**
     * Process the request to get details about course by selected code
     * URL example: "/1". Parse by pattern: "/{code}"
     * if success go to view "admin/courses/course")
     *
     * @param courseCode course code
     * @return modelAndView("admin/courses/course")
     */
    @RequestMapping(value = "view/{courseCode}", method = RequestMethod.GET)
    public ModelAndView single(@PathVariable String courseCode) {
        ModelAndView mav = new ModelAndView(ROOT + "/page/courseContent");
        Course course = courseService.findByCode(courseCode);
        mav.addObject("course", course);
        return mav;
    }

    /**
     * Process the request to get create course form
     *
     * @return modelAndView("admin/courses/layout")
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView(ROOT + "/page/createContent");
        mav.addObject("validationRules", validationContext.get(Course.class));
        return mav;
    }

    /**
     * Process the request to post entered course in the form
     *
     * @param course course object
     * @return if all is OK the redirect to view new course or return to edit course
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute(ROOT_MODEL) Course course,
                       @RequestParam(value = "expertList", required = false) List<String> expertList) {
        course.setExperts(bindExperts(expertList));
        courseService.create(course);
        return "redirect:/admin/course/";
    }

    /**
     * Process the request to get edit course form
     *
     * @return modelAndView("admin/courses/layout")
     */
    @RequestMapping(value = "/{courseCode}/update", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable String courseCode) {
        ModelAndView mav = new ModelAndView(ROOT + "/page/updateContent");
        mav.getModelMap().addAttribute("course", courseService.findByCode(courseCode));
        mav.addObject("validationRules", validationContext.get(Course.class));
        return mav;
    }

    /**
     * Process the request to post entered course in the form
     *
     * @param course course object
     * @return if all is OK the redirect to view course or return to edit course
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute(ROOT_MODEL) Course course, @RequestParam(value = "expertList", required = false) List<String> expertList) {
        course.setId(courseService.findByCode(course.getCode()).getId());// TO DO: id must be already present
        course.setExperts(bindExperts(expertList));
        courseService.update(course);
        return "redirect: view/" + course.getCode();
    }

    protected Set<User> bindExperts(List<String> experts) {
        if (experts == null) return null;
        Set<User> courseExperts = new HashSet<>();
        for (String expert : experts) {
            String params[] = expert.split(" ");
            SearchCriteria userSearchCriteria = new SearchCriteria(User.class);
            userSearchCriteria.addUnitCriteria("email", params[2], SearchCriteria.RestrictionStrategy.EQ);
            courseExperts.addAll(userService.search(userSearchCriteria).getResults());
        }
        return courseExperts;
    }

    private void switchPage(ModelAndView mav, int page) {
        paginator.setCurrentPage(page);
        SearchResults<Course> results = courseService.search(paginator.getSearchCriteria());
        Page pageModel = paginator.getPage(page, results.getTotalResults());
        mav.addAllObjects(pageModel.createMap().byCurrentPage().byLastPage()
                .byNextPage().byPrevPage().byStartPage().toMap());
        mav.addObject("courses", results.getResults());
    }

    /**
     * Binding course conditions for entry into the form conclusions
     *
     * @param binder
     */
    @InitBinder(ROOT_MODEL)
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Collection.class, new CustomTagsEditor());
    }

    /**
     * Prepare collection categories as model attribute
     *
     * @return collection categories
     */
    @ModelAttribute("categories")
    public Collection<String> getCategories() {
        return CourseType.findAll();
    }

    @ModelAttribute("statuses")
    public Collection<String> getStats() {
        return CourseStatus.findAll();
    }


    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * Prepare course as model attribute
     *
     * @return course object
     */
    @ModelAttribute(value = ROOT_MODEL)
    public Course getCommandObject() {
        return new Course();
    }


}
