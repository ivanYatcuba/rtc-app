package net.github.rtc.app.model.activity.events;

import org.springframework.context.ApplicationEvent;

public class DeleteEntityEvent extends ApplicationEvent {

    private String details;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public DeleteEntityEvent(Object source, String details) {
        super(source);
        this.details = details;
    }
}
