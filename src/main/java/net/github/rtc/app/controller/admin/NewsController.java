package net.github.rtc.app.controller.admin;


import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.news.NewsService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller("newsController")
@RequestMapping(value = "/admin/news")
public class NewsController implements MenuItem {

    public static final String STRING_NEWS = "news";
    private static final String STRING_STATUSES = "statuses";
    private static final String STRING_FILTER_NEWS = "filterNews";
    private static final String STRING_REDIRECT_VIEW = "redirect:/admin/news/";
    private static final String STRING_VALIDATION_RULES = "validationRules";
    private static final String STRING_REDIRECT = "redirect:";
    private static final String STRING_ADMIN_SEARCH = "/admin/search";

    private static final String ROOT = "portal/admin";
    private static final String UPDATE_VIEW = "/news/newsUpdate";
    private static final String CREATE_VIEW = "/news/newsCreate";
    private static final String DETAILS_VIEW = "/news/newsDetails";

    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Autowired
    private DateService dateService;
    @Autowired
    private ValidationContext validationContext;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        final ModelAndView mav = new ModelAndView(ROOT + CREATE_VIEW);
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(News.class));
        return mav;
    }

    /**
     * Process the request to get details about news by selected code
     * URL example: "/1". Parse by pattern: "/{code}"
     * if success go to view "admin/news/view")
     *
     * @param newsCode news code
     * @return modelAndView("admin/news/")
     */
    @RequestMapping(value = "/{newsCode}", method = RequestMethod.GET)
    public ModelAndView single(@PathVariable final String newsCode) {
        final ModelAndView mav = new ModelAndView(ROOT + DETAILS_VIEW);
        final News news = newsService.findByCode(newsCode);
        mav.addObject(STRING_NEWS, news);
        return mav;
    }

    /**
     * Process the request to get edit news form
     *
     * @return modelAndView("admin/news/layout")
     */
    @RequestMapping(value = "update/{newsCode}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable final String newsCode) {
        final ModelAndView mav = new ModelAndView(ROOT + UPDATE_VIEW);
        mav.getModelMap().addAttribute(STRING_NEWS, newsService.findByCode(newsCode));
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(News.class));
        return mav;
    }

    /**
     * Process the request to post entered news in the form
     *
     * @param news news object
     * @return the redirect to view news
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(
      @ModelAttribute(STRING_NEWS) final News news, @RequestParam(required = false) final boolean publish) {
        final News newsTmp = newsService.findByCode(news.getCode());
        news.setCreateDate(newsTmp.getCreateDate());
        if (publish) {
            news.setPublishDate(dateService.getCurrentDate());
        }
        news.setAuthor(newsTmp.getAuthor());
        news.setStatus(publish ? NewsStatus.PUBLISHED : newsTmp.getStatus());
        newsService.update(news);
        return STRING_REDIRECT_VIEW + news.getCode();
    }

    @RequestMapping(value = "/delete/{newsCode}", method = RequestMethod.GET)
    public String deleteByCode(@PathVariable final String newsCode) {
        newsService.deleteByCode(newsCode);
        return STRING_REDIRECT + STRING_ADMIN_SEARCH;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String setStatusForRemoval(@RequestParam final String newsCode) throws Exception {
        newsService.deleteByCode(newsCode);
        return STRING_REDIRECT + STRING_ADMIN_SEARCH;
    }

    @RequestMapping(value = "/publish/{newsCode}", method = RequestMethod.GET)
    public String publishByCode(@PathVariable final String newsCode) {
        final News news = newsService.findByCode(newsCode);
        news.setPublishDate(dateService.getCurrentDate());
        news.setStatus(NewsStatus.PUBLISHED);
        newsService.update(news);
        return STRING_REDIRECT + STRING_ADMIN_SEARCH;
    }

    @ModelAttribute(STRING_STATUSES)
    public Collection<String> getStatuses() {
        return NewsStatus.findAll();
    }

    @ModelAttribute(STRING_NEWS)
    public News getNews() {

        return new News();
    }

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        return userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /**
     * Process the request to post entered course in the form
     *
     * @param news news object
     * @return if all is OK the redirect to view new course or return to edit
     * course
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
      @ModelAttribute(STRING_NEWS) final News news, @RequestParam(required = false) final boolean publish) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String name = auth.getName(); //get logged in username
        news.setCreateDate(dateService.getCurrentDate());
        news.setAuthor(userService.loadUserByUsername(name));
        if (publish) {
            news.setStatus(NewsStatus.PUBLISHED);
        }
        newsService.create(news);
        return STRING_REDIRECT_VIEW + news.getCode();
    }

    @Override
    public String getMenuItem() {
        return STRING_NEWS;
    }
}
