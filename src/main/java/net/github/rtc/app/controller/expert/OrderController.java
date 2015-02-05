package net.github.rtc.app.controller.expert;

import net.github.rtc.app.controller.common.MenuItem;
import net.github.rtc.app.model.entity.user.UserRequestStatus;
import net.github.rtc.app.service.user.UserCourseOrderService;
import net.github.rtc.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("expertController")
@RequestMapping("user/expert/order")
public class OrderController implements MenuItem {

    private static final String ROOT = "portal/expert";
    private static final String REDIRECT_REQUESTS = "redirect:/user/expert/order";
    private static final String ORDERS = "orders";
    @Autowired
    private UserCourseOrderService userCourseOrderService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView expertOrders() {
        final ModelAndView mav = new ModelAndView(ROOT + "/orders/orders");
        mav.addObject(ORDERS,
                    userCourseOrderService.getOrderByExpertCode(
                    userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).
                            getCode()));
        return mav;
    }

    @RequestMapping(value = "/accept/{orderCode}", method = RequestMethod.GET)
    public String acceptRequest(@PathVariable final String orderCode) {
        userCourseOrderService.changeOrderStatus(UserRequestStatus.ACCEPTED, orderCode);
        return REDIRECT_REQUESTS;
    }

    @RequestMapping(value = "/decline/{orderCode}", method = RequestMethod.GET)
    public String declineRequest(@PathVariable final String orderCode) {
        userCourseOrderService.changeOrderStatus(UserRequestStatus.REJECTED, orderCode);
        return REDIRECT_REQUESTS;
    }

    @Override
    public String getMenuItem() {
        return ORDERS;
    }
}
