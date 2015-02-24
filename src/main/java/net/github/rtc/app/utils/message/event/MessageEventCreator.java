package net.github.rtc.app.utils.message.event;

import net.github.rtc.app.model.entity.order.UserCourseOrder;

import net.github.rtc.app.model.event.OrderMessageEvent;
import net.github.rtc.app.utils.message.factory.order.OrderResponseMessageFactoryImpl;
import net.github.rtc.app.utils.message.factory.order.OrderSendMessageFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class MessageEventCreator implements ApplicationEventPublisherAware {

    @Autowired
    private OrderResponseMessageFactoryImpl orderResponseMessageFactory;

    @Autowired
    private OrderSendMessageFactoryImpl orderSendMessageFactory;

    private ApplicationEventPublisher publisher;


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void createOrderResponseMessageEvent(UserCourseOrder order) {
        publisher.publishEvent(new OrderMessageEvent(this, orderResponseMessageFactory.getMessages(order)));
    }

    public void createOrderSendMessageEvent(UserCourseOrder order) {
        publisher.publishEvent(new OrderMessageEvent(this, orderSendMessageFactory.getMessages(order)));
    }

}
