package net.github.rtc.app.utils.datatable;

import org.hibernate.criterion.DetachedCriteria;

public interface SearchFilter {
    DetachedCriteria getCriteria();
}
