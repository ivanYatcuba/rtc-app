package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import net.github.rtc.app.utils.datatable.search.UserSearchFilter;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Lapshin Ugene
 */
@Controller("adminNavigationController")
@RequestMapping("admin/user")
public class UserController {

    private static final int USERS_PER_PAGE = 5;
    private static final String ROOT = "portal/admin";
    private static final String STRING_USER = "user";
    private static final String STRING_USERS = "users";
    private static final String PATH_PAGE_VIEW_ALL_USERS = "/page/viewAllusers";
    private static final String PATH_PAGE_USER_PAGE = "/page/userPagea";
    private static final String REDIRECT_USER_PAGE = "redirect:/admin/user/userPage/";
    private static final String STRING_VALIDATION_RULES = "validationRules";
    private static final String REDIRECT_VIEW_ALL = "redirect:/admin/user/viewAll";
    private static final String STRING_USER_FILTER = "userFilter";
    private static final String STRING_AUTHORITIES = "authorities";

    @Autowired
    private ValidationContext validationContext;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/viewAll", method = RequestMethod.GET)
    public ModelAndView index() {
        final UserSearchFilter filter = getFilterUser();
        filter.setPage(1);
        return switchPage(filter);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public ModelAndView filter(@ModelAttribute(STRING_USER_FILTER) final
                               UserSearchFilter userFilter) {
                return switchPage(userFilter);
    }

    @RequestMapping(value = "/viewAll", method = RequestMethod.POST)
    public @ResponseBody ModelAndView switchPage(@ModelAttribute(STRING_USER_FILTER) final UserSearchFilter userFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + PATH_PAGE_VIEW_ALL_USERS);
        final SearchResults<User> results = userService.search(userFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_USERS, results.getResults());
        mav.addObject(STRING_AUTHORITIES, getAuthorities());
        mav.addObject(STRING_USER_FILTER, userFilter);
        return mav; //maybe switch url??
    }

    @RequestMapping(value = "userPage/editPage/{code}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable final String code) {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/editPages");
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(User.class));
        final User us = userService.findByCode(code);
        mav.addObject(STRING_USER, us);
        return mav;
    }

    @RequestMapping(value = "/userPage/{code}", method = RequestMethod.GET)
    public ModelAndView userPage(@PathVariable final String code) {
        final ModelAndView mav = new ModelAndView(ROOT + PATH_PAGE_USER_PAGE);
        mav.addObject(STRING_USER, userService.findByCode(code));
        return mav;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String setStatusForRemoval(@RequestParam final String userCode) {
        userService.markUserForRemoval(userCode);
        return REDIRECT_VIEW_ALL;
    }

    @RequestMapping(value = "/restore/{userCode}", method = RequestMethod.GET)
    public String setStatusActive(@PathVariable final String userCode) {
        userService.activateUser(userCode);
        return REDIRECT_VIEW_ALL;
    }

    @RequestMapping(value = "/expertUsers", method = RequestMethod.POST)
    public
    @ResponseBody
    List<String> getExpertUsers() {
        final List<String> results = new ArrayList<>();
        final List<User> users = userService.getUserByRole(RoleType.ROLE_EXPERT);
        for (final User user : users) {
            results.add(user.shortString());
        }
        return results;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public ModelAndView createUser() {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/createuser");
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(User.class));
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute(STRING_USER) @Valid final User user, final SessionStatus session, @RequestParam final RoleType selectedRole) {
        user.setAuthorities(Arrays.asList(userService.getRoleByType(selectedRole)));
        userService.create(user);
        session.setComplete();
        return REDIRECT_USER_PAGE + user.getCode();
    }

    @RequestMapping(value = "/update/{code}", method = RequestMethod.POST)
    public String update(@PathVariable final String code, @ModelAttribute(STRING_USER) @Valid final User user, final SessionStatus session,
                         @RequestParam final RoleType selectedRole) {
        user.setAuthorities(Arrays.asList(userService.getRoleByType(selectedRole)));
        user.setCode(code);
        user.setId(userService.findByCode(user.getCode()).getId());
        userService.update(user);
        session.setComplete();
        return REDIRECT_USER_PAGE + user.getCode();
    }


    @InitBinder(STRING_USER)
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
       // binder.registerCustomEditor(List.class, "tags", new CustomTagsEditor());
       // binder.registerCustomEditor(List.class, STRING_TYPES, new CustomStringEditor());
    }

    @InitBinder(STRING_USER_FILTER)
    public void initFilterBinder(final WebDataBinder binder) {
        initBinder(binder);
    }

    @ModelAttribute(value = STRING_USER)
    public User getCommandObject() {
        return new User();
    }

    @ModelAttribute(STRING_USER_FILTER)
    public UserSearchFilter getFilterUser() {
        return new UserSearchFilter();
    }

    @ModelAttribute(STRING_AUTHORITIES)
    public Collection<String> getAuthorities() {
        return RoleType.findAll();
    }

    @ModelAttribute("roles")
    public List<RoleType> getCategories() {
        final List<RoleType> roles = new ArrayList<>();
        roles.add(RoleType.ROLE_USER);
        roles.add(RoleType.ROLE_ADMIN);
        roles.add(RoleType.ROLE_EXPERT);
        return roles;
    }

    @ModelAttribute("english")
    public Collection<String> getEnglish() {
        final Collection<String> s = new ArrayList<String>();
        s.add("Basic");
        s.add("Intermidiate");
        s.add("Advanced");
        return s;
    }

    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
