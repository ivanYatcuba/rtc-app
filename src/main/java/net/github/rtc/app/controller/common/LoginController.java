package net.github.rtc.app.controller.common;

import net.github.rtc.app.model.user.RoleType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import net.github.rtc.app.model.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
/**
 * @author Vladislav Pikus
 */
@Controller
public class LoginController {

    private ModelAndView buildLoginMav(ModelMap model) {
        ModelAndView mav = new ModelAndView("welcome/welcomeLayout", model);
        mav.addObject("content","../portal/user/login");
        return mav;
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login(ModelMap model) {

        return buildLoginMav(model);
    }

    @RequestMapping(value="/loginfailed", method = RequestMethod.GET)
    public ModelAndView loginerror(ModelMap model) {
        ModelAndView mav = buildLoginMav(model);
        mav.addObject("error", "true");

        return mav;
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout(ModelMap model) {
        SecurityContextHolder.clearContext();
        return buildLoginMav(model);

    }

@RequestMapping(value = "/login_attempt", method = RequestMethod.GET)
    public String loginAttempt() {
        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser.hasRole(RoleType.ROLE_ADMIN.name())) {
         return "redirect:/admin";
        } else {
         return "redirect:/user/view/";
        }
    }
    
    
}
