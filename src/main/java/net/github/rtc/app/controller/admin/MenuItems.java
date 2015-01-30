package net.github.rtc.app.controller.admin;

/**
 * Created by Alexey Samoylov on 28.01.2015.
 */
public enum MenuItems {
    ACTIVITY("activity"),
    NEWS("news"),
    COURSE("course"),
    USER("user"),
    REPORT("report");

    private final String text;

    private MenuItems(String s) {
        text = s;
    }

    public static boolean contains(String items) {

        for (MenuItems c : MenuItems.values()) {
            if (c.toString().equals(items)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return text;
    }
}
