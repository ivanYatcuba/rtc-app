package net.github.rtc.app.model.course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Yatcuba on 7/22/14.
 */
public enum CourseStatus {
    DRAFT, PUBLISHED;

    public static List<String> findAll() {
        List<String> res = new ArrayList<>();
        for(CourseStatus status : CourseStatus.values()){
            res.add(status.name());
        }
        return res;
    }
}
