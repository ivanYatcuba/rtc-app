package net.github.rtc.app.service.impl.news;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.dao.NewsDao;
import net.github.rtc.app.model.activity.ActivityEntity;
import net.github.rtc.app.model.activity.events.DeleteEntityEvent;
import net.github.rtc.app.model.activity.events.NewEntityEvent;
import net.github.rtc.app.model.activity.events.UpdateEntityEvent;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.service.impl.AbstractGenericServiceImpl;
import net.github.rtc.app.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends AbstractGenericServiceImpl<News> implements NewsService, ApplicationEventPublisherAware {
    @Autowired
    private NewsDao newsDao;

    private ApplicationEventPublisher publisher;

    @Override
    public News create(News news) {
        NewEntityEvent entityEvent = new NewEntityEvent(this, news.getLogDetail(), ActivityEntity.NEWS);
        publisher.publishEvent(entityEvent);
        return super.create(news);
    }

    @Override
    public News update(News news) {
        UpdateEntityEvent entityEvent = new UpdateEntityEvent(this, news.getLogDetail(), ActivityEntity.NEWS);
        publisher.publishEvent(entityEvent);
        return super.update(news);
    }

    @Override
    public void deleteByCode(String code) {
        News news = findByCode(code);
        DeleteEntityEvent entityEvent = new DeleteEntityEvent(this, news.getLogDetail(), ActivityEntity.NEWS);
        publisher.publishEvent(entityEvent);
        super.deleteByCode(code);
    }

    protected GenericDao<News> getDao() {
        return newsDao;
    }

    public List<News> findPublishedNews() {
        return newsDao.findPublished();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
