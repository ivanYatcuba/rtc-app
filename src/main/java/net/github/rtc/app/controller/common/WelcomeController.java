package net.github.rtc.app.controller.common;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.utils.datatable.FilterSettings;
import net.github.rtc.app.utils.datatable.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView welcome() throws Exception {
        ModelAndView mav = new ModelAndView("welcome/welcomeLayout");
        FilterSettings settings = new FilterSettings(Course.class);
        settings.addOption("startDate", SearchCriteria.RestrictionStrategy.GE);
        settings.addOption("status",  SearchCriteria.RestrictionStrategy.EQ);
        settings.setOrder("startDate", SearchCriteria.SortOrder.ASC);
        Course sample = new Course();
        sample.setStartDate(new Date());
        sample.setStatus(CourseStatus.PUBLISHED);
        SearchCriteria searchCriteria = settings.buildSearchCriteria(sample);
        searchCriteria.setPageSize(3);
        mav.addObject("soonCourses",courseService.search(searchCriteria).getResults());
        mav.addObject("content","content/welcomeContent");
        return mav;
    }

    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
