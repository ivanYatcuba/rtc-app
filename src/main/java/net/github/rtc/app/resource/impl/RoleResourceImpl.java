package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.resource.RoleResource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Ivan Yatcuba on 7/24/14.
 */
@Repository
public class RoleResourceImpl implements RoleResource {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Role getRoleByType(RoleType type) {
        String query = "select role from Role role where role.name = :name";
        return (Role)sessionFactory.getCurrentSession().createQuery(query).setString("name", type.name()).uniqueResult();
    }

    @Override
    public void createRole(RoleType type) {
        sessionFactory.getCurrentSession().save(new Role(type));
    }
}
