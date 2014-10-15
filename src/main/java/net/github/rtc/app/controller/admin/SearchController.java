package net.github.rtc.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Berdniky on 15.10.2014.
 */

@Controller
@RequestMapping(value = "/admin/developed")
public class SearchController {

    private static final String ROOT = "portal/admin";
    private static final String STRING_SEARCH_PAGE = "/search/tables";

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchPage() {
        return ROOT + "/search/searchPage";
    }

    @RequestMapping(value = "/newsTable", method = RequestMethod.POST)
    public ModelAndView getNewsTable() {
        final ModelAndView mav = new ModelAndView(ROOT + STRING_SEARCH_PAGE + "/newsSearchTable");
        return mav;
    }

    @RequestMapping(value = "/courseTable", method = RequestMethod.POST)
    public ModelAndView getCourseTable() {
        final ModelAndView mav = new ModelAndView();
        return mav;
    }

    @RequestMapping(value = "/userTable", method = RequestMethod.POST)
    public ModelAndView getUserTable() {
        final ModelAndView mav = new ModelAndView();
        return mav;
    }

    @RequestMapping(value = "/reportTable", method = RequestMethod.POST)
    public ModelAndView getReportTable() {
        final ModelAndView mav = new ModelAndView();
        return mav;
    }

}
