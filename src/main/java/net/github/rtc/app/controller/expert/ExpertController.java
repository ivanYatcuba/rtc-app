package net.github.rtc.app.controller.expert;

import net.github.rtc.app.model.user.Request;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.model.user.UserRequestStatus;
import net.github.rtc.app.service.CourseService;
import net.github.rtc.app.service.UserCourseOrderService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.service.UserServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller("expertController")
@RequestMapping("/expert")
public class ExpertController {

    private static final String ROOT = "portal/expert";
    private static final String STRING_USER = "user";
    private static final String REDIRECT_REQUESTS = "redirect:/expert/requests";

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserCourseOrderService userCourseOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceLogin userServiceLogin;

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public ModelAndView expertCourses() {
        final ModelAndView mav = new ModelAndView(ROOT + "/page/Coursesexpert");
        final List<UserCourseOrder> orderList
          = userCourseOrderService.getOrderByStatus(UserRequestStatus.PENDING);
        final List<Request> requestsList = new ArrayList<>();
        if (!orderList.isEmpty()) {
            for (final UserCourseOrder order : orderList) {
                try {
                    final Request request = new Request((int) order.getId(),
                      userService.findByCode(order.getUserCode()).getName(),
                      order.getReason(),
                      courseService.findByCode(order.getCourseCode()).getName(),
                      order.getPosition().toString());
                    requestsList.add(request);
                } catch (final Throwable t) {
                    System.out.print("error");
                }
            }
        }
        final User user = userServiceLogin.loadUserByUsername(
          SecurityContextHolder.getContext().getAuthentication().getName());
        mav.addObject(STRING_USER, user);
        mav.addObject("requests", requestsList);
        return mav;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView userCourses() {
        final ModelAndView mav = new ModelAndView(
          ROOT + "/page/expertAllcourse");
        mav.addObject("courses", courseService.findAll());
        return mav;
    }

    @RequestMapping(value = "/accept/{orderId}", method = RequestMethod.GET)
    public String acceptRequest(@PathVariable final Integer orderId) {
        changeOrderStatus(UserRequestStatus.ACCEPTED, orderId);
        return REDIRECT_REQUESTS;
    }

    @RequestMapping(value = "/decline/{orderId}", method = RequestMethod.GET)
    public String declineRequest(@PathVariable final Integer orderId) {
        changeOrderStatus(UserRequestStatus.REJECTED, orderId);
        return REDIRECT_REQUESTS;
    }

    private void changeOrderStatus(
      final UserRequestStatus userRequestStatus, final Integer orderId) {
        final UserCourseOrder order = userCourseOrderService.getUserOrder(
          orderId.longValue());
        order.setResponseDate(new Date());
        order.setStatus(userRequestStatus);
        userCourseOrderService.update(order);
    }

    @ModelAttribute(value = STRING_USER)
    public User getCommandObject() {
        return new User();
    }

    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
