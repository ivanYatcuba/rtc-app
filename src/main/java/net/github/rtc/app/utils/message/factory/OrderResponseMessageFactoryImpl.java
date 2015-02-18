package net.github.rtc.app.utils.message.factory;

import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.model.entity.message.MessageType;
import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.utils.AuthorizedUserProvider;
import net.github.rtc.app.utils.StringFromTemplateBuilder;
import net.github.rtc.app.utils.message.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrderResponseMessageFactoryImpl implements MessageFactory {

    private static final String ACCEPTED_TEMPLATE_PATH = "/templates/messages/orderAcceptedMessage.ftl";
    private static final String REJECTED_TEMPLATE_PATH = "/templates/messages/orderRejectedMessage.ftl";

    private UserCourseOrder order;

    @Autowired
    private StringFromTemplateBuilder stringFromTemplateBuilder;
    @Autowired
    private DateService dateService;

    public void setOrder(UserCourseOrder order) {
        this.order = order;
    }

    @Override
    public Message getMessage() {
        return new MessageBuilder().setSenderCode(AuthorizedUserProvider.getAuthorizedUser().getCode()).
                setReceiverCode(order.getUserCode()).
                setSendingDate(dateService.getCurrentDate()).
                setType(MessageType.USER).setText(getMessageText()).build();
    }

    private String getMessageText() {
        switch (order.getStatus()) {
            case ACCEPTED: return stringFromTemplateBuilder.setTemplate(ACCEPTED_TEMPLATE_PATH).build();
            case REJECTED: return stringFromTemplateBuilder.setTemplate(REJECTED_TEMPLATE_PATH).build();
            default: throw new IllegalArgumentException();
        }
    }
}
