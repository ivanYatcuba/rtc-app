package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.service.NewsService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.datatable.search.NewsSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import net.github.rtc.app.utils.propertyeditors.CustomStringEditor;
import net.github.rtc.app.utils.propertyeditors.CustomTagsEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller("newsController")
@RequestMapping(value = "/admin/news")
public class NewsController {

    private static final String ROOT = "portal/admin";
    private static final String STRING_TYPES = "types";
    private static final String STRING_STATUSES = "statuses";
    private static final String STRING_FILTER_NEWS = "filterNews";
    private static final String STRING_NEWS = "news";

    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody ModelAndView viewAll(@ModelAttribute("filterNews") final NewsSearchFilter filterNews) {
        final ModelAndView mav = new ModelAndView(ROOT + "/news/content/search/searchTable");
        final SearchResults results = newsService.search(filterNews);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_NEWS, results.getResults());
        mav.addObject(STRING_STATUSES, getStatuses());
//        mav.addObject(STRING_FILTER_NEWS, filterNews);
        return mav;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView viewAll() {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/pageListNews");
        final NewsSearchFilter newsFilter = new NewsSearchFilter();
        newsFilter.setPage(1);
        final SearchResults results = newsService.search(newsFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_NEWS, results.getResults());
        mav.addObject(STRING_STATUSES, getStatuses());
        mav.addObject(STRING_FILTER_NEWS, newsFilter);
        return mav;
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/pageCreateNews");
        return mav;
    }

    /**
     * Binding course conditions for entry into the form conclusions
     *
     * @param binder
     */
    @InitBinder(STRING_NEWS)
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "tags", new CustomTagsEditor());
        binder.registerCustomEditor(List.class, STRING_TYPES, new CustomStringEditor());
    }

    @InitBinder(STRING_FILTER_NEWS)
    public void initFilterBinder(final WebDataBinder binder) {
        initBinder(binder);
    }

    @ModelAttribute(STRING_STATUSES)
    public Collection<String> getStatuses() {
        return CourseStatus.findAll();
    }

    @ModelAttribute("news")
    public News getNews() {

        return new News();
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
            @ModelAttribute(STRING_NEWS) final News news,
            @RequestParam(value = "expertList",
                    required = false) final List<String> expertList) {
        news.setCreateDate(new Date());
        news.setAuthor(userService.loadUserByUsername("admin"));
        newsService.create(news);
        return "redirect:/admin/news/list";
    }

}
