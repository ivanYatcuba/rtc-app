package net.github.rtc.app.model.activity;


import java.util.ArrayList;
import java.util.List;

public enum ActivityAction {
    SAVED, UPDATED, REMOVED;
    public static List<String> findAll() {
        final List<String> res = new ArrayList<>();
        for (final ActivityAction action : ActivityAction.values()) {
            res.add(action.name());
        }
        return res;
    }
}
