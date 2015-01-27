package net.github.rtc.app.controller.admin;

import net.github.rtc.app.controller.common.ResourceNotFoundException;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserStatus;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.datatable.search.UserSearchFilter;
import net.github.rtc.app.utils.files.upload.FileUpload;
import net.github.rtc.app.utils.propertyeditors.CustomRoleEditor;
import net.github.rtc.app.utils.propertyeditors.CustomTypeEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

/**
 * @author Lapshin Ugene
 */
@Controller("adminNavigationController")
@RequestMapping("admin/user")
public class UserController implements MenuItem {

    private static final String STRING_USER = "user";
    private static final String REDIRECT_USER_PAGE = "redirect:/admin/user/view/";
    private static final String STRING_VALIDATION_RULES = "validationRules";
    private static final String REDIRECT_ADMIN_SEARCH = "redirect:/admin/search";
    private static final String STRING_USER_FILTER = "userFilter";
    private static final String STRING_AUTHORITIES = "authorities";
    private static final String STRING_STATUSES = "statuses";
    private static final String PROGRAMMING_LANGUAGES = "programmingLanguages";
    private static final String ROOT = "portal/admin";
    private static final String UPDATE_VIEW = "/user/userUpdate";
    private static final String CREATE_VIEW = "/user/userCreate";
    private static final String DETAILS_VIEW = "/user/userDetails";
    @Autowired
    private ValidationContext validationContext;
    @Autowired
    private UserService userService;
    @Autowired
    private FileUpload upload;
    @Value("${img.save.folder}")
    private String imgFold;

    @RequestMapping(value = "/edit/{code}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable final String code) {
        final ModelAndView mav = new ModelAndView(ROOT + UPDATE_VIEW);
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(User.class));
        final User us = userService.findByCode(code);
        mav.addObject(STRING_USER, us);
        return mav;
    }

    @RequestMapping(value = "/view/{code}", method = RequestMethod.GET)
    public ModelAndView userPage(@PathVariable final String code) {
        final ModelAndView mav = new ModelAndView(ROOT + DETAILS_VIEW);
        final User user = userService.findByCode(code);

        if (userService.isNotFound(user)) {
            throw new ResourceNotFoundException();
        }

        mav.addObject(STRING_USER, user);
        return mav;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String setStatusForRemoval(@RequestParam final String userCode) throws Exception {
        userService.markUserForRemoval(userCode);
        return REDIRECT_ADMIN_SEARCH;
    }

    @RequestMapping(value = "/restore/{userCode}", method = RequestMethod.GET)
    public String setStatusActiveAndRestore(@PathVariable final String userCode) {
        userService.restoreUser(userCode);
        userService.inactivateUser(userCode);
        return REDIRECT_ADMIN_SEARCH;
    }

    @RequestMapping(value = "/inactivate/{userCode}", method = RequestMethod.GET)
    public String setStatusInactive(@PathVariable final String userCode) {
        userService.inactivateUser(userCode);
        return REDIRECT_ADMIN_SEARCH;
    }

    @RequestMapping(value = "/activate/{userCode}", method = RequestMethod.GET)
    public String setStatusActive(@PathVariable final String userCode) {
        userService.activateUser(userCode);
        return REDIRECT_ADMIN_SEARCH;
    }

    @RequestMapping(value = "/getExperts", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, String> getExpertUsers() {
        final Map<String, String> results = new HashMap<>();
        final List<User> admins = userService.getUserByRole(RoleType.ROLE_EXPERT);
        for (final User admin : admins) {
            results.put(admin.shortString(), admin.getCode());
        }
        return results;
    }

    @RequestMapping(value = "/getAdmins", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, String> getAdminMapDataId() {
        final Map<String, String> results = new HashMap<>();
        final List<User> admins = userService.getUserByRole(RoleType.ROLE_ADMIN);
        for (final User admin : admins) {
            results.put(admin.shortString(), admin.getCode());
        }
        return results;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public ModelAndView createUser() {
        final ModelAndView mav = new ModelAndView(ROOT + CREATE_VIEW);
        mav.addObject(STRING_VALIDATION_RULES, validationContext.get(User.class));
        return mav;
    }

    @RequestMapping(value = "/save", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String save(
      @ModelAttribute(STRING_USER) final User user,
      @RequestParam(value = "uploadPhoto", required = false) MultipartFile img,
      @RequestParam(required = false) final boolean ifActive) {
        user.setStatus(ifActive ? UserStatus.ACTIVE : UserStatus.INACTIVE);
        userService.create(user, img);
        return REDIRECT_USER_PAGE + user.getCode();
    }

    @RequestMapping(value = "/update/{code}", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String update(
      @ModelAttribute(STRING_USER) @Valid final User user,
      @RequestParam(value = "uploadPhoto", required = false) MultipartFile img,
      @RequestParam(required = false) final boolean ifActive) {
        if (!img.isEmpty()) {
            user.setPhoto(upload.saveImage(user.getCode(), img));
        }
        if (user.getStatus() != UserStatus.ACTIVE) {
            user.setStatus(ifActive ? UserStatus.ACTIVE : UserStatus.INACTIVE);
        }
        userService.update(user);
        return REDIRECT_USER_PAGE + user.getCode();
    }

    @InitBinder
    public void initFilterBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(List.class, PROGRAMMING_LANGUAGES, new CustomTypeEditor());
        binder.registerCustomEditor(List.class, STRING_AUTHORITIES, new CustomRoleEditor(userService));
    }

    @ModelAttribute(value = STRING_USER)
    public User getCommandObject() {
        return new User();
    }

    @ModelAttribute(STRING_USER_FILTER)
    public UserSearchFilter getFilterUser() {
        return new UserSearchFilter();
    }

    @ModelAttribute(STRING_STATUSES)
    public Collection<String> getStatuses() {
        return UserStatus.findAll();
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
        s.add("Intermediate");
        s.add("Advanced");
        return s;
    }

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        return userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @ModelAttribute("currentUserName")
    public String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public String getMenuItem() {
        return STRING_USER;
    }
}
