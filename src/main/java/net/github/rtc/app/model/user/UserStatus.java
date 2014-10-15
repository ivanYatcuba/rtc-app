package net.github.rtc.app.model.user;

import java.util.ArrayList;
import java.util.List;

public enum UserStatus {
    ACTIVE, FOR_REMOVAL, INACTIVE;

    public static List<String> findAll() {
        final List<String> res = new ArrayList<>();
        for (final UserStatus status : UserStatus.values()) {
            res.add(status.name());
        }
        return res;
    }
}
