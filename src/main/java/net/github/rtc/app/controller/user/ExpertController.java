package net.github.rtc.app.controller.user;

import net.github.rtc.app.model.CourseDto;
import net.github.rtc.app.model.SearchFilter;
import net.github.rtc.app.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller("expertController")
@RequestMapping("/expert")
public class ExpertController {

    private static final String ROOT = "user";
    //private static final String ROOT_MODEL = "user" ;

    @Autowired
    private CoursesService coursesService;

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public ModelAndView expertCourses() {
        ModelAndView mav = new ModelAndView("user" + "/layout");
        mav.addObject("content", "expertCourses");
        return mav;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView userCourses() {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        Map<String, String> map = new HashMap<String, String>();
        CourseDto dto = coursesService.findByFilter(getFilter().createQuery(map).toString());
        mav.addObject("courses", dto.getCourses());
        mav.addObject("content", "expertAll");
        return mav;
    }

    @ModelAttribute("searchFilter")
    public SearchFilter getFilter() {
        return new SearchFilter();
    }
}