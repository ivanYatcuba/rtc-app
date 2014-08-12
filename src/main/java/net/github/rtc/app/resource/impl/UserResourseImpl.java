/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.resource.UserResource;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Саша
 */
@Repository
public class UserResourseImpl extends GenericResourceImpl<User> implements UserResource{

    @Override
    public User findByEmail(String email) {
        return (User)sessionFactory.getCurrentSession().createCriteria(User.class).
                add(Restrictions.eq("email", email)).uniqueResult();
    }

    @Override
    public Role getRoleByType(RoleType type) {
        return (Role)sessionFactory.getCurrentSession().createCriteria(Role.class).
                add(Restrictions.eq("name", type.name())).uniqueResult();
    }

    @Override
    public void createRole(RoleType type) {
        sessionFactory.getCurrentSession().save(new Role(type));
    }
}
