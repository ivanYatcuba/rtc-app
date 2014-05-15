package net.github.rtc.app.controller.admin;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Chernichenko Bogdan on 15.05.2014.
 */

@Controller("exportController")
@RequestMapping("admin/export")

public class ExportController {

    private static final String ROOT = "admin";
    //private static final String ROOT_MODEL = "export";


    @RequestMapping(value = "/exportCourses", method = RequestMethod.GET)
    public ModelAndView export() {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.addObject("content", "courses/content/exportCourses");
        return mav;
    }
}
