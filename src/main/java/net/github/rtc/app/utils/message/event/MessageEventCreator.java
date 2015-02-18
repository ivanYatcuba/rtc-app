package net.github.rtc.app.utils.message.event;

import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.utils.message.factory.order.OrderMessageFactory;
import net.github.rtc.app.utils.message.factory.order.OrderResponseMessageFactoryImpl;
import net.github.rtc.app.utils.message.factory.order.OrderSendMessageFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class MessageEventCreator implements ApplicationEventPublisherAware {

    @Autowired
    private ApplicationContext appContext;

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void createOrderResponseMessageEvent(UserCourseOrder order) {
        final OrderMessageFactory factory = appContext.getBean(OrderResponseMessageFactoryImpl.class);
        publisher.publishEvent(new OrderMessageEvent(this, order, factory));
    }

    public void createOrderSendMessageEvent(UserCourseOrder order) {
        final OrderMessageFactory factory = appContext.getBean(OrderSendMessageFactoryImpl.class);
        publisher.publishEvent(new OrderMessageEvent(this, order, factory));
    }

}
