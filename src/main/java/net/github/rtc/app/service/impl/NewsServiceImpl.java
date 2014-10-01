package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.dao.NewsDao;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.service.NewsService;
import net.github.rtc.app.utils.datatable.search.AbstractSearchCommand;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsServiceImpl extends AbstractGenericServiceImpl<News> implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Transactional
    public SearchResults<News> search(
            final DetachedCriteria criteria, final int start, final int max) {
        /*log.debug("Searching courses///");*/
        return newsDao.search(criteria, start, max);
    }

    @Transactional
    public SearchResults<News> search(AbstractSearchCommand searchCommand) {
        /*log.debug("Searching courses///");*/
        return newsDao.search(searchCommand);
    }

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    protected GenericDao<News> getDao() {
        return newsDao;
    }
}
