package net.github.rtc.app.controller.user;

import net.github.rtc.app.controller.common.MenuItem;
import net.github.rtc.app.model.user.EnglishLevel;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.service.user.UserService;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/user/profile")
public class ProfileController implements MenuItem {

    private static final String ROOT = "portal/user";
    private static final String STRING_USER = "user";
    private static final String STRING_REDIRECT = "redirect:/";
    private static final String STRING_VALIDATION_RULES = "validationRules";
    @Autowired
    private UserService userService;
    @Autowired
    private ValidationContext validationContext;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView user() {
        final ModelAndView mav = new ModelAndView(ROOT + "/profile/profile");
        final User user = userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        mav.addObject(STRING_USER, user);
        return mav;
    }

    /**
     * Process the request to get edit user form
     *
     * @return modelAndView(user/layout)
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit() {
        final User user = userService.loadUserByUsername(
          SecurityContextHolder.getContext().getAuthentication().getName());
        final ModelAndView mav = new ModelAndView(ROOT + "/profile/editProfile");
        mav.getModelMap().addAttribute(STRING_USER, user);
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(User.class));
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
      @ModelAttribute(STRING_USER) final User user) {
        user.setAuthorities(Arrays.asList(userService.getRoleByType(RoleType.ROLE_USER)));
        userService.update(user);
        final Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        authenticationManager.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(request);
        return new ModelAndView(STRING_REDIRECT + "user/profile");
    }

    /**
     * Binding user conditions for entry into the form conclusions
     *
     * @param binder
     */
    @InitBinder(STRING_USER)
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Collection.class, new CustomTypeEditor());
    }

    /**
     * Prepare user as model attribute
     *
     * @return user object
     */
    @ModelAttribute(value = STRING_USER)
    public User getCommandObject() {
        return new User();
    }

    @ModelAttribute("currentUserName")
    public String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
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
