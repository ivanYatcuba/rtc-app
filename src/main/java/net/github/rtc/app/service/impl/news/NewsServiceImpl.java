package net.github.rtc.app.service.impl.news;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.dao.NewsDao;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.service.impl.AbstractGenericServiceImpl;
import net.github.rtc.app.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends AbstractGenericServiceImpl<News> implements NewsService {
    @Autowired
    private NewsDao newsDao;

    protected GenericDao<News> getDao() {
        return newsDao;
    }

    public List<News> findPublishedNews() {
        return newsDao.findPublished();
    }

    @Override
    public void deleteByCode(String code) {
        super.deleteByCode(code);
    }

    @Override
    public News create(News t) { return  super.create(t); }

    @Override
    public News update(News t) {
        return super.update(t);
    }
}
