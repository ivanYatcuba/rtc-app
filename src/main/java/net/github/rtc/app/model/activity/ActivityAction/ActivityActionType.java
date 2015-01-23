package net.github.rtc.app.model.activity.ActivityAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey Samoylov on 23.01.2015.
 */
public enum ActivityActionType {
    UPDATED("Updated"), SAVED("Saved"), REMOVED("Removed");
    private final String text;

    @Override
    public String toString() {
        return text;
    }

    private ActivityActionType(final String s) {
        text = s;
    }

}
