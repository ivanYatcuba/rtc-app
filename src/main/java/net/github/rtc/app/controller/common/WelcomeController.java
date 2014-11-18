package net.github.rtc.app.controller.common;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ivan on 28.03.14.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    /**
     * Request to login page
     *
     * @return ModelAndView("welcome/welcomeLayout")
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() throws Exception {
        final ModelAndView mav = new ModelAndView("welcome/welcomeLayout");
        mav.addObject("content", "../portal/user/login");
        return mav;
    }

    @ModelAttribute("currentUserName")
    public String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
