/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.github.rtc.app.dao.impl;

import net.github.rtc.app.dao.UserDao;
import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;



/**
 * @author Саша
 */
@Repository
public class UserDaoImpl extends AbstractGenericDaoImpl<User> implements UserDao {

    public static final String EMAIL_STRING = "email";

    @Override
    public User findByEmail(final String email) {
        return (User) getCurrentSession().createCriteria(User
          .class).
          add(Restrictions.eq(EMAIL_STRING, email)).uniqueResult();
    }

    @Override
    public Role getRoleByType(final RoleType type) {
        return (Role) getCurrentSession().createCriteria(Role
          .class).
          add(Restrictions.eq(EMAIL_STRING, type.name())).uniqueResult();
    }

    @Override
    public void createRole(final RoleType type) {
        getCurrentSession().save(new Role(type));
    }

    @Override
    public List<User> getUsersByType(final RoleType type) {
        return getCurrentSession().createCriteria(
          User.class).createAlias("authorities", "a").
          add(Restrictions.eq("a.name", type.name())).list();
    }

    @Override
    public void deletingUser() {
        final Session session = getCurrentSession();
        final Collection<User> listUser = session.createQuery("from User u where u.removalDate is not null").list();
        for (final User user : listUser) {
            session.delete(user);
        }
    }

}
