
package net.github.rtc.app.service.news;

import net.github.rtc.app.dao.DaoTestContext;
import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.dao.news.NewsDao;

import net.github.rtc.app.model.dto.filter.AbstractSearchFilter;
import net.github.rtc.app.model.dto.filter.NewsSearchFilter;
import net.github.rtc.app.model.entity.AbstractPersistenceObject;
import net.github.rtc.app.model.entity.activity.ActivityEntity;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.model.entity.news.NewsStatus;
import net.github.rtc.app.service.builder.NewsBuilder;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.generic.AbstractCrudEventsServiceTest;

import net.github.rtc.app.service.generic.GenericService;
import net.github.rtc.app.service.news.NewsServiceImpl;
import org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
public class NewsServiceImplTest extends AbstractCrudEventsServiceTest {

    @Mock
    private NewsBuilder newsBuilder;
    @Mock
    private DateService dateService;
    @Mock
    private NewsDao newsDao;
    @InjectMocks
    private NewsServiceImpl newsService;

    private DaoTestContext daoTestContext = new DaoTestContext();

    @Override
    protected GenericService<AbstractPersistenceObject> getGenericService() {
        return (GenericService)newsService;
    }

    @Override
    protected GenericDao<AbstractPersistenceObject> getGenericDao() {
        return (GenericDao)newsDao;
    }

    @Override
    protected ActivityEntity trueActivityEntity() {
        return ActivityEntity.NEWS;
    }

    @Override
    protected AbstractPersistenceObject getTestEntity() {
        final News news = new News();
        news.setTitle("title");
        news.setCreateDate(new Date());
        news.setStatus(NewsStatus.DRAFT);
        news.setDescription("Y");
        return news;
    }

    @Override
    protected AbstractSearchFilter getSearchCommand() {
        return new NewsSearchFilter();
    }

    @Test
    public void testCreate()  {
        final News testEntity = (News) getTestEntity();
        final Course course = (Course) daoTestContext.getModelBuilder(Course.class).build();

        when(newsDao.create(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity, newsService.create(testEntity));

        testEntity.setStatus(NewsStatus.DRAFT);
        assertEquals(newsService.create(testEntity, true).getStatus(), NewsStatus.PUBLISHED);

        testEntity.setStatus(NewsStatus.DRAFT);
        assertEquals(newsService.create(testEntity, false).getStatus(), NewsStatus.DRAFT);

        when(newsBuilder.build(any(Course.class))).thenReturn(testEntity);
        newsService.create(course);

        verify(newsDao, times(4)).create(any(News.class));
    }

    @Test
    public void testUpdate() {
        final News testEntity = (News) getTestEntity();
        when(newsDao.update(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity, newsService.update(testEntity));

        testEntity.setStatus(NewsStatus.DRAFT);
        assertEquals(newsService.update(testEntity, true).getStatus(), NewsStatus.PUBLISHED);

        testEntity.setStatus(NewsStatus.DRAFT);
        assertEquals(newsService.update(testEntity, false).getStatus(), NewsStatus.DRAFT);

        testEntity.setStatus(NewsStatus.PUBLISHED);
        assertEquals(newsService.update(testEntity, false).getStatus(), NewsStatus.PUBLISHED);
    }

    @Test
    public void testFindAllByStatus() throws Exception {
        List<News> newsListDraft = new ArrayList<>();
        News n = (News) getTestEntity();

        n.setCode("1");
        newsListDraft.add(n);
        n = (News) getTestEntity();
        n.setCode("2");
        newsListDraft.add(n);

        List<News> newsListPublished = new ArrayList<>();
        n = (News) getTestEntity();
        n.setCode("3");
        newsListPublished.add(n);
        n = (News) getTestEntity();
        n.setCode("4");
        newsListPublished.add(n);

        when(newsDao.findAllByStatus(NewsStatus.DRAFT)).thenReturn(newsListDraft);
        when(newsDao.findAllByStatus(NewsStatus.PUBLISHED)).thenReturn(newsListPublished);

        assertArrayEquals(newsService.findAllByStatus(NewsStatus.DRAFT).toArray(),newsListDraft.toArray());
        assertArrayEquals(newsService.findAllByStatus(NewsStatus.PUBLISHED).toArray(),newsListPublished.toArray());


    }

    @Test
    public void testPublish() throws Exception {
        final News testEntity = (News) getTestEntity();
        testEntity.setStatus(NewsStatus.DRAFT);

        when(newsDao.findByCode(CODE)).thenReturn(testEntity);
        when(newsDao.update(testEntity)).thenReturn(testEntity);

        newsService.publish(CODE);
        assertEquals(testEntity.getStatus(), NewsStatus.PUBLISHED);
    }



}

