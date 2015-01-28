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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Vladislav Pikus
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private ModelAndView buildLoginMav(final ModelMap model) {
        final ModelAndView mav
                = new ModelAndView("welcome/welcomeLayout", model);
        mav.addObject("content", "../portal/user/login");
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(final ModelMap model) throws Exception {
        return buildLoginMav(model);
    }

    @RequestMapping(value = "/j_spring_security_check{other}", method = RequestMethod.GET)
    public void securityError(final ModelMap model, @PathVariable("other") String other) {
        throw new ResourceNotFoundException();
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public ModelAndView loginError(final ModelMap model) {

        if (!ifAnonymous()) {
            throw new ResourceNotFoundException();
        }
        final ModelAndView mav = buildLoginMav(model);
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
