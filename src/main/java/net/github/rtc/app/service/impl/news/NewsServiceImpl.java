package net.github.rtc.app.service.impl.news;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.dao.NewsDao;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.impl.genericservise.AbstractCRUDEventsService;
import net.github.rtc.app.service.news.NewsService;
import net.github.rtc.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends AbstractCRUDEventsService<News> implements NewsService {
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private UserService userService;
    @Autowired
    private DateService dateService;

    protected GenericDao<News> getDao() {
        return newsDao;
    }

    public List<News> findPublishedNews() {
        return newsDao.findPublished();
    }

    @Override
    public void saveNews(News news, boolean ifPublish, boolean doUpdate) {
        news.setCreateDate(dateService.getCurrentDate());
        news.setAuthor(userService.getAuthorizedUser());
        if (ifPublish) {
            news.setStatus(NewsStatus.PUBLISHED);
            news.setPublishDate(dateService.getCurrentDate());
        }
        if (doUpdate) {
            update(news);
        } else {
            create(news);
        }
    }

    @Override
    public void publish(String newsCode) {
        final News news = findByCode(newsCode);
        news.setStatus(NewsStatus.PUBLISHED);
        news.setPublishDate(dateService.getCurrentDate());
        update(news);
    }
}
