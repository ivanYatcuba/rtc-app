package net.github.rtc.app.service.news;

import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.service.generic.GenericService;

import java.util.List;

public interface NewsService extends GenericService<News> {
    List<News> findPublishedNews();
    void create(News news, boolean isPublished);
    void update(News news, boolean isPublished);
    void publish(String newsCode);
    void createNewsFromCourse(Course course);
}
