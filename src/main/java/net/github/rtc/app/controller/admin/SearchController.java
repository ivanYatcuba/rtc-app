package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import net.github.rtc.app.model.report.ExportFormat;
import net.github.rtc.app.model.report.ReportClasses;
import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserStatus;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.news.NewsService;
import net.github.rtc.app.service.report.ReportService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.datatable.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by Berdniky on 15.10.2014.
 */

@Controller
@RequestMapping(value = "/admin")
public class SearchController {

    private static final String ROOT = "portal/admin";
    private static final String STRING_SEARCH_PAGE = "/search/tables";
    private static final String STRING_NEWS = "news";
    private static final String STRING_NEWS_STATUSES = "newsStatuses";
    private static final String STRING_USER_STATUSES = "userStatuses";
    private static final String STRING_COURSE_STATUSES = "courseStatuses";
    private static final String STRING_NEWS_FILTER = "newsFilter";
    private static final String STRING_TYPES = "types";
    private static final String STRING_COURSE_FILTER = "courseFilter";
    private static final String STRING_COURSE_CATIGORIES = "courseCategories";
    private static final String STRING_USERS = "users";
    private static final String STRING_USER_AUTHORITIES = "userAuthorities";
    private static final String STRING_USER_FILTER = "userFilter";
    private static final String STRING_COURSES = "courses";
    private static final String STRING_REPORTS = "reports";
    private static final String STRING_REPORT_FILTER = "reportFilter";
    private static final String STRING_EXPERTS = "experts";
    private static final String MENU_ITEM = "menuItem";
    private static final String STRING_ACTIVITY = "activity";
    private static final String STRING_ACTIVITY_FILTER = "activityFilter";
    @Autowired
    private NewsService newsService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/search/{menuItem}", method = RequestMethod.GET)
    public ModelAndView searchPagewithParam(@PathVariable(MENU_ITEM) final String menuItem) {
        final ModelAndView mav = new ModelAndView("redirect:" + "/admin/search");
        mav.addObject(MENU_ITEM, menuItem);
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchPage(@RequestParam(value = MENU_ITEM, required = false) String menuItem) {
        final ModelAndView mav = new ModelAndView(ROOT + "/search/searchPage");
        mav.addObject(MENU_ITEM, menuItem);
        return mav;
    }

    @RequestMapping(value = "/activityTable", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView getActivityTable(@ModelAttribute(STRING_ACTIVITY_FILTER) final ActivitySearchFilter activityFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + STRING_SEARCH_PAGE + "/activitySearchTable");
        return mav;
    }

    @RequestMapping(value = "/newsTable", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView getNewsTable(@ModelAttribute(STRING_NEWS_FILTER) final NewsSearchFilter newsFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + STRING_SEARCH_PAGE + "/newsSearchTable");
        final SearchResults<News> results = newsService.search(newsFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_NEWS, results.getResults());
        return mav;
    }

    @RequestMapping(value = "/courseTable", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView getCourseTable(@ModelAttribute(STRING_COURSE_FILTER) final CourseSearchFilter courseFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + STRING_SEARCH_PAGE + "/courseSearchTable");
        final SearchResults<Course> results = courseService.search(courseFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_COURSES, results.getResults());
        mav.addObject(STRING_TYPES, CourseType.findAll());
        mav.addObject(STRING_COURSE_STATUSES, getCourseStatuses());
        mav.addObject(STRING_COURSE_FILTER, courseFilter);
        return mav;
    }

    @RequestMapping(value = "/userTable", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView getUserTable(@ModelAttribute(STRING_USER_FILTER) final UserSearchFilter userFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + STRING_SEARCH_PAGE + "/userSearchTable");
        final SearchResults<User> results = userService.search(userFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_USERS, results.getResults());
        return mav;
    }

    @RequestMapping(value = "/reportTable", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView getReportTable(@ModelAttribute(STRING_REPORT_FILTER) final ReportSearchFilter reportFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + STRING_SEARCH_PAGE + "/reportSearchTable");
        final SearchResults<ReportDetails> results = reportService.search(reportFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_REPORTS, results.getResults());
        return mav;
    }

    @ModelAttribute(STRING_ACTIVITY_FILTER)
    public ActivitySearchFilter getActivitySearchFilter() {
        return  new ActivitySearchFilter();
    }

    @ModelAttribute(STRING_NEWS_FILTER)
    public NewsSearchFilter getNewsSearchFilter() {
        return new NewsSearchFilter();
    }

    @ModelAttribute(STRING_COURSE_FILTER)
    public CourseSearchFilter getCourseSearchFilter() {
        return new CourseSearchFilter();
    }

    @ModelAttribute(STRING_USER_FILTER)
    public UserSearchFilter getUserSearchFilter() {
        return new UserSearchFilter();
    }

    @ModelAttribute(STRING_REPORT_FILTER)
    public ReportSearchFilter getReportSearchFilter() {
        return new ReportSearchFilter();
    }

    @ModelAttribute(STRING_NEWS_STATUSES)
    public Collection<String> getNewsStatuses() {
        return NewsStatus.findAll();
    }

    @ModelAttribute(STRING_USER_STATUSES)
    public Collection<String> getUserStatuses() {
        return UserStatus.findAll();
    }

    @ModelAttribute(STRING_COURSE_STATUSES)
    public Collection<String> getCourseStatuses() {
        return CourseStatus.findAll();
    }

    @ModelAttribute(STRING_USER_AUTHORITIES)
    public Collection<String> getAuthorities() {
        return RoleType.findAll();
    }

    @ModelAttribute("reportFormats")
    public List<String> getFormats() {
        return ExportFormat.findAll();
    }

    @ModelAttribute("reportStats")
    public List<String> getStats() {

        final List<String> stats = new ArrayList<>();
        stats.add(CourseStatus.DRAFT.name());
        stats.add(CourseStatus.PUBLISHED.name());
        return stats;
    }

    @ModelAttribute("reportTypes")
    public ReportClasses[] getTypes() {
        return ReportClasses.values();
    }

    @ModelAttribute(STRING_COURSE_CATIGORIES)
    public Map<String, String> getCategories() {
        final Map<String, String> categories = new HashMap<>();
        for (CourseType type : CourseType.findAll()) {
            categories.put(type.name(), type.toString());
        }
        return categories;
    }

    @ModelAttribute(STRING_EXPERTS)
    public Map<String, String> getExpertUsers() {
        final Map<String, String> expertMap = new HashMap<>();
        for (User u : userService.getUserByRole(RoleType.ROLE_EXPERT)) {
            expertMap.put(u.getCode(), u.toString());
        }
        return expertMap;
    }

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        return userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
