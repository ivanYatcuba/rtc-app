package net.github.rtc.app.utils.notifiers;

import net.github.rtc.app.model.activity.Activity;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.activity.ActivityService;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.events.ActivityEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ActivityNotifier implements ApplicationListener<ActivityEvent> {

    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private DateService dateService;

    @Override
    public void onApplicationEvent(ActivityEvent event) {
        final Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            final String username = authentication.getName();
            final User user = userService.loadUserByUsername(username);
            final Activity activity = new Activity();
            activity.setUsername(user.shortString());
            activity.setDetail(event.getDetails());
            activity.setAction(event.getAction());
            activity.setActionDate(dateService.getCurrentDate());
            activity.setEntity(event.getEntity());
            activityService.create(activity);
        }
    }
}
