package net.github.rtc.app.model.activity.notifiers;


import net.github.rtc.app.model.activity.events.NewEntityEvent;
import org.springframework.context.ApplicationListener;

public class NewEntityNotifier implements ApplicationListener<NewEntityEvent> {
    @Override
    public void onApplicationEvent(NewEntityEvent event) {
        System.out.println("new!");
    }
}
