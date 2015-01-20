package net.github.rtc.app.model.activity.notifiers;

import net.github.rtc.app.model.activity.events.DeleteEntityEvent;
import org.springframework.context.ApplicationListener;

public class DeleteEntityNotifier implements ApplicationListener<DeleteEntityEvent> {
    @Override
    public void onApplicationEvent(DeleteEntityEvent event) {
        System.out.println("delete!");
    }
}
