package net.github.rtc.app.dao;

import net.github.rtc.app.model.news.News;

import java.util.List;

public interface NewsDao extends GenericDao<News> {
    List<News> findPublished();
}
