package net.github.rtc.app.dao.impl;

import net.github.rtc.app.dao.*;
import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mvc-dao-test.xml")
public class UserDaoTest extends AbstractGenericDaoTest<User> {
    @Autowired
    private UserDao userDao;

    @Override
    protected GenericDao<User> getGenericDao() {
        return userDao;
    }

    @Override
    protected ModelBuilder getModelBuilder() {
        return daoTestContext.getModelBuilder(User.class);
    }

    @Override
    protected EqualityChecker getEqualityChecker() {
        return daoTestContext.getEqualityChecker(User.class);
    }

    @Test
    @Transactional
    public void testFindByEmail(){
        User user1 = new User();
        user1.setEmail("email1");
        User user2 = new User();
        user2.setEmail("email2");
        getGenericDao().create(user1);
        getGenericDao().create(user2);
        User foundUser  = userDao.findByEmail("email1");
        getEqualityChecker().check(foundUser, user1);
    }

    @Test
    @Transactional
    public void testGetRoleByType(){
        Role foundRole = userDao.getRoleByType(RoleType.ROLE_ADMIN);
        assertEquals(foundRole.getName(), RoleType.ROLE_ADMIN);
    }

    @Test
    @Transactional
    public void testCreateRole(){
        assertNotNull(userDao.createRole(RoleType.ROLE_EXPERT));
    }

    @Test
    @Transactional
    public void testGetUsersByType(){
        User user1 = new User();
        user1.setAuthorities(Arrays.asList(userDao.getRoleByType(RoleType.ROLE_ADMIN)));
        User user2 = new User();
        user2.setAuthorities(Arrays.asList(userDao.getRoleByType(RoleType.ROLE_USER)));
        User user3 = new User();
        user3.setAuthorities(Arrays.asList(userDao.getRoleByType(RoleType.ROLE_USER)));
        getGenericDao().create(user1);
        getGenericDao().create(user2);
        getGenericDao().create(user3);
        List<User> users = userDao.getUsersByType(RoleType.ROLE_ADMIN);
        assertEquals(1, users.size());
        for(final User u : users) {
            for(final GrantedAuthority r : u.getAuthorities()){
                assertEquals("ROLE_ADMIN", r.getAuthority());
            }
        }
    }

    @Test
    @Transactional
    public void testDeletingUser(){
        User user1 = new User();
        Date removal = new Date();
        User user2 = new User();
        user2.setRemovalDate(removal);
        User user3 = new User();
        user3.setRemovalDate(removal);
        getGenericDao().create(user1);
        getGenericDao().create(user2);
        getGenericDao().create(user3);
        userDao.deletingUser();
        assertEquals(2, getGenericDao().findAll().size());
    }
}
