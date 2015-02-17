/*
package net.github.rtc.app.service.impl;
//todo
import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.dao.news.NewsDao;
import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import net.github.rtc.app.service.AbstractGenericServiceTest;
import net.github.rtc.app.service.GenericService;
import net.github.rtc.app.utils.datatable.search.AbstractSearchCommand;
import net.github.rtc.app.utils.datatable.search.NewsSearchFilter;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-dao-test.xml" })
public class NewsServiceTest extends AbstractGenericServiceTest {

    @Mock
    private NewsDao newsDao;
    @InjectMocks
    private NewsServiceImpl newsService;


    @Override
    protected GenericService<AbstractPersistenceObject> getGenericService() {
        return (GenericService)newsService;
    }

    @Override
    protected GenericDao<AbstractPersistenceObject> getGenericDao() {
        return (GenericDao)newsDao;
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
    protected AbstractSearchCommand getSearchCommand() {
        return new NewsSearchFilter();
    }
}
*/
