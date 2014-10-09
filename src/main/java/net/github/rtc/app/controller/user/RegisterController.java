package net.github.rtc.app.controller.user;

import net.github.rtc.app.exception.ServiceProcessingException;
import net.github.rtc.app.model.user.User;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ivan on 18.04.14.
 */
@Controller("registerController")
@RequestMapping("user/register")
public class RegisterController {

    @Autowired
    private ValidationContext validationContext;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView openRegisterPage() {
        final ModelAndView mav = new ModelAndView(
          "portal/user/page/registration");
        final User u = new User();
        mav.getModelMap().addAttribute("user", u);
        mav.addObject("validationRules", validationContext.get(User.class));
        return mav;
    }
}
