package net.github.rtc.app.controller.common;

import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.service.UserServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import net.github.rtc.app.model.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
/**
 * @author Vladislav Pikus
 */
@Controller
public class LoginController {

    @Autowired
    private UserServiceLogin userServiceLogin;

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

    @RequestMapping(value="/mailExist/", method = RequestMethod.POST)
    public @ResponseBody boolean mailExist(@RequestParam String email, @RequestParam String currentEmail) {
        if(email.equals(currentEmail)){
            return true;
        }
        return userServiceLogin.loadUserByUsername(email) == null;

    }

@RequestMapping(value = "/login_attempt", method = RequestMethod.GET)
    public String loginAttempt() {
    User user = userServiceLogin.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    if (user.hasRole(RoleType.ROLE_ADMIN.name())) {
         return "redirect:/admin";
        } else {
         return "redirect:/user/view/";
        }
    }
    
    
}
