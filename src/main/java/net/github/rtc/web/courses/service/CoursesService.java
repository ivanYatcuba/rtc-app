package net.github.rtc.web.courses.service;

import net.github.rtc.web.courses.model.Courses;
//import com.springapp.mvc.User;

import java.util.Collection;

/**
 * Service Interface
 * Provides operations with {@link net.github.rtc.web.courses.model.Courses} objects
 *
 * @author Vladislav Pikus
 * @author Dmitry Pritula
 */
public interface CoursesService {
    /**
     * Find collection of courses
     *
     * @return collection of courses
     */
    Collection<Courses> findAll();

    void delete(String code);

    Courses findByCode(String code);

    Courses create(Courses course);

    void update(Courses course);
}
