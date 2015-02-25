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
import net.github.rtc.app.utils.NewsBuilder;
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
    public List<News> findPublishedNews() {
        return newsDao.findPublished();
    }

    @Override
    public void create(News news, boolean isPublished) {
        setAuthorAndDate(news);
        if (isPublished) {
            setPublish(news);
        }
        create(news);
    }

    @Override
    public void update(News news, boolean isPublished) {
        if (isPublished) {
            setPublish(news);
        }
        update(news);
    }

    @Override
    public void publish(String newsCode) {
        final News news = findByCode(newsCode);
        setPublish(news);
        update(news);
    }

    @Override
    public void createNewsFromCourse(Course course) {
        create(newsBuilder.build(course));
    }

    /**
     * Set news status as PUBLISHED and set publish date as current
     * @param news news that will be updated
     */
    private void setPublish(News news) {
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
    private void setAuthorAndDate(News news) {
        news.setCreateDate(dateService.getCurrentDate());
        news.setAuthor(AuthorizedUserProvider.getAuthorizedUser());
    }
}
