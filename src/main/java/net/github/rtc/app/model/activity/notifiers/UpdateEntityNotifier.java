package net.github.rtc.app.model.activity.notifiers;

import net.github.rtc.app.model.activity.Activity;
import net.github.rtc.app.model.activity.ActivityAction;
import net.github.rtc.app.model.activity.events.UpdateEntityEvent;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.ActivityService;
import net.github.rtc.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;


public class UpdateEntityNotifier implements ApplicationListener<UpdateEntityEvent> {

    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    @Override
    public void onApplicationEvent(UpdateEntityEvent event) {
        System.out.println("hello!");
        User user = userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Activity activity = new Activity();
        activity.setUsername(user.shortString());
        activity.setDetail(event.getDetails());
        activity.setAction(ActivityAction.UPDATED);
        activity.setActionDate(new Date());
        activity.setEntity(event.getEntity());
        activityService.create(activity);
        if ((activity.getId() % 2) == 0) {
            activityService.deleteByCode(activity.getCode());
        }
    }
}
