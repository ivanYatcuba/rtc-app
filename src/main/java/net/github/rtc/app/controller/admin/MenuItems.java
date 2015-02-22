package net.github.rtc.app.controller.admin;

public enum MenuItems {
    ACTIVITY("activity"),
    NEWS("news"),
    COURSE("course"),
    USER("user"),
    REPORT("report"),
    LOGS("logs");

    private final String text;

    private MenuItems(String s) {
        text = s;
    }

    public String toString() {
        return text;
    }
}
