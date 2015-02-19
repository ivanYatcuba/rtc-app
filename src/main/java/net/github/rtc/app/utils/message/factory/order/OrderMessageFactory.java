package net.github.rtc.app.utils.message.factory.order;

import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.utils.message.factory.MessageFactory;

public interface OrderMessageFactory extends MessageFactory {
    void setOrder(UserCourseOrder order);
}
