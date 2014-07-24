package net.github.rtc.app.resource;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;

/**
 * Created by Ivan Yatcuba on 7/24/14.
 */
public interface RoleResource {
    Role getRoleByType(RoleType type);
    void createRole(RoleType type);
}
