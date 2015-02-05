package net.github.rtc.app.model.entity.report;

import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.user.User;

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
