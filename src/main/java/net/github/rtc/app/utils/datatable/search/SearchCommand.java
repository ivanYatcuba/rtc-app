package net.github.rtc.app.utils.datatable.search;

import org.hibernate.criterion.DetachedCriteria;

public interface SearchCommand {
    DetachedCriteria getCriteria();

    int getPage();
}
