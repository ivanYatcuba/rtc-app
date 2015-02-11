package net.github.rtc.app.utils.notifiers;

import net.github.rtc.app.model.entity.activity.Activity;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.activity.ActivityService;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.utils.AuthorizedUserProvider;
import net.github.rtc.app.utils.events.ActivityEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

public class ActivityNotifier implements ApplicationListener<ActivityEvent> {


    @Autowired
    private ActivityService activityService;
    @Autowired
    private DateService dateService;

    @Override
    public void onApplicationEvent(ActivityEvent event) {
            final User user = AuthorizedUserProvider.getAuthorizedUser();
            final Activity activity = new Activity();

            activity.setUsername(user.toString());
            activity.setDetail(event.getDetails());
            activity.setAction(event.getAction());
            activity.setActionDate(dateService.getCurrentDate());
            activity.setEntity(event.getEntity());

            activityService.create(activity);
    }
}
