package rtc.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ivan on 28.03.14.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() {
        ModelAndView mav = new ModelAndView("welcome/welcomeLayout");
        return mav;
    }
}
