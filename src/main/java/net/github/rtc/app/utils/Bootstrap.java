package net.github.rtc.app.utils;

import net.github.rtc.app.model.activity.Activity;
import net.github.rtc.app.model.activity.ActivityAction;
import net.github.rtc.app.model.activity.ActivityEntity;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserStatus;
import net.github.rtc.app.service.ActivityService;
import net.github.rtc.app.utils.date.DateService;
import net.github.rtc.app.service.user.UserService;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

@Component
@DependsOn("allowEncryptionWithoutJCE")
public class Bootstrap implements InitializingBean {

    private static final String STRING_ADMIN = "admin";
    private static final String ADMIN_EMAIL = "admin@email.com";
    @Autowired
    private HibernatePBEStringEncryptor hibernateStringEncryptor;
    @Autowired
    private UserService userService;
    @Autowired
    private DateService dateService;
    @Autowired
    private ActivityService activityService;

    public void loadTestUsers() {
        if (userService.loadUserByUsername(ADMIN_EMAIL) == null) {
            if (userService.getRoleByType(RoleType.ROLE_ADMIN) == null) {
                userService.createRole(RoleType.ROLE_ADMIN);
            }
            if (userService.getRoleByType(RoleType.ROLE_USER) == null) {
                userService.createRole(RoleType.ROLE_USER);
            }
            if (userService.getRoleByType(RoleType.ROLE_EXPERT) == null) {
                userService.createRole(RoleType.ROLE_EXPERT);
            }

            final User admin = new User("TestName", "TestMiddlename", "TestSurname", ADMIN_EMAIL, STRING_ADMIN);
            admin.setAuthorities(Arrays.asList(userService.getRoleByType(RoleType.ROLE_ADMIN)));
            admin.setRegisterDate(dateService.getCurrentDate());
            admin.setGender("Male");
            admin.setPhone("123456");
            admin.setNote("note");
            admin.setBirthDate(dateService.getCurrentDate());
            admin.setStatus(UserStatus.ACTIVE);
            admin.setEnglish("eng");
            userService.create(admin);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadTestUsers();
    }
}
