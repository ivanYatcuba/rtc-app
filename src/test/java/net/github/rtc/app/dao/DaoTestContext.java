package net.github.rtc.app.dao;

import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.CodeGenerationService;
import net.github.rtc.app.service.impl.CodeGenerationServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.*;

import static junit.framework.Assert.assertEquals;

@Component
public class DaoTestContext implements InitializingBean {
    private Map<Class, ModelBuilder> modelBuilder;
    private Map<Class, EqualityChecker> equalityChecker;
    protected final CodeGenerationService codeGenerationService = new CodeGenerationServiceImpl();

    public ModelBuilder getModelBuilder(Class aClass) {
        return modelBuilder.get(aClass);
    }

    public EqualityChecker getEqualityChecker(Class aClass) {
        return equalityChecker.get(aClass);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initEqualityChecker();
        initModelBuilder();
    }

    private void initModelBuilder() {
        modelBuilder = new HashMap<>();
        modelBuilder.put(Course.class,  new ModelBuilder() {
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
        });

        modelBuilder.put(User.class,  new ModelBuilder() {
            @Override
            public User build() {
                final User user = new User();
                user.setEmail("testMail@mail.com");
                user.setPassword("1111");
                user.setCode(codeGenerationService.generate());
                return user;
            }
        });
    }

    private void initEqualityChecker() {
        equalityChecker = new HashMap<>();
        equalityChecker.put(Course.class,  new EqualityChecker() {
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
            }});
        equalityChecker.put(User.class,  new EqualityChecker() {
            @Override
            public void check(final AbstractPersistenceObject sample, final AbstractPersistenceObject another) {
                final User courseSample = (User)sample;
                final User courseAnother = (User)another;
                assertEquals(courseSample.getEmail(), courseAnother.getEmail());
                assertEquals(courseSample.getPassword(), courseAnother.getPassword());
            }});
    }
}
