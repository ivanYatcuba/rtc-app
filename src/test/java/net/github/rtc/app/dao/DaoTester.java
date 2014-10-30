package net.github.rtc.app.dao;


import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.service.CodeGenerationService;
import net.github.rtc.app.service.impl.CodeGenerationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-dao-test.xml" })
public class DaoTester {

    @Autowired
    private CoursesDao coursesDao;

    final CodeGenerationService codeGenerationService = new CodeGenerationServiceImpl();

    @Test
    @Transactional
    public void testCourseDao() throws Exception {


        final GenericDaoTest courseGenericDaoTest = new GenericDaoTest((GenericDao)coursesDao,
          new ModelBuilder() {
            @Override
            public Course build() {
                final Course course = new Course();
                course.setName("Test Course");
                course.setCapacity(1);
                course.setTypes(new HashSet<>(Arrays.asList(CourseType.BA, CourseType.QA)));
                course.setEndDate(new Date());
                course.setStartDate(new Date());
                course.setCode(codeGenerationService.generate());
                return course;
            }
        }, new EqualityChecker() {
            @Override
            public void check(final AbstractPersistenceObject sample, final AbstractPersistenceObject another) {
                final Course courseSample = (Course)sample;
                final Course courseAnother = (Course)another;
                assertEquals(courseSample.getName(), courseAnother.getName());
                assertEquals(courseSample.getCapacity(), courseAnother.getCapacity());
                assertEquals(courseSample.getId(), courseAnother.getId());
                assertEquals(courseSample.getEndDate(), courseAnother.getEndDate());
                assertEquals(courseSample.getStartDate(), courseAnother.getStartDate());
                assertEquals(courseSample.getCode(), courseAnother.getCode());
                assertEquals(courseSample.getTypes(), courseAnother.getTypes());
            }
        });
        runTestMethods(courseGenericDaoTest);
    }

    private void runTestMethods(final GenericDaoTest test){
        test.testCreate();
        test.testFindById();
        test.testFindByCode();
        test.testDeleteById();
        test.testDeleteByCode();
        test.testFindAll();
        test.testUpdate();
        test.testSearch();
    }
}
