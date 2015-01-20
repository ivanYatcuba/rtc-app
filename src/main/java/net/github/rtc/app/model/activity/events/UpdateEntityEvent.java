package net.github.rtc.app.model.activity.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;

public class UpdateEntityEvent extends ApplicationEvent {

    private String details;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public UpdateEntityEvent(Object source, String details) {
        super(source);
        this.details = details;
    }
}
