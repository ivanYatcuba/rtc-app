package net.github.rtc.web.courses.controller;

import net.github.rtc.web.courses.model.Courses;
import net.github.rtc.web.courses.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * Controller for {@link net.github.rtc.web.courses.model.Courses}
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
     * Processes the request to view all courses page
     *
     * @return modelAndView("courses/courses")
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("courses/courses");
        Collection<Courses> courses = service.findAll();
        mav.addObject("courses", courses);
        return mav;
    }

    /**
     * Processes the request to delete by id
     * Url example: "/delete/1". Parse by pattern: "/delete/{courseId}"
     * If all is well, we get redirected to "course"
     *
     * @param courseId course ID
     * @return redirect to "/course"
     */
    @RequestMapping(value="/delete/{courseId}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer courseId) {
        service.delete(courseId);
        return "redirect:/courses";
    }
}
