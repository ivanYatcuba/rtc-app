package net.github.rtc.app.dao;


import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.util.EqualityChecker;
import net.github.rtc.app.util.ModelBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-dao-test.xml" })
public class DaoTester {

    @Autowired
    private CoursesDao coursesDao;

    @Test
    @Transactional
    public void testCourse() {
        final GenericDaoTest<Course> courseGenericDaoTest = new GenericDaoTest<>(coursesDao,
          new ModelBuilder<Course>() {
            @Override
            public Course build() {
                Course course = new Course();
                course.setName("Test Course");
                return course;
            }
        }, new EqualityChecker<Course>() {
            @Override
            public void check(final Course sample, final Course another) {
                assertEquals(sample.getName(), another.getName());
            }
        });
        courseGenericDaoTest.testCreate();
    }
}
