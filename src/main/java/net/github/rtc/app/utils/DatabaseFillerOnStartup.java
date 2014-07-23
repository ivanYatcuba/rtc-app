package net.github.rtc.app.utils;

import net.github.rtc.app.model.Role;
import net.github.rtc.app.model.RoleTypes;
import net.github.rtc.app.model.User;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.service.UserServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Ivan Yatcuba on 7/22/14.
 */
@Component
public class DatabaseFillerOnStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    UserService userService;
    @Autowired
    UserServiceLogin userServiceLogin;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(userServiceLogin.loadUserByUsername("admin") == null){
            User admin = new User("TestName","TestMiddlename","TestSurname", "admin", "admin");
            admin.setAuthorities(Arrays.asList(new Role(RoleTypes.ROLE_ADMIN), new Role(RoleTypes.ROLE_USER)));
            userService.create(admin);
        }
    }
}
