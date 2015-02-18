package net.github.rtc.app.utils.message.event;

import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.utils.message.factory.order.OrderMessageFactory;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class OrderMessageEvent extends ApplicationEvent {

    private OrderMessageFactory factory;

    public OrderMessageEvent(Object source, UserCourseOrder order, OrderMessageFactory
            factory) {

        super(source);
        factory.setOrder(order);
        this.factory = factory;
    }

    public List<Message> getMessages() {
        return factory.getMessages();
    }
}
