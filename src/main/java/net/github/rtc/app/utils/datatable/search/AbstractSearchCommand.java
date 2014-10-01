package net.github.rtc.app.utils.datatable.search;

import org.hibernate.criterion.DetachedCriteria;

public abstract class AbstractSearchCommand implements SearchCommand {
    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    abstract public DetachedCriteria getCriteria();
}
