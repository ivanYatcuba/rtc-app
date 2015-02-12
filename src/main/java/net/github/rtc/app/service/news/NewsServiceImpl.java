package net.github.rtc.app.service.news;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.dao.NewsDao;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.model.entity.news.NewsStatus;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.generic.AbstractCRUDEventsService;
import net.github.rtc.app.utils.AuthorizedUserProvider;
import net.github.rtc.app.utils.NewsFromCourseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends AbstractCRUDEventsService<News> implements NewsService {
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private DateService dateService;
    @Autowired
    private NewsFromCourseBuilder newsFromCourseBuilder;

    protected GenericDao<News> getDao() {
        return newsDao;
    }

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
        create(newsFromCourseBuilder.setCourse(course).
                setAuthor(AuthorizedUserProvider.getAuthorizedUser()).
                setCreationDate(dateService.getCurrentDate()).build());
    }

    private void setPublish(News news) {
        news.setStatus(NewsStatus.PUBLISHED);
        news.setPublishDate(dateService.getCurrentDate());
    }

    private void setAuthorAndDate(News news) {
        news.setCreateDate(dateService.getCurrentDate());
        news.setAuthor(AuthorizedUserProvider.getAuthorizedUser());
    }
}
