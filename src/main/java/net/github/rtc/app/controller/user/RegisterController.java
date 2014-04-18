package net.github.rtc.app.controller.user;

import net.github.rtc.app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ivan on 18.04.14.
 */
@Controller("registerController")
@RequestMapping("user/register")
public class RegisterController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView openRegisterPage() {
        ModelAndView mav = new ModelAndView("user/layout");
        User u = new User();
        mav.getModelMap().addAttribute("user", u);
        mav.addObject("content", "register");
        return mav;
    }

    @ModelAttribute("english")
    public Collection<String> getEnglish() {

        Collection<String> s = new ArrayList<String>();
        s.add("Basic");
        s.add("Intermidiate");
        s.add("Advanced");
        return s;
    }
}
