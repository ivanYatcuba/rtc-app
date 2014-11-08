package net.github.rtc.app.utils;

import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserStatus;
import net.github.rtc.app.service.DateService;
import net.github.rtc.app.service.UserService;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;

@Component
public class Bootstrap implements InitializingBean {

    private static final String STRING_ADMIN = "admin";
    @Autowired
    private HibernatePBEStringEncryptor hibernateStringEncryptor;
    @Autowired
    private UserService userService;
    @Autowired
    private DateService dateService;

    public void loadTestUsers() {
        if (userService.loadUserByUsername(STRING_ADMIN) == null) {
            if (userService.getRoleByType(RoleType.ROLE_ADMIN) == null) {
                userService.createRole(RoleType.ROLE_ADMIN);
            }
            if (userService.getRoleByType(RoleType.ROLE_USER) == null) {
                userService.createRole(RoleType.ROLE_USER);
            }
            if (userService.getRoleByType(RoleType.ROLE_EXPERT) == null) {
                userService.createRole(RoleType.ROLE_EXPERT);
            }

            final User admin = new User("TestName", "TestMiddlename", "TestSurname", STRING_ADMIN, STRING_ADMIN);
            admin.setAuthorities(Arrays.asList(userService.getRoleByType(RoleType.ROLE_ADMIN)));
            admin.setRegisterDate(dateService.getCurrentDate());
            admin.setGender("Male");
            admin.setStatus(UserStatus.ACTIVE);
            userService.create(admin);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            final Field field = Class.forName("javax.crypto.JceSecurity").
              getDeclaredField("isRestricted");
            field.setAccessible(true);
            field.set(null, java.lang.Boolean.FALSE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadTestUsers();
    }
}
