package net.github.rtc.app.dao.impl;

import net.github.rtc.app.dao.NewsDao;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl extends AbstractGenericDaoImpl<News> implements NewsDao {

    private static Logger log = LoggerFactory.getLogger(NewsDaoImpl.class.getName());

    @Override
    public News create(News news) {
        System.out.println(news.toString());
        log.debug(news.toString());
        return super.create(news);
    }

    @Override
    public List<News> findPublished() {
        return getCurrentSession().createCriteria(News.class).add(Restrictions.eq("status", NewsStatus.PUBLISHED)).addOrder(Order.asc("publishDate")).list();
    }
}
