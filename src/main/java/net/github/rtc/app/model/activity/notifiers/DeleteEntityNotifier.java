package net.github.rtc.app.model.activity.notifiers;

import net.github.rtc.app.model.activity.Activity;
import net.github.rtc.app.model.activity.ActivityAction;
import net.github.rtc.app.model.activity.events.DeleteEntityEvent;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.ActivityService;
import net.github.rtc.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

public class DeleteEntityNotifier implements ApplicationListener<DeleteEntityEvent> {

    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    @Override
    public void onApplicationEvent(DeleteEntityEvent event) {
        System.out.println("delete!");
        User user = userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Activity activity = new Activity();
        activity.setUsername(user.shortString());
        activity.setDetail(event.getDetails());
        activity.setAction(ActivityAction.REMOVED);
        activity.setActionDate(new Date());
        activity.setEntity(event.getEntity());
        activityService.create(activity);
    }
}
