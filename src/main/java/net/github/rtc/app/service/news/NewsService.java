package net.github.rtc.app.service.news;

import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.service.generic.GenericService;

import java.util.List;

/**
 * The service class that is responsible for operation on News model class
 * @see net.github.rtc.app.model.entity.news.News
 */
public interface NewsService extends GenericService<News> {
    /**
     * Find all news with status PUBLISHED
     * @return
     */
    List<News> findPublishedNews();

    /**
     * Create news and publish it if necessary
     * @param news
     * @param isPublished flag that determines if news needs to be published
     */
    void create(News news, boolean isPublished);

    /**
     * Update news and publish it if necessary
     * @param news
     * @param isPublished flag that determines if news needs to be published
     */
    void update(News news, boolean isPublished);

    /**
     * Publish news and update it
     * @param newsCode
     */
    void publish(String newsCode);

    /**
     * Create news about the course
     * @param course
     */
    void createNewsFromCourse(Course course);
}
