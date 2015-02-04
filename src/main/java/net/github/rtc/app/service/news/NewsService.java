package net.github.rtc.app.service.news;

import net.github.rtc.app.model.news.News;
import net.github.rtc.app.service.GenericService;

import java.util.List;

public interface NewsService extends GenericService<News> {
    List<News> findPublishedNews();
    void create(News news, boolean ifPublish);
    void update(News news, boolean ifPublish);
    void publish(String newsCode);
}
