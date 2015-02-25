package net.github.rtc.app.model.dto.filter;

import org.hibernate.criterion.DetachedCriteria;

public interface SearchFilter {
    DetachedCriteria getCriteria();
    int getPage();
    int getPerPage();
}
