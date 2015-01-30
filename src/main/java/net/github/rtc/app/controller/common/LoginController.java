package net.github.rtc.app.controller.common;

import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private  static final String LOGIN_LOGIN = "login/login";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView(LOGIN_LOGIN);
    }


    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public ModelAndView loginError(final ModelMap model) {

        if (!ifAnonymous()) {
            throw new ResourceNotFoundException();
        }
        final ModelAndView mav = new ModelAndView(LOGIN_LOGIN, model);
        mav.addObject("error", "true");

        return mav;
    }

    @RequestMapping(value = "/mailExist/", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean mailExist(
            @RequestParam final String email,
            @RequestParam final String currentEmail) {
        if (email.equals(currentEmail)) {
            return true;
        }
        return userService.loadUserByUsername(email)
                == null;

    }

    @RequestMapping(value = "/login_attempt", method = RequestMethod.GET)
    public String loginAttempt() {
        final User user
                = userService.loadUserByUsername(SecurityContextHolder
                .getContext().getAuthentication().getName());
        if (user.hasRole(RoleType.ROLE_ADMIN.name())) {
            return "redirect:/admin";
        } else {
            return "redirect:/user/view/";
        }
    }


    public boolean ifAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication instanceof AnonymousAuthenticationToken;
    }

}
