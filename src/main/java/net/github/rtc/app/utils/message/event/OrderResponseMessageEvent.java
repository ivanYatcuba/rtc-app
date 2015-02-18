package net.github.rtc.app.utils.message.event;

import net.github.rtc.app.model.entity.order.UserCourseOrder;

public class OrderResponseMessageEvent extends AbstractSendMessageEvent {

    private UserCourseOrder order;

    public OrderResponseMessageEvent(Object source, UserCourseOrder order) {
        super(source);
        this.order = order;
    }

    public UserCourseOrder getOrder() {
        return order;
    }
}
