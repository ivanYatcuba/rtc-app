package net.github.rtc.app.model.report;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.user.User;

public enum ReportClasses {
    Course(Course.class), User(User.class);
    private Class value;

    private ReportClasses(Class value) {
        this.value = value;
    }

    public Class getValue() {
        return value;
    }


}
