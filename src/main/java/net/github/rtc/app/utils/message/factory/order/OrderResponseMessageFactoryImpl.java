package net.github.rtc.app.utils.message.factory.order;

import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.model.entity.message.MessageType;
import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.model.entity.order.UserRequestStatus;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.security.AuthorizedUserProvider;
import net.github.rtc.app.utils.TemplateStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("responseMessageFactoryImpl")
public class OrderResponseMessageFactoryImpl implements OrderMessageFactory {

    private static final String ACCEPTED_TEMPLATE_PATH = "/templates/messages/orderAcceptedMessage.ftl";
    private static final String REJECTED_TEMPLATE_PATH = "/templates/messages/orderRejectedMessage.ftl";

    @Autowired
    private DateService dateService;

    @Autowired
    private TemplateStringBuilder templateStringBuilder;

    @Override
    public List<Message> getMessages(UserCourseOrder order) {
        final Message msg = new Message();
        msg.setSenderUserCode(AuthorizedUserProvider.getAuthorizedUser().getCode());
        msg.setReceiverUserCode(order.getUserCode());
        msg.setSendingDate(dateService.getCurrentDate());
        msg.setType(MessageType.SYSTEM);
        msg.setText(getMessageText(order.getStatus()));

        return Arrays.asList(msg);
    }

    private String getMessageText(UserRequestStatus status) {
        switch (status) {
            case ACCEPTED: return templateStringBuilder.build(ACCEPTED_TEMPLATE_PATH);
            case REJECTED: return templateStringBuilder.build(REJECTED_TEMPLATE_PATH);
            default: throw new IllegalArgumentException();
        }
    }
}
