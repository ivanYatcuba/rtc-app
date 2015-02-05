package net.github.rtc.app.model.entity.course;

public enum CourseType {
    QA("QA"), DEV("DEV"), BA("BA");

    private String code;

    CourseType(String code) {
        this.code = code;
    }

    public String toString() {
        return this.code;
    }
}

