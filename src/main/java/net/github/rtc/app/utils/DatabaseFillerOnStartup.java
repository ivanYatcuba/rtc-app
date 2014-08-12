package net.github.rtc.app.utils;

import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.service.UserServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by Ivan Yatcuba on 7/22/14.
 */
//todo: rename class to Bootstrap.java
//todo: add InitializingBean interface instead of ApplicationListener
//todo: rename onApplicationEvent method to loadTestUsers
//todo: implement afterPropertiesSet method and call loadTestUsers
@Component
public class DatabaseFillerOnStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    UserService userService;
    @Autowired
    UserServiceLogin userServiceLogin;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
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
}
