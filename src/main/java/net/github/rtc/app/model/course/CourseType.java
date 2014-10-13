package net.github.rtc.app.model.course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 7/22/14.
 */
public enum CourseType {
    QA, DEV, BA;

    @Override
    public String toString() {
        String name = "";
        switch (ordinal()) {
            case 0:
                name = "Quality Assurance";
                break;
            case 1:
                name = "Development";
                break;
            case 2:
                name = "Business Analysis";
                break;
            default:
                name = "Incorret type";
                break;
        }
        return name;
    }

    public static List<String> findAll() {
        final List<String> res = new ArrayList<>();
        for (final CourseType type : CourseType.values()) {
            res.add(type.name());
        } return res;
    }
}

