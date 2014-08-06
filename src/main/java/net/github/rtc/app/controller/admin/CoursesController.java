package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.utils.datatable.CourseSearchResult;
import net.github.rtc.app.utils.datatable.Page;
import net.github.rtc.app.utils.datatable.SearchFilter;
import net.github.rtc.app.utils.propertyeditors.CustomTagsEditor;
import net.github.rtc.app.service.CategoryService;
import net.github.rtc.app.service.CoursesService;
import net.github.rtc.app.utils.Paginator;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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

    private CoursesService coursesService;

    @Autowired
    private ValidationContext validationContext;

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private Paginator paginator;

    @Autowired
    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    /**
     * Processes the request to view all courses page
     *
     * @return modelAndView("admin/courses/courses")
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(required = true, defaultValue = "1") int page) {
        ModelAndView mav = new ModelAndView(ROOT + "/page/listcontent");

        getFilter().setPageNumber(page - 1);
        getFilter().setMaxResult(paginator.getMaxPerPage());
        CourseSearchResult result = coursesService.findByFilter(getFilter());

        Page pageModel = paginator.getPage(page, result.getTotalCount());
        mav.addAllObjects(pageModel.createMap().byCurrentPage().byLastPage()
                .byNextPage().byPrevPage().byStartPage().toMap());

        mav.addObject("courses", result.getCourses());
        mav.addObject("isFiltered", false);
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
        coursesService.delete(courseCode);
        return "redirect:/" + "admin";
    }

    @RequestMapping(value = "/publish/{courseCode}", method = RequestMethod.GET)
    public String publish(@PathVariable String courseCode) {
        coursesService.publish(coursesService.findByCode(courseCode));
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
    @RequestMapping(value = "/{courseCode}", method = RequestMethod.GET)
    public ModelAndView single(@PathVariable String courseCode) {
        ModelAndView mav = new ModelAndView(ROOT + "/page/courseContent");
        Course course = coursesService.findByCode(courseCode);
        mav.addObject("course", course);
        return mav;
    }

    /**
     * Process the request to get view about course by custom filter
     *
     * @param searchFilter searchFilter model
     * @return modelAndView("admin/courses/courses")
     */
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public ModelAndView filter(@ModelAttribute("searchFilter") SearchFilter searchFilter,
                               @RequestParam(required = true, defaultValue = "1") int page) {
        ModelAndView mav = new ModelAndView(ROOT + "/page/listcontent");

        searchFilter.setPageNumber(page - 1);
        searchFilter.setMaxResult(paginator.getMaxPerPage());
        CourseSearchResult result = coursesService.findByFilter(searchFilter);

        Page pageModel = paginator.getPage(page, result.getTotalCount());
        mav.addAllObjects(pageModel.createMap().byCurrentPage().byLastPage()
                .byNextPage().byPrevPage().byStartPage().toMap());

        mav.addObject("courses", result.getCourses());
        mav.addObject("isFiltered", false);
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
     * @param course        course object
     * @param bindingResult binding course result
     * @param session       current session
     * @return if all is OK the redirect to view new course or return to edit course
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute(ROOT_MODEL) @Valid Course course,
                             BindingResult bindingResult,
                             SessionStatus session) {
        if (bindingResult.hasErrors()) {
            return  new ModelAndView(ROOT + "/page/createContent");
        }
        coursesService.create(course);
        session.setComplete();
        return new ModelAndView("redirect:/admin/course/");
    }

    /**
     * Process the request to get edit course form
     *
     * @return modelAndView("admin/courses/layout")
     */
    @RequestMapping(value = "/{courseCode}/update", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable String courseCode) {
        ModelAndView mav = new ModelAndView(ROOT + "/page/updateContent");
        mav.getModelMap().addAttribute("course", coursesService.findByCode(courseCode));
        mav.addObject("validationRules", validationContext.get(Course.class));
        return mav;
    }

    /**
     * Process the request to post entered course in the form
     *
     * @param course        course object
     * @param bindingResult binding course result
     * @param session       current session
     * @return if all is OK the redirect to view course or return to edit course
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute(ROOT_MODEL) Course course,
                               BindingResult bindingResult,
                               SessionStatus session) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(ROOT + "/page/updateContent");
        }
        coursesService.update(course);
        session.setComplete();
        return new ModelAndView("redirect:" + course.getCode());
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
        return categoryService.findAll();
    }

    /**
     * Prepare searchFilter as model attribute
     *
     * @return searchFilter object
     */
    @ModelAttribute("searchFilter")
    public SearchFilter getFilter() {
        return new SearchFilter();
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
