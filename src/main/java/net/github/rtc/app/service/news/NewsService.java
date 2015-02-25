package net.github.rtc.app.service.news;

import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.model.entity.news.NewsStatus;
import net.github.rtc.app.service.generic.GenericService;

import java.util.List;

/**
 * The service class that is responsible for operation on News model class
 * @see net.github.rtc.app.model.entity.news.News
 */
public interface NewsService extends GenericService<News> {
    /**
     * Find all news with status PUBLISHED
     * @return list of published news
     */
    List<News> findPublishedNews();

    /**
     * Create news and publish it if necessary
     * @param news the news that will be persisted to db
     * @param isPublished flag that determines if news needs to be published
     */
    void create(News news, boolean isPublished);

    /**
     * Update news and publish it if necessary
     * @param news the news that will be updated
     * @param isPublished flag that determines if news needs to be published
     */
    void update(News news, boolean isPublished);

    /**
     * Publish news and update it
     * @param newsCode code of the news that will be published
     */
    void publish(String newsCode);

    /**
     * Create news about the course
     * @param course the course about what news will be created
     */
    void create(Course course);

    /**
     * Find all news by news status
     * @param status a current status of news
     */
    List<News> findAllByStatus(NewsStatus status);
}
