package net.github.rtc.app.controller.user;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.user.*;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.service.UserCourseOrderService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.service.UserServiceLogin;
import net.github.rtc.app.utils.datatable.CourseSearchFilter;
import net.github.rtc.app.utils.propertyeditors.CustomStringEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication
  .UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    private static final String ROOT = "portal/user";
    private static final String STRING_USER = "user";
    private static final String STRING_REDIRECT = "redirect:/";
    private static final int START_SEARCH = 3;
    private static final String STRING_COURSE = "course";
    private static final String STRING_USER_COURSES = "userCourses";

    @Autowired
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceLogin userServiceLogin;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserCourseOrderService userCourseOrderService;
    @Autowired
    private ValidationContext validationContext;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView user() {
        final ModelAndView mav = new ModelAndView(ROOT
          + "/page/userdataview");
        final User user = userServiceLogin.loadUserByUsername(
          SecurityContextHolder.getContext().getAuthentication().getName());
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
        final User user = userServiceLogin.loadUserByUsername(
          SecurityContextHolder.getContext().getAuthentication().getName());
        final ModelAndView mav = new ModelAndView(ROOT
          + "/page/edituser");
        mav.getModelMap().addAttribute(STRING_USER, user);
        mav.addObject("validationRules", validationContext.get(User.class));
        return mav;
    }

    /**
     * Process the request to post entered user in the form
     *
     * @param user          course object
     * @param bindingResult binding user result
     * @param session       current session
     * @return if all is OK the redirect to view user details
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(
      @ModelAttribute(STRING_USER) final User user,
      final BindingResult bindingResult,
      final SessionStatus session) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(STRING_REDIRECT
              + ROOT
              + "/edit/");
        }
        user.setAuthorities(
          Arrays.asList(userService.getRoleByType(RoleType.ROLE_USER)));
        user.setId(userService.findByCode(user.getCode()).getId());
        userService.update(user);
        session.setComplete();
        final Authentication request = new UsernamePasswordAuthenticationToken(
          user.getUsername(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(request);
        return new ModelAndView(STRING_REDIRECT + "user/view");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(
      @ModelAttribute(STRING_USER) final User user,
      final BindingResult bindingResult,
      final SessionStatus session) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(STRING_REDIRECT + ROOT
              + "/register/");
        }
        user.setAuthorities(
          Arrays.asList(userService.getRoleByType(RoleType.ROLE_USER)));
        user.setRegisterDate(new Date());
        userService.create(user);
        session.setComplete();
        return new ModelAndView("redirect:/login/");
    }

    @RequestMapping(value = "/userCourses", method = RequestMethod.GET)
    public ModelAndView userCourses() throws Exception {
        final User user = userServiceLogin.loadUserByUsername(
          SecurityContextHolder.getContext().getAuthentication().getName());
        final UserCourseOrder currentUserCourseOrder
          = userCourseOrderService.getUserOrderByUserCode(user.getCode());
        if (currentUserCourseOrder
          == null) {
            final ModelAndView mav = new ModelAndView(ROOT
              + "/page/usercours");

            final CourseSearchFilter courseSearchFilter
              = new CourseSearchFilter();
            courseSearchFilter.setStatus(CourseStatus.PUBLISHED);
            mav.addObject(STRING_USER, user);
            mav.addObject("courses",
              courseService.search(courseSearchFilter.getCriteria(), 1,
                      START_SEARCH).getResults());
            return mav;
        } else {
            final ModelAndView mav = new ModelAndView(ROOT
              + "/page/courseorder");
            final Course orderedCourse = courseService.findByCode(
              currentUserCourseOrder.getCourseCode());
            mav.addObject(STRING_USER, user);
            mav.addObject("orderStatus", currentUserCourseOrder.getStatus());
            mav.addObject(STRING_COURSE, orderedCourse);
            return mav;
        }
    }

    @RequestMapping(value = "/courseDetails/{courseCode}",
      method = RequestMethod.GET)
    public ModelAndView courseDetails(@PathVariable final String courseCode) {
        final ModelAndView mav = new ModelAndView(
          "portal/user/page/courseDetail");
        mav.addObject(STRING_COURSE, courseService.findByCode(courseCode));
        mav.addObject(STRING_USER, userServiceLogin.loadUserByUsername(
          SecurityContextHolder.getContext().getAuthentication().getName()));
        return mav;
    }

    @RequestMapping(value = "/sendOrder", method = RequestMethod.POST)
    public ModelAndView sendCourseOrder(@RequestBody final String orderData) {
        final ModelAndView mav = new ModelAndView("redirect:/user/userCourses");
        //I'm sorry for this...
        final Map<String, String> orderParamsMap = getUserCourseOrderParams(
          orderData);
        final User user = userServiceLogin.loadUserByUsername(
          SecurityContextHolder.getContext().getAuthentication().getName());
        final UserCourseOrder userCourseOrder = buildUserCourseOrder(
          orderParamsMap, user.getCode());
        userCourseOrderService.insert(userCourseOrder);
        return mav;
    }

    private UserCourseOrder buildUserCourseOrder(
      final Map<String, String> orderParamsMap, final String userCode) {
        final UserCourseOrder userCourseOrder = new UserCourseOrder();
        userCourseOrder.setUserCode(userCode);
        userCourseOrder.setCourseCode(orderParamsMap.get("selectedCode"));

        if (orderParamsMap.get("Developer").equals(STRING_USER_COURSES)) {
            userCourseOrder.setPosition(TraineePosition.DEVELOPER);
        } else if (orderParamsMap.get("Tester").equals(STRING_USER_COURSES)) {
            userCourseOrder.setPosition(TraineePosition.TESTER);
        } else if (orderParamsMap.get("Business Analyst ")
                .equals(STRING_USER_COURSES)) {
            userCourseOrder.setPosition(TraineePosition.BUSINESS_ANALYST);
        }
        userCourseOrder.setReason(orderParamsMap.get("userTextArea"));
        userCourseOrder.setStatus(UserRequestStatus.PENDING);
        userCourseOrder.setRequestDate(new Date());
        return userCourseOrder;
    }

    private Map<String, String> getUserCourseOrderParams(
      final String orderData) {
        final Map<String, String> orderParamsMap = new HashMap<>();
        final String[] orderParams = orderData.split("&");
        for (final String param : orderParams) {
            final String[] value = param.split("=");
            orderParamsMap.put(value[0], value[1]);
        }
        return orderParamsMap;
    }

    /**
     * Binding user conditions for entry into the form conclusions
     *
     * @param binder
     */
    @InitBinder(STRING_USER)
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class,
          new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Collection.class, new CustomStringEditor());
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

    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
