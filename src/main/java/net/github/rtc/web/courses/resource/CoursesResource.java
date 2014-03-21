package net.github.rtc.web.courses.resource;

import net.github.rtc.web.courses.model.Courses;

import java.util.Collection;

/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link Courses} objects
 *
 * @author Vladislav Pikus
 */
public interface CoursesResource {

    Courses findByCode(String code);

    /**
     * Find collection of courses
     *
     * @return collection of courses
     */
    Collection<Courses> findAll();

    void delete(String code);

    Courses create(Courses course);

    void update(Courses course);
}
