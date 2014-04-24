package net.github.rtc.app.controller.user;

import net.github.rtc.app.model.*;
import net.github.rtc.app.service.CoursesService;
import net.github.rtc.app.service.UserCourseOrderService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.service.UserServiceLogin;
import net.github.rtc.app.utils.propertyeditors.CustomTagsEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

    private static final String ROOT = "user";
    private static final String ROOT_MODEL = "user" ;


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView user(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        User user = userService.findById(id);
        mav.addObject("user", user);
        mav.addObject("content", "userContent");
        return mav;
    }

    /**
     * Process the request to get edit user form
     *
     * @return modelAndView("user/layout")
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer id) {
        User u = userService.findById(id);
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.getModelMap().addAttribute("user", u);
        mav.addObject("content", "editUser");
        return mav;
    }

    /**
     * Process the request to post entered user in the form
     *
     * @param user course object
     * @param bindingResult binding user result
     * @param session current session
     * @return if all is OK the redirect to view user details
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute(ROOT_MODEL) User user,
                               BindingResult bindingResult,
                               SessionStatus session) {
        if (bindingResult.hasErrors()) {
            return modelAndViewContentBuilder("editUser");
        }
        userService.update(user);
        session.setComplete();
        return new ModelAndView("redirect:/" + ROOT + "/" +"view/"+ user.getId());
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute(ROOT_MODEL) User user,
                               BindingResult bindingResult,
                               SessionStatus session) {
        if (bindingResult.hasErrors()) {
            return modelAndViewContentBuilder("register");
        }
        userService.create(user);
        user = userServiceLogin.loadUserByUsername(user.getEmail());
        session.setComplete();
        return new ModelAndView("redirect:/" + ROOT + "/" +"view/"+ user.getId());
    }

    @RequestMapping(value = "{id}/userCourses", method = RequestMethod.GET)
    public ModelAndView userCourses(@PathVariable Integer id) {
        UserCourseOrder currentUserCourseOrder = userCourseOrderService.getUserOrderByUserId(id);
        if(currentUserCourseOrder == null){
            ModelAndView mav = new ModelAndView(ROOT + "/layout");
            Map<String, String> map = new HashMap<String, String>();
            CourseDto dto = coursesService.findByFilter(getFilter().createQuery(map).byStatus().toString());
            User currentUser = userService.findById(id);
            mav.addObject("user", currentUser);
            mav.addObject("courses", dto.getCourses());
            mav.addObject("content", "userCourses");
            return mav;
        }
        else{
            ModelAndView mav = new ModelAndView(ROOT + "/layout");
            User currentUser = userService.findById(id);
            Course orderedCourse = coursesService.findByCode(currentUserCourseOrder.getCourseCode());
            mav.addObject("user", currentUser);
            mav.addObject("orderStatus", currentUserCourseOrder.getStatus());
            mav.addObject("courseName", orderedCourse.getName());
            mav.addObject("courseDescription", orderedCourse.getDescription());
            mav.addObject("content", "userCourseOrder");
            return mav;
        }
    }


    @RequestMapping(value = "{id}/sendOrder", method = RequestMethod.POST)
    public ModelAndView sendCourseOrder(@RequestBody String orderData, @PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("redirect:/user/"+id+"/userCourses");
        //I'm sorry for this...
        Map<String, String> orderParamsMap = getUserCourseOrderParams(orderData);
        UserCourseOrder userCourseOrder = buildUserCourseOrder(orderParamsMap, id);
        userCourseOrderService.insert(userCourseOrder);
        return mav;
    }


    private UserCourseOrder buildUserCourseOrder(Map<String, String> orderParamsMap, Integer userId){
        UserCourseOrder userCourseOrder = new UserCourseOrder();
        userCourseOrder.setUserId(userId.longValue());
        userCourseOrder.setCourseCode(orderParamsMap.get("selectedCode"));

        if(orderParamsMap.get("userCourses").equals("Developer")){
            userCourseOrder.setPosition(TraineePosition.DEVELOPER);
        }
        else if(orderParamsMap.get("userCourses").equals("Tester")){
            userCourseOrder.setPosition(TraineePosition.TESTER);
        }
        else if(orderParamsMap.get("userCourses").equals("Business Analyst ")){
            userCourseOrder.setPosition(TraineePosition.BUSINESS_ANALIST);
        }
        userCourseOrder.setReason(orderParamsMap.get("userTextArea"));
        userCourseOrder.setStatus(UserRequestStatus.PENDING);
        userCourseOrder.setRequestDate(new Date());
        return userCourseOrder;
    }

    private Map<String, String> getUserCourseOrderParams(String orderData){
        Map<String, String> orderParamsMap = new HashMap<String, String>();
        String[] orderParams = orderData.split("&");
        for(String param: orderParams){
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

    /**
     * build MAV
     *
     * @param content content name
     * @return configurated mav
     */
    private ModelAndView modelAndViewContentBuilder(String content) {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.addObject("content", content);
        return mav;
    }

    @ModelAttribute("english")
    public Collection<String> getEnglish() {

        Collection<String> s = new ArrayList<String>();
        s.add("Basic");
        s.add("Intermediate");
        s.add("Advanced");
        return s;
    }
}