package net.github.rtc.app.service;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;

/**
 * Created by Ivan Yatcuba on 7/24/14.
 */
public interface RoleService {
    void createRole(RoleType type);
    Role getRoleByType(RoleType type);

}
