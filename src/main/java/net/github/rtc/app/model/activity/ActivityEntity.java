package net.github.rtc.app.model.activity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ActivityEntity {
    USER("USER"), COURSE("COURSE"), NEWS("NEWS");

    private String code;

    ActivityEntity(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        String name = "";
        switch (ordinal()) {
            case 0:
                name = "User";
                break;
            case 1:
                name = "Course";
                break;
            case 2:
                name = "News";
                break;
            default:
                name = "Incorrect entity";
                break;
        }
        return name;
    }

    public static List<ActivityEntity> findAll() {
        return Arrays.asList(ActivityEntity.values());
    }

    public String getCode() {
        return this.code;
    }
}
