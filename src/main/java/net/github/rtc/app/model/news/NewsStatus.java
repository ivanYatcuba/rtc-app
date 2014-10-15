package net.github.rtc.app.model.news;

import java.util.ArrayList;
import java.util.List;

public enum NewsStatus {
    DRAFT, PUBLISHED, ALL;

    public static List<String> findAll() {
        final List<String> res = new ArrayList<>();
        for (final NewsStatus status : NewsStatus.values()) {
            res.add(status.name());
        }
        return res;
    }

}
