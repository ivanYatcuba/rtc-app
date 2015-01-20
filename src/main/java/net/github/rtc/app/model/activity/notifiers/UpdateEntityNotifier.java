package net.github.rtc.app.model.activity.notifiers;

import net.github.rtc.app.model.activity.events.UpdateEntityEvent;
import org.springframework.context.ApplicationListener;


public class UpdateEntityNotifier implements ApplicationListener<UpdateEntityEvent> {
    @Override
    public void onApplicationEvent(UpdateEntityEvent event) {
        System.out.println("hello!");
    }
}
