package net.github.rtc.app.utils;


import net.github.rtc.app.model.activity.ActivityAction;
import net.github.rtc.app.model.activity.ActivityEntity;
import net.github.rtc.app.model.activity.IActivity;
import net.github.rtc.app.utils.events.ActivityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class EventCreator implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;
    private Object source;
    private ActivityEntity entity;

    public EventCreator(Object source, ActivityEntity entity) {
        this.source = source;
        this.entity = entity;
    }

    public void createAndPublishEvent(IActivity activity, ActivityAction action) {
        final ActivityEvent event = new ActivityEvent(source, activity.getLogDetail(), entity, action);
        publisher.publishEvent(event);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
}
