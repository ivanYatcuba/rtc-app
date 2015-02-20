package net.github.rtc.app.utils.message.factory.order;

import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.model.entity.order.UserCourseOrder;

import java.util.List;

public interface OrderMessageFactory {
    List<Message> getMessages(UserCourseOrder order);
}
