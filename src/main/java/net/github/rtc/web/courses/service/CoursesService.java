package net.github.rtc.web.courses.service;

import net.github.rtc.web.courses.model.Courses;

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

    /**
     * Will be delete course by ID
     * If ID is null then will be throw {@link net.github.rtc.web.courses.exception.ServiceProcessingException}
     *
     * @param id course ID
     */
    void delete(Integer id);

    Courses findById(Integer id);

    Courses create(Courses course);

    void update(Courses course);
}
