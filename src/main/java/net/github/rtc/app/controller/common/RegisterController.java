package net.github.rtc.app.controller.common;

import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.propertyeditors.CustomTypeEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final String USER = "user";
    private static final String REDIRECT_LOGIN = "redirect:/login/";
    private static final String VALIDATION_RULES = "validationRules";
    private static final String VIEW_NAME = "register/register";

    @Autowired
    private ValidationContext validationContext;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView openRegisterPage() {
        final ModelAndView mav = new ModelAndView(VIEW_NAME);
        mav.addObject(VALIDATION_RULES, validationContext.get(User.class));
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute(USER) final User user) {
        userService.registerUser(user);
        return new ModelAndView(REDIRECT_LOGIN);
    }

    @ModelAttribute(USER)
    public User getCommandObject() {
        return new User();
    }

    @InitBinder(USER)
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Collection.class, new CustomTypeEditor());
    }
}
