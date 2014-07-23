package net.github.rtc.app.controller.user;

import net.github.rtc.app.model.*;
import net.github.rtc.app.service.CoursesService;
import net.github.rtc.app.service.UserCourseOrderService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.service.UserServiceLogin;
import net.github.rtc.app.utils.propertyeditors.CustomTagsEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

    @Autowired
    UserService userService;
    @Autowired
    UserServiceLogin userServiceLogin;
    @Autowired
    private CoursesService coursesService;
    @Autowired
    private UserCourseOrderService userCourseOrderService;
    @Autowired
    ValidationContext validationContext;

    private static final String ROOT = "portal/user";
    private static final String ROOT_MODEL = "user";


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView user() {
        ModelAndView mav = new ModelAndView(ROOT + "/page/userdataview");
        User user = userServiceLogin.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        mav.addObject("user", user);
        return mav;
    }

    /**
     * Process the request to get edit user form
     *
     * @return modelAndView(user/layout)
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit() {
        User user = userServiceLogin.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView mav = new ModelAndView(ROOT + "/page/edituser");
        mav.getModelMap().addAttribute("user", user);
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
    public ModelAndView update(@ModelAttribute(ROOT_MODEL) User user,
                               BindingResult bindingResult,
                               SessionStatus session) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/" + ROOT + "/edit/");
        }
        userService.update(user);
        session.setComplete();
        return new ModelAndView("redirect:/" + ROOT + "/view");
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute(ROOT_MODEL) User user,
                             BindingResult bindingResult,
                             SessionStatus session) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/" + ROOT + "/register/");
        }

        user.setAuthorities(getUserRole());
        userService.create(user);
        session.setComplete();
        return new ModelAndView("redirect:/login/");
    }

    @RequestMapping(value = "/userCourses", method = RequestMethod.GET)
    public ModelAndView userCourses() {
        User user = userServiceLogin.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        UserCourseOrder currentUserCourseOrder = userCourseOrderService.getUserOrderByUserCode(user.getCode());
        if (currentUserCourseOrder == null) {
            ModelAndView mav = new ModelAndView(ROOT + "/page/usercours");

            SearchFilter searchFilter = new SearchFilter();
            searchFilter.setStatus("PUBLISHED");
            CourseDto courseDto = coursesService.findByFilter(searchFilter);
            Collections.sort((List<Course>)courseDto.getCourses(), new Comparator<Course>() {
                public int compare(Course course1, Course course2) {
                    if (course1.getStartDate() == null || course2.getStartDate() == null)
                        return 0;
                    return course1.getStartDate().compareTo(course2.getStartDate());
                }
            });

            mav.addObject("user", user);
            mav.addObject("courses", courseDto.getCourses());
            return mav;
        } else {
            ModelAndView mav = new ModelAndView(ROOT + "/page/courseorder");
            Course orderedCourse = coursesService.findByCode(currentUserCourseOrder.getCourseCode());
            mav.addObject("user", user);
            mav.addObject("orderStatus", currentUserCourseOrder.getStatus());
            mav.addObject("courseName", orderedCourse.getName());
            mav.addObject("courseDescription", orderedCourse.getDescription());
            return mav;
        }
    }


    @RequestMapping(value = "/sendOrder", method = RequestMethod.POST)
    public ModelAndView sendCourseOrder(@RequestBody String orderData) {
        ModelAndView mav = new ModelAndView("redirect:/user/userCourses");
        //I'm sorry for this...
        Map<String, String> orderParamsMap = getUserCourseOrderParams(orderData);
        User user = userServiceLogin.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        UserCourseOrder userCourseOrder = buildUserCourseOrder(orderParamsMap, user.getCode());
        userCourseOrderService.insert(userCourseOrder);
        return mav;
    }


    private UserCourseOrder buildUserCourseOrder(Map<String, String> orderParamsMap, String userCode) {
        UserCourseOrder userCourseOrder = new UserCourseOrder();
        userCourseOrder.setUserCode(userCode);
        userCourseOrder.setCourseCode(orderParamsMap.get("selectedCode"));

        if (orderParamsMap.get("userCourses").equals("Developer")) {
            userCourseOrder.setPosition(TraineePosition.DEVELOPER);
        } else if (orderParamsMap.get("userCourses").equals("Tester")) {
            userCourseOrder.setPosition(TraineePosition.TESTER);
        } else if (orderParamsMap.get("userCourses").equals("Business Analyst ")) {
            userCourseOrder.setPosition(TraineePosition.BUSINESS_ANALIST);
        }
        userCourseOrder.setReason(orderParamsMap.get("userTextArea"));
        userCourseOrder.setStatus(UserRequestStatus.PENDING);
        userCourseOrder.setRequestDate(new Date());
        return userCourseOrder;
    }

    private Map<String, String> getUserCourseOrderParams(String orderData) {
        Map<String, String> orderParamsMap = new HashMap<String, String>();
        String[] orderParams = orderData.split("&");
        for (String param : orderParams) {
            String[] value = param.split("=");
            orderParamsMap.put(value[0], value[1]);
        }
        return orderParamsMap;
    }

    @ModelAttribute("searchFilter")
    public SearchFilter getFilter() {
        return new SearchFilter();
    }


    /**
     * Binding user conditions for entry into the form conclusions
     *
     * @param binder
     */
    @InitBinder(ROOT_MODEL)
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Collection.class, new CustomTagsEditor());
    }

    /**
     * Prepare user as model attribute
     *
     * @return user object
     */
    @ModelAttribute(value = ROOT_MODEL)
    public User getCommandObject() {
        return new User();
    }



    private List<Role> getUserRole(){
        List<Role> roles = new ArrayList<Role>();
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roles.add(userRole);
        return roles;
    }
}