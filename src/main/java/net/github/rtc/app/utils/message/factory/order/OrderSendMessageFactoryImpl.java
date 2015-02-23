package net.github.rtc.app.utils.message.factory.order;

import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.model.entity.message.MessageType;
import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.utils.TemplateStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("orderSendMessageFactory")
public class OrderSendMessageFactoryImpl implements OrderMessageFactory {

    private static final String NEW_ORDER_TEMPLATE_PATH = "/templates/messages/newOrderMessage.ftl";

    @Autowired
    private DateService dateService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TemplateStringBuilder templateStringBuilder;

    @Override
    public List<Message> getMessages(UserCourseOrder order) {
        final List<Message> msgList = new ArrayList<>();
        final Course course = courseService.findByCode(order.getCourseCode());

        for (User expert : course.getExperts()) {
            final Message msg = new Message();
            msg.setSenderUserCode(order.getUserCode());
            msg.setSendingDate(dateService.getCurrentDate());
            msg.setType(MessageType.SYSTEM);
            msg.setText(getMessageText());
            msg.setReceiverUserCode(expert.getCode());
            msgList.add(msg);
        }
        return msgList;
    }

    private String getMessageText() {
            return templateStringBuilder.build(NEW_ORDER_TEMPLATE_PATH);
        }

}
