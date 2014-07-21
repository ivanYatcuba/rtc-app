package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.Course;
import net.github.rtc.app.model.CourseDto;
import net.github.rtc.app.resource.CoursesResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Data Access Object Implementation
 * All queries are built using {@link RestTemplate}
 *
 * @author Vladislav Pikus
 */
@Repository
public class CoursesResourceImpl implements CoursesResource {

    /**
     * @see CoursesResource#findByCode(String)
     */
    @Override
    public Course findByCode(String code) {
        return null;
    }

    /**
     * @see CoursesResource#delete(String)
     */
    @Override
    public void delete(String code) {

    }

    /**
     * @see CoursesResource#create(net.github.rtc.app.model.Course)
     */
    @Override
    public Course create(Course course) {
        return null;
    }

    /**
     * @see CoursesResource#update(net.github.rtc.app.model.Course)
     */
    @Override
    public void update(Course course) {
       }

    /**
     * @see CoursesResource#findByFilter(String)
     */
    @Override
    public CourseDto findByFilter(String query) {
        return null;
    }
}
