package net.github.rtc.app.utils.message;

import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.service.message.MessageService;
import net.github.rtc.app.utils.message.event.OrderResponseMessageEvent;
import net.github.rtc.app.utils.message.event.AbstractSendMessageEvent;
import net.github.rtc.app.utils.message.factory.MessageFactory;
import net.github.rtc.app.utils.message.factory.OrderResponseMessageFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SendMessageEventHandler implements ApplicationListener<AbstractSendMessageEvent> {

    @Autowired
    private MessageService messageService;
    @Autowired
    private AbstractMessageFactoryProvider messageFactoryProvider;

    @Override
    public void onApplicationEvent(AbstractSendMessageEvent sendMessageEvent) {
        final Message message = getMessageFactory(sendMessageEvent).getMessage();
        messageService.create(message);
    }

    private MessageFactory getMessageFactory(AbstractSendMessageEvent event) {
        if (event instanceof OrderResponseMessageEvent) {
            final OrderResponseMessageFactoryImpl factory = messageFactoryProvider.getOrderResponseMessageFactory();
            factory.setOrder(((OrderResponseMessageEvent) event).getOrder());
            return factory;
        }
        throw new IllegalArgumentException();
    }
}
