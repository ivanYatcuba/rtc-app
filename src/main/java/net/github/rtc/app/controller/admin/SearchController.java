package net.github.rtc.app.controller.admin;

import net.github.rtc.app.controller.common.ResourceNotFoundException;
import net.github.rtc.app.model.activity.Activity;
import net.github.rtc.app.model.activity.ActivityAction;
import net.github.rtc.app.model.activity.ActivityEntity;
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
import net.github.rtc.app.service.activity.ActivityService;
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

@Controller
@RequestMapping(value = "/admin")
public class SearchController {

    private static final String ROOT = "portal/admin";
    private static final String SEARCH_PAGE = "/search/tables";
    private static final String NEWS = "news";
    private static final String NEWS_STATUSES = "newsStatuses";
    private static final String USER_STATUSES = "userStatuses";
    private static final String COURSE_STATUSES = "courseStatuses";
    private static final String NEWS_FILTER = "newsFilter";
    private static final String TYPES = "types";
    private static final String COURSE_FILTER = "courseFilter";
    private static final String COURSE_CATIGORIES = "courseCategories";
    private static final String USERS = "users";
    private static final String USER_AUTHORITIES = "userAuthorities";
    private static final String USER_FILTER = "userFilter";
    private static final String COURSES = "courses";
    private static final String REPORTS = "reports";
    private static final String REPORT_FILTER = "reportFilter";
    private static final String EXPERTS = "experts";
    private static final String MENU_ITEM = "menuItem";
    private static final String ACTIVITY_FILTER = "activityFilter";
    private static final String ACTIVITY_ENTITIES = "activityEntities";
    private static final String ACTIVITY_ACTIONS = "activityActions";
    private static final String ACTIVITIES = "activities";
    @Autowired
    private NewsService newsService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/search/{menuItem}", method = RequestMethod.GET)
    public ModelAndView searchPageWithParam(@PathVariable(MENU_ITEM) final String menuItem) {
         if (!MenuItems.contains(menuItem)) {
             throw new ResourceNotFoundException();
         }
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
    ModelAndView getActivityTable(@ModelAttribute(ACTIVITY_FILTER) final ActivitySearchFilter activityFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + SEARCH_PAGE + "/activitySearchTable");
        final SearchResults<Activity> results = activityService.search(activityFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(ACTIVITIES, results.getResults());
        return mav;
    }

    @RequestMapping(value = "/newsTable", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView getNewsTable(@ModelAttribute(NEWS_FILTER) final NewsSearchFilter newsFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + SEARCH_PAGE + "/newsSearchTable");
        final SearchResults<News> results = newsService.search(newsFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(NEWS, results.getResults());
        return mav;
    }

    @RequestMapping(value = "/courseTable", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView getCourseTable(@ModelAttribute(COURSE_FILTER) final CourseSearchFilter courseFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + SEARCH_PAGE + "/courseSearchTable");
        final SearchResults<Course> results = courseService.search(courseFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(COURSES, results.getResults());
        mav.addObject(TYPES, CourseType.findAll());
        mav.addObject(COURSE_STATUSES, getCourseStatuses());
        mav.addObject(COURSE_FILTER, courseFilter);
        return mav;
    }

    @RequestMapping(value = "/userTable", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView getUserTable(@ModelAttribute(USER_FILTER) final UserSearchFilter userFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + SEARCH_PAGE + "/userSearchTable");
        final SearchResults<User> results = userService.search(userFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(USERS, results.getResults());
        return mav;
    }

    @RequestMapping(value = "/reportTable", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView getReportTable(@ModelAttribute(REPORT_FILTER) final ReportSearchFilter reportFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + SEARCH_PAGE + "/reportSearchTable");
        final SearchResults<ReportDetails> results = reportService.search(reportFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(REPORTS, results.getResults());
        return mav;
    }

    @ModelAttribute(ACTIVITY_FILTER)
    public ActivitySearchFilter getActivitySearchFilter() {
        return  new ActivitySearchFilter();
    }

    @ModelAttribute(NEWS_FILTER)
    public NewsSearchFilter getNewsSearchFilter() {
        return new NewsSearchFilter();
    }

    @ModelAttribute(COURSE_FILTER)
    public CourseSearchFilter getCourseSearchFilter() {
        return new CourseSearchFilter();
    }

    @ModelAttribute(USER_FILTER)
    public UserSearchFilter getUserSearchFilter() {
        return new UserSearchFilter();
    }

    @ModelAttribute(REPORT_FILTER)
    public ReportSearchFilter getReportSearchFilter() {
        return new ReportSearchFilter();
    }

    @ModelAttribute(NEWS_STATUSES)
    public Collection<String> getNewsStatuses() {
        return NewsStatus.findAll();
    }

    @ModelAttribute(USER_STATUSES)
    public Collection<String> getUserStatuses() {
        return UserStatus.findAll();
    }

    @ModelAttribute(COURSE_STATUSES)
    public Collection<String> getCourseStatuses() {
        return CourseStatus.findAll();
    }

    @ModelAttribute(USER_AUTHORITIES)
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

    @ModelAttribute(COURSE_CATIGORIES)
    public Map<String, String> getCategories() {
        final Map<String, String> categories = new HashMap<>();
        for (CourseType type : CourseType.findAll()) {
            categories.put(type.name(), type.toString());
        }
        return categories;
    }

    @ModelAttribute(ACTIVITY_ENTITIES)
    public Map<String, String> getActivityEntities() {
        final Map<String, String> entities = new HashMap<>();
        for (ActivityEntity entity: ActivityEntity.findAll()) {
            entities.put(entity.name(), entity.toString());
        }
        return entities;
    }

    @ModelAttribute(ACTIVITY_ACTIONS)
    public Map<String, String> getActivityAction() {
        final Map<String, String> actions = new HashMap<>();
        for (ActivityAction action : ActivityAction.findAll()) {
            actions.put(action.name(), action.toString());
        }
        return actions;
    }

    @ModelAttribute(EXPERTS)
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
