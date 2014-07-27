package net.github.rtc.app.service.impl;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.resource.RoleResource;
import net.github.rtc.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ivan Yatcuba on 7/24/14.
 */
//todo: move to UserServiceImpl
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleResource roleResource;

    @Override
    @Transactional
    public void createRole(RoleType type) {
        roleResource.createRole(type);
    }

    @Override
    @Transactional
    public Role getRoleByType(RoleType type) {
        return roleResource.getRoleByType(type);
    }
}
