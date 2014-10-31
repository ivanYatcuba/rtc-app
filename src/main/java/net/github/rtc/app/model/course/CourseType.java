package net.github.rtc.app.model.course;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dell on 7/22/14.
 */
public enum CourseType {
    QA("QA"), DEV("DEV"), BA("BA");

    private String code;

    CourseType(String code) {
        this.code = code;
    }

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

    public static List<CourseType> findAll() {
        return Arrays.asList(CourseType.values());
    }

    public String getCode() {
        return this.code;
    }
}

