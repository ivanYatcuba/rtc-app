package net.github.rtc.app.controller.expert;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("expertController")
@RequestMapping("/expert")
public class ExpertController {

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public ModelAndView expertCourses() {
        ModelAndView mav = new ModelAndView("user" + "/layout");
        mav.addObject("content", "expertCourses");
        return mav;
    }
}
