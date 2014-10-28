package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.upload.FileUpload;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import net.github.rtc.app.utils.datatable.search.UserSearchFilter;
import net.github.rtc.app.utils.propertyeditors.CustomRoleEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
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
    private static final String PATH_PAGE_USER_TABLE = "/user/content/userTable";
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
    @Autowired
    private FileUpload upload;

    @Value("${img.save.folder}")
    private String imgFold;

    private String photo;

    @RequestMapping(value = "/viewAll", method = RequestMethod.GET)
    public ModelAndView index() {
        final UserSearchFilter filter = getFilterUser();
        filter.setPage(1);
        final ModelAndView mav = new ModelAndView(ROOT + PATH_PAGE_VIEW_ALL_USERS);
        final SearchResults<User> results = userService.search(filter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_USERS, results.getResults());
        mav.addObject(STRING_AUTHORITIES, getAuthorities());
        mav.addObject(STRING_USER_FILTER, filter);
        return mav;
    }

    @RequestMapping(value = "/viewAll", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView switchPage(@Validated @ModelAttribute(STRING_USER_FILTER) final UserSearchFilter userFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + PATH_PAGE_USER_TABLE);
        final SearchResults<User> results = userService.search(userFilter);
        mav.addAllObjects(results.getPageModel());
        mav.addObject(STRING_USERS, results.getResults());
        mav.addObject(STRING_AUTHORITIES, getAuthorities());
        mav.addObject(STRING_USER_FILTER, userFilter);
        return mav;
    }

    @RequestMapping(value = "userPage/editPage/{code}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable final String code) {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/editPages");
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(User.class));
        final User us = userService.findByCode(code);
        mav.addObject(STRING_USER, us);
        photo = userService.findByCode(us.getCode()).getPhoto();
        // mav.addObject("path", imgfold);
        return mav;
    }

    @RequestMapping(value = "/userPage/{code}", method = RequestMethod.GET)
    public ModelAndView userPage(@PathVariable final String code) {
        final ModelAndView mav = new ModelAndView(ROOT + PATH_PAGE_USER_PAGE);
        mav.addObject(STRING_USER, userService.findByCode(code));
        return mav;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String setStatusForRemoval(@RequestParam final String userCode) throws Exception {
        userService.markUserForRemoval(userCode);
        return REDIRECT_VIEW_ALL;
    }

    @RequestMapping(value = "/restore/{userCode}", method = RequestMethod.GET)
    public String setStatusActiveAndRestore(@PathVariable final String userCode) {
        userService.restoreUser(userCode);
        userService.inactivateUser(userCode);
        return REDIRECT_VIEW_ALL;
    }

    @RequestMapping(value = "/inactivate/{userCode}", method = RequestMethod.GET)
    public String setStatusInactive(@PathVariable final String userCode) {
        userService.inactivateUser(userCode);
        return REDIRECT_VIEW_ALL;
    }

    @RequestMapping(value = "/activate/{userCode}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/getAdmins", method = RequestMethod.POST)
    public
    @ResponseBody
    List<String> getAllAdmins() {
        final List<String> results = new ArrayList<>();
        final List<User> admins = userService.getUserByRole(RoleType.ROLE_ADMIN);
        for (final User admin : admins) {
            results.add(admin.shortString());
        }
        return results;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public ModelAndView createUser() {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/createuser");
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(User.class));
        return mav;
    }

    @RequestMapping(value = "/save", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String save(@ModelAttribute(STRING_USER) @Valid final User user, final SessionStatus session, @RequestParam final RoleType selectedRole,
                       @RequestParam(value = "uploadPhoto", required = false) MultipartFile img) {
        user.setAuthorities(Arrays.asList(userService.getRoleByType(selectedRole)));
        userService.create(user);
        if (!img.isEmpty()) {
            user.setPhoto(upload.saveImage(user.getCode(), img));
        }
        userService.update(user);
        session.setComplete();
        return REDIRECT_USER_PAGE + user.getCode();
    }

    @RequestMapping(value = "/update/{code}", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String update(@PathVariable final String code, @ModelAttribute(STRING_USER) @Valid final User user, final SessionStatus session,
                         @RequestParam final RoleType selectedRole, @RequestParam(value = "uploadPhoto", required = false) MultipartFile img) {
        user.setAuthorities(Arrays.asList(userService.getRoleByType(selectedRole)));
        user.setCode(code);
        user.setId(userService.findByCode(user.getCode()).getId());
        if (!img.isEmpty()) {
            user.setPhoto(upload.saveImage(user.getCode(), img));
        } else {
            user.setPhoto(photo);
        }
        userService.update(user);
        session.setComplete();
        return REDIRECT_USER_PAGE + user.getCode();
    }

    @InitBinder
    public void initFilterBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(List.class, STRING_AUTHORITIES, new CustomRoleEditor());
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
