package net.github.rtc.app.service.impl;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.resource.RoleResource;
import net.github.rtc.app.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class.getName());

    @Override
    @Transactional
    public void createRole(RoleType type) {
        LOG.info("Creating user role with type: " + type);
        roleResource.createRole(type);
    }

    @Override
    @Transactional
    public Role getRoleByType(RoleType type) {
        LOG.info("Geting user role with type: " + type);
        return roleResource.getRoleByType(type);
    }
}
