package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.propertyeditors.CustomStringEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    private static Logger log = LoggerFactory.getLogger(
      UserController.class.getName());

    private static final String ROOT = "portal/admin";
    private static final String STRING_USER = "user";
    private static final String STRING_USERS = "users";
    private static final int USERS_PER_PAGE = 10;
    private static final String PATH_PAGE_VIEW_ALL_USERS = "/page/viewAllusers";
    private static final String STRING_PAGES = "pages";
    private static final String STRING_NUMBER_OF_PAGE = "numberOfPage";
    private static final String PATH_PAGE_USER_PAGE = "/page/userPagea";
    private static final String STRING_VALIDATION_RULES = "validationRules";
    private static final String REDIRECT_VIEW_ALL
      = "redirect:/admin/user/viewAll";

    @Autowired
    private ValidationContext validationContext;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/viewAll", method = RequestMethod.GET)
    public ModelAndView viewAll() {
        final ModelAndView mav = new ModelAndView(
          ROOT + PATH_PAGE_VIEW_ALL_USERS);
        final Collection<User> listUser = userService.findAll();
        final ArrayList<User> newListUser = new ArrayList<User>();
        for (final User user : listUser) {
            newListUser.add(user);
        }
        final int numberOfPage = 1;
        int lastUser = USERS_PER_PAGE * numberOfPage;
        final int firstUser = lastUser - USERS_PER_PAGE;
        if (lastUser > newListUser.size()) {
            lastUser = newListUser.size();
        }
        final int numbOfPages = (int) Math.ceil(
          listUser.size() / (double) USERS_PER_PAGE);
        mav.addObject(STRING_USERS, newListUser.subList(firstUser, lastUser));
        mav.addObject(STRING_PAGES, numbOfPages);
        mav.addObject(STRING_NUMBER_OF_PAGE, numberOfPage);
        return mav;
    }

    @RequestMapping(value = "/viewAll/{numberOfPage}",
      method = RequestMethod.GET)
    public ModelAndView viewAll(@PathVariable final int numberOfPage) {
        final ModelAndView mav = new ModelAndView(
          ROOT + PATH_PAGE_VIEW_ALL_USERS);
        final Collection<User> listUser = userService.findAll();
        final ArrayList<User> newListUser = new ArrayList<>();
        for (final User user : listUser) {
            newListUser.add(user);
        }
        int lastUser = USERS_PER_PAGE * numberOfPage;
        final int firstUser = lastUser - USERS_PER_PAGE;
        if (lastUser > newListUser.size()) {
            lastUser = newListUser.size();
        }
        final int numbOfPages = (int) Math.ceil(
          listUser.size() / (double) USERS_PER_PAGE);
        mav.addObject(STRING_USERS, newListUser.subList(firstUser, lastUser));
        mav.addObject(STRING_PAGES, numbOfPages);
        mav.addObject(STRING_NUMBER_OF_PAGE, numberOfPage);
        return mav;
    }

    @RequestMapping(value = "/view/{code}", method = RequestMethod.GET)
    public ModelAndView user(@PathVariable final Integer code) {
        final ModelAndView mav = new ModelAndView(ROOT + PATH_PAGE_USER_PAGE);
        return mav;
    }

    @RequestMapping(value = "userPage/editPage/{code}",
      method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable final String code) {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/editPages");
        mav.addObject(STRING_VALIDATION_RULES,
          validationContext.get(User.class));
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

    @RequestMapping(value = "/changeUserStatus", method = RequestMethod.POST)
    public String delete(@RequestParam final String userCode) {
        log.info("Getting code: " + userCode);
        //1. Do not forget to remove it
        //2. Use logs
        //System.out.print("1111111111111");
        userService.markUserForRemoval(userCode);
        return REDIRECT_VIEW_ALL;
    }

    @RequestMapping(value = "/expertUsers", method = RequestMethod.POST)
    public
    @ResponseBody
    List<String> getExpertUsers() {
        final List<String> results = new ArrayList<>();
        final List<User> users = userService.getUserByRole(
          RoleType.ROLE_EXPERT);
        for (final User user : users) {
            results.add(user.shortString());
        }
        return results;
    }


    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public ModelAndView createUser() {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/createuser");
        mav.addObject(STRING_VALIDATION_RULES,
          validationContext.get(User.class));
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
      @ModelAttribute(STRING_USER) @Valid final User user,
      final SessionStatus session,
      @RequestParam final RoleType selectedRole) {
        user.setAuthorities(
          Arrays.asList(userService.getRoleByType(selectedRole)));
        user.setRegisterDate(new Date());
        userService.create(user);
        session.setComplete();
        return REDIRECT_VIEW_ALL;
    }

    @RequestMapping(value = "/update/{code}", method = RequestMethod.POST)
    public String update(
      @PathVariable final String code,
      @ModelAttribute(STRING_USER) @Valid
      final User user,
      final BindingResult bindingResult,
      final SessionStatus session,
      @RequestParam final RoleType selectedRole) {
        user.setAuthorities(
          Arrays.asList(userService.getRoleByType(selectedRole)));
        user.setCode(code);
        user.setId(userService.findByCode(user.getCode()).getId());
        userService.update(user);
        session.setComplete();
        return "redirect:/admin/user/userPage/" + user.getCode();
    }

    @ModelAttribute(value = STRING_USER)
    public User getCommandObject() {
        return new User();
    }

    @InitBinder(STRING_USER)
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class,
          new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Collection.class, new CustomStringEditor());
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
