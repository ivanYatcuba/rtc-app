package net.github.rtc.app.model.activity.entityList;


/**
 * Created by Alexey Samoylov on 23.01.2015.
 */
public enum EntityType {

    COURSE("Course"), USER("User"), NEWS("News");
    private final String text;

    private EntityType(final String s) {
        text = s;
    }

    @Override
    public String toString() {
        return text;
    }
}
