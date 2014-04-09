package rtc.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Vladislav Pikus
 */
@Controller
public class LoginController {
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login(ModelMap model) {
        ModelAndView mav = new ModelAndView("welcome/welcomeLayout");
        mav.addObject("content","/user/login");
        return mav;
    }

    @RequestMapping(value="/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {

        model.addAttribute("error", "true");
        return "/user/login";

    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {

        return "/user/login";

    }
}
