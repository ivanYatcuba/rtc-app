package net.github.rtc.app.controller.common;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.utils.datatable.CourseSearchResult;
import net.github.rtc.app.utils.datatable.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ivan on 28.03.14.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    @Autowired
    private CourseService courseService;

    /**
     * Request to main page, get three coming soon courses
     * @return ModelAndView("welcome/welcomeLayout")
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() {
        ModelAndView mav = new ModelAndView("welcome/welcomeLayout");


        SearchFilter searchFilter = new SearchFilter();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Calendar rightNow = Calendar.getInstance();

        searchFilter.setStartDate(sdf.format(rightNow.getTime()));
        searchFilter.setStatus(CourseStatus.PUBLISHED);
                searchFilter.setMaxResult(3);

        CourseSearchResult result = courseService.findByFilter(searchFilter);
        Collections.sort((List<Course>)result.getCourses(), new Comparator<Course>() {
            public int compare(Course course1, Course course2) {
                if (course1.getStartDate() == null || course2.getStartDate() == null)
                    return 0;
                return course1.getStartDate().compareTo(course2.getStartDate());
            }
        });
        mav.addObject("soonCourses", result.getCourses());
        mav.addObject("content","content/welcomeContent");
        return mav;
    }

    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
