package net.github.rtc.app.utils.message.event;

import org.springframework.context.ApplicationEvent;

public abstract class AbstractSendMessageEvent extends ApplicationEvent {

    public AbstractSendMessageEvent(Object source) {
        super(source);
    }

}
