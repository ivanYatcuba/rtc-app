package net.github.rtc.app.model.activity;


import java.util.ArrayList;
import java.util.List;

public enum ActivityEntity {
    USER, COURSE, NEWS;
    public static List<String> findAll() {
        final List<String> res = new ArrayList<>();
        for (final ActivityEntity entity : ActivityEntity.values()) {
            res.add(entity.name());
        }
        return res;
    }
}
