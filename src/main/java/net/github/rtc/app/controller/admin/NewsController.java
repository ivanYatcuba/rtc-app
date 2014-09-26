package net.github.rtc.app.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class NewsController {

    private static final String ROOT = "portal/admin";

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ModelAndView viewAll(){
        final ModelAndView mav = new ModelAndView(ROOT + "/page/pageListNews");
        return mav;
    }

    @RequestMapping(value = "/createNews", method = RequestMethod.GET)
    public ModelAndView create(){
        final ModelAndView mav = new ModelAndView(ROOT + "/page/pageCreateNews");
        return mav;
    }


}
