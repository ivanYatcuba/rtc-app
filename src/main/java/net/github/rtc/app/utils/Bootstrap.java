package net.github.rtc.app.utils;

import net.github.rtc.app.export.JobManager;
import net.github.rtc.app.export.JobManagerAction;
import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.ReportService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.service.UserServiceLogin;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ReportService reportService;
    @Autowired
    private JobManager jobManager;


    public void loadTestUsers() {
        if(userServiceLogin.loadUserByUsername("admin") == null){
            if(userService.getRoleByType(RoleType.ROLE_ADMIN) == null){
                userService.createRole(RoleType.ROLE_ADMIN);
            }
            if(userService.getRoleByType(RoleType.ROLE_USER) == null){
                userService.createRole(RoleType.ROLE_USER);
            }
            if(userService.getRoleByType(RoleType.ROLE_EXPERT) == null){
                userService.createRole(RoleType.ROLE_EXPERT);
            }

            User admin = new User("TestName","TestMiddlename","TestSurname", "admin", "admin");
            admin.setAuthorities(Arrays.asList(userService.getRoleByType(RoleType.ROLE_ADMIN)));
            admin.setRegisterDate(new Date());
            userService.create(admin);
        }
        for(ReportDetails reportDetails : reportService.getAll()){
            try {
                jobManager.manageJob(reportDetails, JobManagerAction.CREATE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadTestUsers();
    }
}
