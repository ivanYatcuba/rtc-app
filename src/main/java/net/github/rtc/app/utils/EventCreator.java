package net.github.rtc.app.utils;


import net.github.rtc.app.model.activity.Activity;
import net.github.rtc.app.model.activity.ActivityAction;
import net.github.rtc.app.model.activity.ActivityEntity;
import net.github.rtc.app.model.activity.IActivity;
import net.github.rtc.app.utils.events.ActivityEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
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
