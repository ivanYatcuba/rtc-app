package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.dao.NewsDao;
import net.github.rtc.app.model.news.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends AbstractGenericServiceImpl<News> {
    @Autowired
    private NewsDao newsDao;

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    protected GenericDao<News> getDao() {
        return newsDao;
    }
}
