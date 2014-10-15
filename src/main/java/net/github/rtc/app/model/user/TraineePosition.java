package net.github.rtc.app.model.user;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 22.04.14.
 */
public enum TraineePosition {
    TESTER, DEVELOPER, BUSINESS_ANALYST;

    public static List<String> findAll() {
        final List<String> res = new ArrayList<>();
        for (final TraineePosition type : TraineePosition.values()) {
            res.add(type.name());
        } return res;
    }
}
