package net.github.rtc.app.model.activity;


import java.util.Arrays;
import java.util.List;

public enum ActivityAction {

    SAVED("SAVED"), UPDATED("UPDATED"), REMOVED("REMOVED");

    private String code;

    ActivityAction(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        String name = "";
        switch (ordinal()) {
            case 0:
                name = "Saved";
                break;
            case 1:
                name = "Updated";
                break;
            case 2:
                name = "Removed";
                break;
            default:
                name = "Incorrect action";
                break;
        }
        return name;
    }

    public static List<ActivityAction> findAll() {
        return Arrays.asList(ActivityAction.values());
    }

    public String getCode() {
        return this.code;
    }
}
