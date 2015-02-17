package net.github.rtc.app.dao.news;

import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.model.entity.news.News;

import java.util.List;

public interface NewsDao extends GenericDao<News> {
    List<News> findPublished();
}
