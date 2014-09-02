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
import net.github.rtc.app.utils.search.SearchCriteria;
import net.github.rtc.app.utils.search.SearchResults;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 * @author Саша
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

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

    @Override
    public List<User> getUserByType(RoleType type) {
        return sessionFactory.getCurrentSession().createCriteria(User.class).createAlias("authorities", "a").
                add(Restrictions.eq("a.name", type.name())).list();
    }

    @Override
    public SearchResults<User> search(SearchCriteria<User> userSearchCriteria) {
        SearchResults<User> userSearchResults = new SearchResults<>();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        for(String restriction : userSearchCriteria.getSearchCriteria().keySet()){
            criteria.add(Restrictions.eq(restriction, userSearchCriteria.getSearchCriteria().get(restriction)));
        }
        if(userSearchCriteria.getPageSize() > 0){
            userSearchResults.setResults(criteria.setFirstResult(userSearchCriteria.getPageSize()).list());
        }else {
            userSearchResults.setResults(criteria.list());
        }
        userSearchResults.setTotalResults(((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue());
        return userSearchResults;
    }
}
