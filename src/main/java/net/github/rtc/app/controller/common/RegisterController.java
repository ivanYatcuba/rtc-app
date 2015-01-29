package net.github.rtc.app.controller.common;

import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserStatus;
import net.github.rtc.app.service.MailService;
import net.github.rtc.app.service.date.DateService;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final String USER = "user";
    @Autowired
    private ValidationContext validationContext;
    @Autowired
    private UserService userService;
    @Autowired
    private DateService dateService;
    @Autowired
    private MailService mailService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView openRegisterPage() {
        final ModelAndView mav = new ModelAndView("register/register");
        mav.addObject("validationRules", validationContext.get(User.class));
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(
      @ModelAttribute(USER) final User user) {
        user.setStatus(UserStatus.ACTIVE);
        user.setAuthorities(Arrays.asList(userService.getRoleByType(RoleType.ROLE_USER)));
        user.setRegisterDate(dateService.getCurrentDate());
        userService.create(user);
        mailService.sendRegistrationMail(user);

        return new ModelAndView("redirect:/login/");
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
