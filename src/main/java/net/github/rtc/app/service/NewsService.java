package net.github.rtc.app.service;

import net.github.rtc.app.model.news.News;
import net.github.rtc.app.utils.datatable.search.AbstractSearchCommand;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.hibernate.criterion.DetachedCriteria;

public interface NewsService extends GenericService<News> {

    SearchResults<News> search(DetachedCriteria criteria, int start, int max);

    SearchResults<News> search(AbstractSearchCommand searchCommand);

}
