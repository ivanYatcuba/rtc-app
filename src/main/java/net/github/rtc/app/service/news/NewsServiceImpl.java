package net.github.rtc.app.service.news;

import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.dao.news.NewsDao;
import net.github.rtc.app.model.entity.activity.ActivityEntity;
import net.github.rtc.app.model.entity.activity.Auditable;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.model.entity.news.NewsStatus;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.generic.AbstractCrudEventsService;
import net.github.rtc.app.service.security.AuthorizedUserProvider;
import net.github.rtc.app.service.builder.NewsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends AbstractCrudEventsService<News> implements NewsService {
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private DateService dateService;

    @Autowired
    private NewsBuilder newsBuilder;


    protected GenericDao<News> getDao() {
        return newsDao;
    }

    @Override
    public void create(News news, boolean isPublished) {
        setAuthorAndDateAttributes(news);
        if (isPublished) {
            setPublishedAttribute(news);
        }
        create(news);
    }

    @Override
    public void update(News news, boolean isPublished) {
        if (isPublished) {
            setPublishedAttribute(news);
        }
        update(news);
    }

    @Override
    public void publish(String newsCode) {
        final News news = findByCode(newsCode);
        setPublishedAttribute(news);
        update(news);
    }

    @Override
    public void create(Course course) {
        create(newsBuilder.build(course));
    }

    @Override
    public List<News> findAllByStatus(NewsStatus status) {
        return newsDao.findAllByStatus(status);
    }

    /**
     * Set news status as PUBLISHED and set publish date as current
     * @param news news that will be updated
     */
    private void setPublishedAttribute(News news) {
        news.setStatus(NewsStatus.PUBLISHED);
        news.setPublishDate(dateService.getCurrentDate());
    }

    @Override
    protected ActivityEntity getActivityEntity(Auditable entityObj) {
        return ActivityEntity.NEWS;
    }

    /**
     * Set news creation date as current and set author as current logged user
     * @param news news that will be updated
     */
    private void setAuthorAndDateAttributes(News news) {
        news.setCreateDate(dateService.getCurrentDate());
        news.setAuthor(AuthorizedUserProvider.getAuthorizedUser());
    }
}
