package net.github.rtc.app.utils.message.event;

import net.github.rtc.app.model.entity.order.UserCourseOrder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class MessageEventCreator implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void createOrderResponseMessageEvent(UserCourseOrder order) {
        publisher.publishEvent(new OrderResponseMessageEvent(this, order));
    }
}
