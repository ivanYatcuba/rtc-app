package net.github.rtc.web.courses.service;

import net.github.rtc.web.courses.model.Courses;

import java.util.Collection;
import java.util.Map;

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

    Collection<Courses> findByFilter(Map<String, String> filter);
}
