package net.github.rtc.app.service;

import net.github.rtc.app.model.news.News;
import net.github.rtc.app.utils.datatable.search.AbstractSearchCommand;
import net.github.rtc.app.utils.datatable.search.SearchResults;

public interface NewsService extends GenericService<News> {
    SearchResults<News> search(AbstractSearchCommand searchCommand);
}
