package net.github.rtc.app.dao.impl;

import net.github.rtc.app.dao.NewsDao;
import net.github.rtc.app.model.news.News;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl extends AbstractGenericDaoImpl<News> implements NewsDao {
}
