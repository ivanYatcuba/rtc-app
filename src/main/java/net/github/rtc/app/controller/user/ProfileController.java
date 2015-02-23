package net.github.rtc.app.controller.user;

import net.github.rtc.app.controller.common.MenuItem;
import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.model.entity.user.*;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.service.security.AuthorizedUserProvider;
import net.github.rtc.app.utils.propertyeditors.CustomTypeEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user/profile")
public class ProfileController implements MenuItem {

    private static final String USER_ROOT = "portal/user";
    private static final String EXPERT_ROOT = "portal/expert";
    private static final String USER = "user";
    private static final String REDIRECT = "redirect:/";
    private static final String VALIDATION_RULES = "validationRules";
    @Autowired
    private UserService userService;
    @Autowired
    private ValidationContext validationContext;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView user() {
        final ModelAndView mav = new ModelAndView(getRoot() + "/profile/profile");
        final User user = AuthorizedUserProvider.getAuthorizedUser();
        mav.addObject(USER, user);
        return mav;
    }

    /**
     * Process the request to get edit user form
     *
     * @return modelAndView(user/layout)
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit() {
        final User user = AuthorizedUserProvider.getAuthorizedUser();
        final ModelAndView mav = new ModelAndView(getRoot() + "/profile/editProfile");
        mav.getModelMap().addAttribute(USER, user);
        mav.addObject(VALIDATION_RULES, validationContext.get(User.class));
        return mav;
    }

    /**
     * Process the request to post entered user in the form
     *
     * @param user course object
     * @return if all is OK the redirect to view user details
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(
      @ModelAttribute(USER) final User user) {
        user.setAuthorities(Arrays.asList(userService.getRoleByType(RoleType.ROLE_USER)));
        userService.update(user);
        final Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        authenticationManager.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(request);
        return new ModelAndView(REDIRECT + "user/profile");
    }

    private String getRoot() {
        if (AuthorizedUserProvider.getAuthorizedUser().hasRole(RoleType.ROLE_EXPERT.name())) {
            return EXPERT_ROOT;
        }
        return USER_ROOT;
    }

    /**
     * Binding user conditions for entry into the form conclusions
     *
     * @param binder
     */
    @InitBinder(USER)
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Collection.class, new CustomTypeEditor());
    }

    @ModelAttribute("currentUserName")
    public String getCurrentUserName() {
        return AuthorizedUserProvider.getAuthorizedUserName();
    }

    @ModelAttribute("order")
    public UserCourseOrder getObject() {
        return new UserCourseOrder();
    }

    @ModelAttribute("english")
    public EnglishLevel[] getEnglish() {
        return EnglishLevel.values();
    }

    @Override
    public String getMenuItem() {
        return "profile";
    }
}
