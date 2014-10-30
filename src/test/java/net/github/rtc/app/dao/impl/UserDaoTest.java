package net.github.rtc.app.dao.impl;

import net.github.rtc.app.dao.*;
import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.model.user.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-dao-test.xml" })
public class UserDaoTest extends AbstractGenericDaoTest {
    @Autowired
    private UserDao userDao;

    @Override
    protected GenericDao<AbstractPersistenceObject> getGenericDao() {
        return (GenericDao)userDao;
    }

    @Override
    protected ModelBuilder getModelBuilder() {
        return daoTestContext.getModelBuilder(User.class);
    }

    @Override
    protected EqualityChecker getEqualityChecker() {
        return daoTestContext.getEqualityChecker(User.class);
    }
}
