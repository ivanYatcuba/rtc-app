package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.service.NewsService;
import net.github.rtc.app.service.ReportService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.datatable.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * Created by Berdniky on 15.10.2014.
 */

@Controller
@RequestMapping(value = "/admin/developed")
public class SearchController {

    private static final String ROOT = "portal/admin";
    private static final String STRING_SEARCH_PAGE = "/search/tables";
    private static final String STRING_NEWS = "news";
    private static final String STRING_STATUSES = "statuses";
    private static final String STRING_NEWS_FILTER = "newsFilter";
    private static final String STRING_TYPES = "types";
    private static final String STRING_COURSE_FILTER = "courseFilter";
    private static final String STRING_USERS = "users";
    private static final String STRING_AUTHORITIES = "authorities";
    private static final String STRING_USER_FILTER = "userFilter";
    private static final String STRING_COURSES = "courses";
    private static final String STRING_REPORTS = "reports";



    @Autowired
    private NewsService newsService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchPage() {
        return ROOT + "/search/searchPage";
    }

    @RequestMapping(value = "/newsTable", method = RequestMethod.POST)
    public ModelAndView getNewsTable (@ModelAttribute("newsFilter") final NewsSearchFilter newsFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + STRING_SEARCH_PAGE + "/newsSearchTable");
        final SearchResults<News> results = newsService.search(newsFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_NEWS, results.getResults());
        return mav;
    }

    @RequestMapping(value = "/courseTable", method = RequestMethod.POST)
    public ModelAndView getCourseTable(@ModelAttribute("courseFilter") final CourseSearchFilter courseFilter) {
        final ModelAndView mav = new ModelAndView();
        final SearchResults<Course> results = courseService.search(courseFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_COURSES, results.getResults());
        return mav;
    }

    @RequestMapping(value = "/userTable", method = RequestMethod.POST)
    public ModelAndView getUserTable(@ModelAttribute("userFilter") final UserSearchFilter userFilter) {
        final ModelAndView mav = new ModelAndView();
        final SearchResults<User> results = userService.search(userFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_USERS, results.getResults());
        return mav;
    }

    @RequestMapping(value = "/reportTable", method = RequestMethod.POST)
    public ModelAndView getReportTable(@ModelAttribute("reportFilter") final ReportSearchFilter reportFilter) {
        final ModelAndView mav = new ModelAndView();
        final SearchResults<ReportDetails> results = reportService.search(reportFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_REPORTS, results.getResults());
        return mav;
    }

    @ModelAttribute("newsFilter")
    public NewsSearchFilter getNewsSearchFilter() {
        return new NewsSearchFilter();
    }

    @ModelAttribute("courseFilter")
    public CourseSearchFilter getCourseSearchFilter() {
        return new CourseSearchFilter();
    }

    @ModelAttribute("userFilter")
    public UserSearchFilter getUserSearchFilter() {
        return new UserSearchFilter();
    }

    @ModelAttribute("reportFilter")
    public ReportSearchFilter getReportSearchFilter() {
        return new ReportSearchFilter();
    }

    @ModelAttribute(STRING_STATUSES)
    public Collection<String> getStatuses() {
        return NewsStatus.findAll();
    }

    @ModelAttribute(STRING_AUTHORITIES)
    public Collection<String> getAuthorities() {
        return RoleType.findAll();
    }

}
