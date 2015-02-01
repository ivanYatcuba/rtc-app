package net.github.rtc.app.model.course;

import java.util.ArrayList;
import java.util.List;

public enum CourseStatus {
    DRAFT, PUBLISHED, ARCHIVED;
    public static List<String> findAll() {
        final List<String> res = new ArrayList<>();
        for (final CourseStatus status : CourseStatus.values()) {
            res.add(status.name());
        }
        return res;
    }
}
