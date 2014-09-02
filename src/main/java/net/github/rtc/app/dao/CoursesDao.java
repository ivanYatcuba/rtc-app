package net.github.rtc.app.dao;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.utils.datatable.PageDto;
import net.github.rtc.app.utils.datatable.SearchFilter;

import java.util.Collection;

/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link net.github.rtc.app.model.course.Course} objects
 *
 * @author Vladislav Pikus
 */
public interface CoursesDao extends GenericDao<Course> {
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
}
