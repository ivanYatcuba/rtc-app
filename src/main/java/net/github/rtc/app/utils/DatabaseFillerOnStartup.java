package net.github.rtc.app.utils;

import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.RoleService;
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
    @Autowired
    RoleService roleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(userServiceLogin.loadUserByUsername("admin") == null){
            roleService.createRole(RoleType.ROLE_ADMIN);
            roleService.createRole(RoleType.ROLE_USER);
            roleService.createRole(RoleType.ROLE_EXPERT);
            User admin = new User("TestName","TestMiddlename","TestSurname", "admin", "admin");
            admin.setAuthorities(Arrays.asList(roleService.getRoleByType(RoleType.ROLE_ADMIN),
                    roleService.getRoleByType(RoleType.ROLE_USER)));
            userService.create(admin);
        }
    }
}
