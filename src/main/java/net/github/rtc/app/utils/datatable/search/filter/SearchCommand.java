package net.github.rtc.app.utils.datatable.search.filter;

import org.hibernate.criterion.DetachedCriteria;

public interface SearchCommand {
    DetachedCriteria getCriteria();
    int getPage();
    int getPerPage();
}
