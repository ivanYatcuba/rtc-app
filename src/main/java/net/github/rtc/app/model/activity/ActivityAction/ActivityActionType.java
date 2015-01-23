package net.github.rtc.app.model.activity.ActivityAction;

/**
 * Created by Alexey Samoylov on 23.01.2015.
 */
public enum ActivityActionType {
    UPDATED("Updated"), SAVED("Saved"), REMOVED("Removed");
    private final String text;

    private ActivityActionType(final String s) {
        text = s;
    }

    @Override
    public String toString() {
        return text;
    }

}
