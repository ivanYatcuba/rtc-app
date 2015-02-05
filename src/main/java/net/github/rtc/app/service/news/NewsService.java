package net.github.rtc.app.service.news;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.GenericService;

import java.util.List;

public interface NewsService extends GenericService<News> {
    List<News> findPublishedNews();
    void create(News news, boolean isPublished);
    void update(News news, boolean isPublished);
    void publish(String newsCode);
    void createNewsFromCourse(Course course, User author);
}
