package net.github.rtc.app.dao;


import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;


@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
public class GenericDaoTest {

    private GenericDao<AbstractPersistenceObject> genericDao;
    private ModelBuilder modelBuilder;
    private EqualityChecker equalityChecker;

    public GenericDaoTest(
        final GenericDao<AbstractPersistenceObject> genericDao, final ModelBuilder modelBuilder, final EqualityChecker equalityChecker) {
        this.genericDao = genericDao;
        this.modelBuilder = modelBuilder;
        this.equalityChecker = equalityChecker;
    }


    public void testCreate() {
        final AbstractPersistenceObject testEntity = modelBuilder.build();
        final List<AbstractPersistenceObject> initList = genericDao.findAll();
        genericDao.create(testEntity);
        assertTrue(initList.size() < genericDao.findAll().size());
    }

    public void testDeleteById(){
        AbstractPersistenceObject testEntity = modelBuilder.build();
        testEntity = genericDao.create(testEntity);
        genericDao.delete(testEntity.getId());
        assertNull(genericDao.find(testEntity.getId()));
    }

    public void testDeleteByCode(){
        AbstractPersistenceObject testEntity = modelBuilder.build();
        testEntity = genericDao.create(testEntity);
        genericDao.deleteByCode(testEntity.getCode());
        assertNull(genericDao.findByCode(testEntity.getCode()));
    }

    public void testFindById() {
        AbstractPersistenceObject testEntity = modelBuilder.build();
        testEntity= genericDao.create(testEntity);
        equalityChecker.check(testEntity, genericDao.find(testEntity.getId()));
    }

    public void testFindByCode() {
        AbstractPersistenceObject testEntity = modelBuilder.build();
        testEntity= genericDao.create(testEntity);
        equalityChecker.check(testEntity, genericDao.findByCode(testEntity.getCode()));
    }

    public void testUpdate(){
        AbstractPersistenceObject  testEntity = modelBuilder.build();
        genericDao.create(testEntity);
        testEntity.setCode("X");
        genericDao.update(testEntity);
        assertEquals("X", genericDao.find(testEntity.getId()).getCode());
    }

    public void testFindAll(){
        List<AbstractPersistenceObject> initList = genericDao.findAll();
        AbstractPersistenceObject  testEntity = modelBuilder.build();
        genericDao.create(testEntity);
        assertTrue(initList.size() < genericDao.findAll().size());
    }

    //ToDo: implement this for every persistence object
    public void testSearch(){
        for(int i=0; i<5; i++) {
            genericDao.create(modelBuilder.build());
        }
        CourseSearchFilter courseSearchFilter = new CourseSearchFilter();
        courseSearchFilter.setName("Test Course");
        courseSearchFilter.setTypes(new HashSet<>(Arrays.asList(CourseType.BA, CourseType.QA)));
        courseSearchFilter.setPage(0);
        List<AbstractPersistenceObject> courses = genericDao.search(courseSearchFilter).getResults();
        assertEquals(5, courses.size());
        for(AbstractPersistenceObject course: courses) {
            Course course1 = (Course)course;
            assertEquals(course1.getName(), "Test Course");
            assertEquals(course1.getTypes(), new HashSet<>(Arrays.asList(CourseType.BA, CourseType.QA)));
        }
    }
}
