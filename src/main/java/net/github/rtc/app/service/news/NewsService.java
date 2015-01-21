package net.github.rtc.app.service.news;

import net.github.rtc.app.model.news.News;
import net.github.rtc.app.service.GenericService;

import java.util.List;

public interface NewsService extends GenericService<News> {
    List<News> findPublishedNews();
}
