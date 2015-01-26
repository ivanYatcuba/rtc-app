package net.github.rtc.app.controller;


import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserStatus;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.date.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller("recoverAdminController")
@RequestMapping("recover")
public class RecoverAdminController {

    public static final String ADMIN_STRING = "admin";

    @Autowired
    private UserService userService;
    @Autowired
    private DateService dateService;

    @RequestMapping(method = RequestMethod.GET)
    public String recoverAdmin() {
        final List<User> admins = userService.getUserByRole(RoleType.ROLE_ADMIN);
        if (admins.get(0).isForRemoval()) {
            userService.deleteByCode(admins.get(0).getCode());
        }
        if (admins.size() == 0 || admins.get(0).isForRemoval()) {
            final User admin = new User();
            admin.setEmail(ADMIN_STRING);
            admin.setPassword(ADMIN_STRING);
            admin.setName("Name");
            admin.setMiddleName("MiddleName");
            admin.setSurname("Surname");
            admin.setPhone("1111");
            admin.setEnglish("Intermediate");
            admin.setGender("Male");
            admin.setStatus(UserStatus.ACTIVE);
            admin.setAuthorities(Arrays.asList(userService.getRoleByType(RoleType.ROLE_ADMIN)));
            admin.setRegisterDate(dateService.getCurrentDate());
            userService.create(admin);
            //userService.activateUser(admin.getCode());
        }
        return "redirect:/";
    }

}
