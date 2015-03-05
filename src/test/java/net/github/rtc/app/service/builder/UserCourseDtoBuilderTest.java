package net.github.rtc.app.service.builder;

import net.github.rtc.app.dao.DaoTestContext;
import net.github.rtc.app.model.dto.user.UserCourseDto;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith(BlockJUnit4ClassRunner.class)
public class UserCourseDtoBuilderTest {

    private DaoTestContext daoTestContext = new DaoTestContext();

    @Test
    public void testBuildCourseFieldsConstructor() throws Exception {
        Course course = getNewCourse();

        UserCourseDtoBuilder builder = new UserCourseDtoBuilder(course);

        assertCourseDto(builder.build(), course);
    }

    @Test
    public void testBuildCourseFieldsMethod() {
        UserCourseDtoBuilder builder = new UserCourseDtoBuilder(getNewCourse());
        Course newCourse = getNewCourse();
        Set<User> experts = new HashSet<>();
        experts.add(new User());
        newCourse.setExperts(experts);
        newCourse.setCode("dasdas");
        newCourse.setName("safhd");

        builder.buildCourseFields(newCourse);

        assertCourseDto(builder.build(), newCourse);
    }

    @Test
    public void testBuildAcceptedOrdersCount() throws Exception {
        int count = 10;
        UserCourseDtoBuilder builder = new UserCourseDtoBuilder(getNewCourse());

        builder.buildAcceptedOrdersCount(count);

        assertTrue(builder.build().getAcceptedOrders().equals(new Integer(count)));
    }

    @Test
    public void testBuildCurrentUserAssigned() throws Exception {
        UserCourseDtoBuilder builder = new UserCourseDtoBuilder(getNewCourse());
        assertFalse(builder.build().getCurrentUserAssigned());

        builder.buildCurrentUserAssigned(true);
        assertTrue(builder.build().getCurrentUserAssigned());
    }

    @Test
    public void testBuildAll() throws Exception {
        int count = 10;

        Course newCourse = getNewCourse();
        Set experts = new HashSet<>();
        experts.add(new User());
        newCourse.setExperts(experts);
        newCourse.setCode("dasdas");
        newCourse.setName("safhd");

        UserCourseDtoBuilder builder = new UserCourseDtoBuilder(getNewCourse()).
                buildCourseFields(newCourse).
                buildCurrentUserAssigned(true).
                buildAcceptedOrdersCount(count);

        final UserCourseDto build = builder.build();

        assertCourseDto(build, newCourse);
        assertTrue(build.getAcceptedOrders().equals(new Integer(count)));
        assertTrue(build.getCurrentUserAssigned());

    }

    private Course getNewCourse() {
        return (Course) daoTestContext.getModelBuilder(Course.class).build();
    }

    private void assertCourseDto(UserCourseDto courseDto, Course course) {
        assertEquals(courseDto.getCode(), course.getCode());
        assertEquals(courseDto.getCapacity(), course.getCapacity());
        assertEquals(courseDto.getName(), course.getName());
        assertArrayEquals(courseDto.getTypes().toArray(), course.getTypes().toArray());
        assertEquals(courseDto.getStartDate(), course.getStartDate());
        assertEquals(courseDto.getEndDate(), course.getEndDate());
        assertEquals(courseDto.getStatus(), course.getStatus());
        assertEquals(courseDto.getDescription(), course.getDescription());

        if (course.getExperts() == null) {
            assertNull(courseDto.getExperts());
        } else {
            assertArrayEquals(courseDto.getExperts().toArray(), course.getExperts().toArray());
        }
    }

}