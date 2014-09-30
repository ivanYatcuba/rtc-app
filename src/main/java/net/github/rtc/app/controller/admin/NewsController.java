package net.github.rtc.app.controller.admin;


import net.github.rtc.app.model.news.News;
import net.github.rtc.app.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class NewsController {

    private static final String ROOT = "portal/admin";

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ModelAndView viewAll() {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/pageListNews");
        final List<News> news = newsService.findAll();
        mav.addObject("news", news);
        return mav;
    }

    @RequestMapping(value = "/news/create", method = RequestMethod.GET)
    public ModelAndView create() {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/pageCreateNews");
        return mav;
    }

}
