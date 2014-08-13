package net.github.rtc.app.utils;

import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.service.UserServiceLogin;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by Ivan Yatcuba on 7/22/14.
 */
@Component
public class Bootstrap implements InitializingBean {
    @Autowired
    UserService userService;
    @Autowired
    UserServiceLogin userServiceLogin;


    public void loadTestUsers() {
        if(userServiceLogin.loadUserByUsername("admin") == null){
            userService.createRole(RoleType.ROLE_ADMIN);
            userService.createRole(RoleType.ROLE_USER);
            userService.createRole(RoleType.ROLE_EXPERT);
            User admin = new User("TestName","TestMiddlename","TestSurname", "admin", "admin");
            admin.setAuthorities(Arrays.asList(userService.getRoleByType(RoleType.ROLE_ADMIN)));
            admin.setRegisterDate(new Date());
            userService.create(admin);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadTestUsers();
    }
}
