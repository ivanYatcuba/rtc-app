package net.github.rtc.app.service;

import net.github.rtc.app.model.news.News;

import java.util.List;

public interface NewsService extends GenericService<News> {
    List<News> findPublishedNews();
}
