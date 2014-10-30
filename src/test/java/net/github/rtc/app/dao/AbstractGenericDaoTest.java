package net.github.rtc.app.dao;


import net.github.rtc.app.model.AbstractPersistenceObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;


@ContextConfiguration(locations = { "classpath:mvc-dao-test.xml" })
public abstract class AbstractGenericDaoTest {

    @Autowired
    protected DaoTestContext daoTestContext;

    protected abstract GenericDao<AbstractPersistenceObject> getGenericDao();
    protected abstract ModelBuilder getModelBuilder();
    protected abstract EqualityChecker getEqualityChecker();

    private AbstractPersistenceObject testEntity;
    private GenericDao<AbstractPersistenceObject> genericDao;

    @Before
    public void setUp(){
        testEntity = getModelBuilder().build();
        genericDao = getGenericDao();
    }

    @After
    public void tearDown(){
        testEntity = null;
        genericDao = null;
    }

    @Test
    @Transactional
    public void testCreate() {
        final List<AbstractPersistenceObject> initList = genericDao.findAll();
        genericDao.create(testEntity);
        assertTrue(initList.size() < genericDao.findAll().size());
    }

    @Test
    @Transactional
    public void testDeleteById(){
        testEntity = genericDao.create(testEntity);
        genericDao.delete(testEntity.getId());
        assertNull(genericDao.find(testEntity.getId()));
    }

    @Test
    @Transactional
    public void testDeleteByCode(){
        testEntity = genericDao.create(testEntity);
        genericDao.deleteByCode(testEntity.getCode());
        assertNull(genericDao.findByCode(testEntity.getCode()));
    }

    @Test
    @Transactional
    public void testFindById() {
        testEntity= genericDao.create(testEntity);
        getEqualityChecker().check(testEntity, genericDao.find(testEntity.getId()));
    }

    @Test
    @Transactional
    public void testFindByCode() {
        testEntity= genericDao.create(testEntity);
        getEqualityChecker().check(testEntity, genericDao.findByCode(testEntity.getCode()));
    }

    @Test
    @Transactional
    public void testUpdate(){
        genericDao.create(testEntity);
        testEntity.setCode("X");
        genericDao.update(testEntity);
        assertEquals("X", genericDao.find(testEntity.getId()).getCode());
    }

    @Test
    @Transactional
    public void testFindAll(){
        final List<AbstractPersistenceObject> initList = genericDao.findAll();
        final AbstractPersistenceObject  testEntity = getModelBuilder().build();
        genericDao.create(testEntity);
        assertTrue(initList.size() < genericDao.findAll().size());
    }


}
