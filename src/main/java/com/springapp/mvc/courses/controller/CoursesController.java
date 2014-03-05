package com.springapp.mvc.courses.controller;

import com.springapp.mvc.courses.model.Courses;
import com.springapp.mvc.courses.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * Courses controlle
 *
 * @author Vladislav Pikus
 */
@Controller("coursesController")
@RequestMapping("/courses")
public class CoursesController {

    private CoursesService service;

    @Autowired
    public void setService(CoursesService service) {
        this.service = service;
    }

    /**
     * Will be show a all courses page
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("courses/courses");
        Collection<Courses> courses = service.findAll();
        mav.addObject("courses", courses);
        return mav;
    }
}
