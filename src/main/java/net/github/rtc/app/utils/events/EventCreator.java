package net.github.rtc.app.utils.events;


import net.github.rtc.app.model.entity.activity.ActivityAction;
import net.github.rtc.app.model.entity.activity.ActivityEntity;
import net.github.rtc.app.model.entity.activity.IActivity;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
public class EventCreator implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    public void createAndPublishEvent(Object source, IActivity activity, ActivityEntity entity, ActivityAction action) {
        final ActivityEvent event = new ActivityEvent(source, activity.getLogDetail(), entity, action);
        publisher.publishEvent(event);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

}
