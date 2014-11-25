package net.github.rtc.app.dao.impl;

import net.github.rtc.app.dao.NewsDao;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl extends AbstractGenericDaoImpl<News> implements NewsDao {
    @Override
    public List<News> findPublished() {
        return getCurrentSession().createCriteria(News.class).
          add(Restrictions.eq("status", NewsStatus.PUBLISHED)).list();
    }
}
