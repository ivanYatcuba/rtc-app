package net.github.rtc.web.courses.controller;

import net.github.rtc.web.courses.model.Courses;
import net.github.rtc.web.courses.propertyeditors.CustomTagsEditor;
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
import java.util.Arrays;
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

    private CoursesService service;

    @Autowired
    public void setService(CoursesService service) {
        this.service = service;
    }

    /**
     * Processes the request to view all courses page
     *
     * @return modelAndView("admin/courses/courses")
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("admin/courses/courses");
        Collection<Courses> courses = service.findAll();
        mav.addObject("courses", courses);
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
        service.delete(courseCode);
        return "redirect:/admin/courses";
    }

    /**
     * Process the request to get deteail about course by selected code
     * URL example: "/1". Parse by pattern: "/{code}"
     * if success go to view "admin/courses/course")
     *
     * @param courseCode course code
     * @return modelAndView("admin/courses/course")
     */
    @RequestMapping(value = "/{courseCode}", method = RequestMethod.GET)
    public ModelAndView single(@PathVariable String courseCode) {
        ModelAndView mav = new ModelAndView("admin/courses/course");
        Courses course = service.findByCode(courseCode);
        mav.addObject("course", course);
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("admin/courses/create_courses");
        Collection<String> categories = Arrays.asList("DEV", "BA", "QA");
        mav.addObject("categories", categories);
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("course") @Valid Courses course,
                             BindingResult bindingResult,
                             SessionStatus session) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("admin/courses/create_courses");
            Collection<String> categories = Arrays.asList("DEV", "BA", "QA");
            mav.addObject("categories", categories);
            return mav;
        }
        course = service.create(course);
        session.setComplete();
        return new ModelAndView("redirect:/admin/courses/" + course.getCode());
    }

    @RequestMapping(value = "/{courseCode}/update", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable String courseCode) {
        ModelAndView mav = new ModelAndView("admin/courses/update");
        mav.getModelMap().addAttribute("course", service.findByCode(courseCode));
        Collection<String> categories = Arrays.asList("DEV", "BA", "QA");
        mav.addObject("categories", categories);
        return mav;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("course") Courses course,
                         BindingResult bindingResult,
                         SessionStatus session) {
        service.update(course);
        session.setComplete();
        return "redirect:/courses/" + course.getCode();
    }

    @InitBinder("course")
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Collection.class, new CustomTagsEditor());
    }

    @ModelAttribute(value = "course")
    public Courses getCommandObject() {
        return new Courses();
    }
}
