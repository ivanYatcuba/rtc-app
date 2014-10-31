package net.github.rtc.app.model.user;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladislav Pikus
 */
public enum RoleType {
    ALL("ALL"), ROLE_USER("User"), ROLE_ADMIN("Administrator"), ROLE_EXPERT("Expert");
    private final String roleViewName;

    RoleType(final String s) {
        roleViewName = s;
    }

    public String getRoleViewName() {
        return roleViewName;
    }
    public static List<String> findAll() {
        final List<String> res = new ArrayList<>();
        for (final RoleType status : RoleType.values()) {
            res.add(status.name());
        }
        return res;
    }
}
