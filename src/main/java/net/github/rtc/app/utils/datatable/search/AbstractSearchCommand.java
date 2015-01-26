package net.github.rtc.app.utils.datatable.search;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

public abstract class AbstractSearchCommand implements SearchCommand {
    private static final int NUMBER_OF_ENTITIES_PER_PAGE = 5;

    private int page;
    private int perPage = NUMBER_OF_ENTITIES_PER_PAGE;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public abstract Order order();

    abstract public DetachedCriteria getCriteria();
}
