package net.github.rtc.web.courses.controller;

import net.github.rtc.web.courses.model.Courses;
import net.github.rtc.web.courses.model.SearchFilter;
import net.github.rtc.web.courses.propertyeditors.CustomTagsEditor;
import net.github.rtc.web.courses.service.CategoryService;
import net.github.rtc.web.courses.service.CoursesService;
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
 * Controller for {@link net.github.rtc.web.courses.model.Courses}
 *
 * @author Vladislav Pikus
 */
@Controller("coursesController")
@RequestMapping("admin/courses")
public class CoursesController {

    private static final String ROOT = "admin/courses";
    private static final String ROOT_MODEL = "course";

    private CoursesService coursesService;

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Processes the request to view all courses page
     *
     * @return modelAndView("admin/courses/courses")
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        Collection<Courses> courses = coursesService.findAll();
        mav.addObject("courses", courses);
        mav.addObject("content", "listContent");
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
        return "redirect:/" + ROOT;
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
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        Courses course = coursesService.findByCode(courseCode);
        mav.addObject("course", course);
        mav.addObject("content", "courseContent");
        return mav;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public ModelAndView filter(@ModelAttribute("searchFilter") SearchFilter searchFilter) {
        ModelAndView mav = new ModelAndView(ROOT + "/courses");
        Collection<Courses> courses = coursesService.findByFilter(searchFilter.getMap());
        mav.addObject("courses", courses);
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.addObject("content", "createContent");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute(ROOT_MODEL) @Valid Courses course,
                             BindingResult bindingResult,
                             SessionStatus session) {
       if (bindingResult.hasErrors()) {
           ModelAndView mav = new ModelAndView(ROOT + "/layout");
           mav.addObject("content", "createContent");
           return mav;
        }
        course = coursesService.create(course);
        session.setComplete();
        return new ModelAndView("redirect:/" + ROOT + "/" + course.getCode());
    }

    @RequestMapping(value = "/{courseCode}/update", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable String courseCode) {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.getModelMap().addAttribute("course", coursesService.findByCode(courseCode));
        mav.addObject("content", "updateContent");
        return mav;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute(ROOT_MODEL) Courses course,
                         BindingResult bindingResult,
                         SessionStatus session) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView(ROOT + "/layout");
            mav.addObject("content", "updateContent");
            return mav;
        }
        coursesService.update(course);
        session.setComplete();
        return new ModelAndView("redirect:/" + ROOT + "/" + course.getCode());
    }

    @InitBinder(ROOT_MODEL)
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Collection.class, new CustomTagsEditor());
    }

    @ModelAttribute("categories")
    public Collection<String> getCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("searchFilter")
    public SearchFilter getFilter() {
        return new SearchFilter();
    }


    @ModelAttribute(value = ROOT_MODEL)
    public Courses getCommandObject() {
        return new Courses();
    }
}
