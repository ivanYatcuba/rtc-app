package net.github.rtc.app.controller.admin;


import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.service.NewsService;
import net.github.rtc.app.utils.datatable.search.NewsSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping(value = "/admin/news/")
public class NewsController {

    private static final String ROOT = "portal/admin";
    private static final String STRING_TYPES = "types";
    private static final String STRING_STATUSES = "statuses";
    private static final String STRING_FILTER_NEWS = "filterNews";
    private static final String STRING_NEWS = "news";

    @Autowired
    private NewsService newsService;

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


    @RequestMapping(value = "/news/create", method = RequestMethod.GET)
    public ModelAndView create() {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/pageCreateNews");
        return mav;
    }
    @ModelAttribute(STRING_STATUSES)
    public Collection<String> getStatuses() {
        return CourseStatus.findAll();
    }

}
