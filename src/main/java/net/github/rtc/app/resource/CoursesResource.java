package net.github.rtc.app.resource;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.PageDto;
import net.github.rtc.app.model.course.SearchFilter;

import java.util.Collection;
import java.util.List;

/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link net.github.rtc.app.model.course.Course} objects
 *
 * @author Vladislav Pikus
 */
public interface CoursesResource {

    /**
     * Find a course object by code
     *
     * @param code course code
     * @return course object or null
     */
    Course findByCode(String code);

    /**
     * Delete existing course by code
     *
     * @param code course code
     */
    void delete(String code);

    /**
     * Create a new course object
     *
     * @param course new course object
     * @return course with updated fields
     */
    Course create(Course course);

    /**
     * Update a course object
     *
     * @param course course object for update
     */
    void update(Course course);

    /**
     * Find course collection by filtering param
     *
     * @param filter filter query
     * @return courseDto
     */
    public Collection<Course> findByCriteria(SearchFilter filter, PageDto pageDto);

    /**
     * Get courses count
     *
     * @return courses count
     */
    Integer getCount();

    /**
     * Find total course count by filter parameters. Such as (title, category, tags, startDate)
     * And find with pagination criteria
     *
     * @param filter filter object
     * @return total course count
     */
    Integer getCount(SearchFilter  filter);

    List<Course> findAll();
}
