package net.github.rtc.app.utils;

import net.github.rtc.app.utils.datatable.Page;
import net.github.rtc.app.utils.datatable.SearchFilter;
import org.springframework.stereotype.Component;

/**
 * @author Vladislav Pikus
 */
@Component
public class Paginator {
    private static final int DEFAULT_MAX_PER_PAGE = 5;
    private static final int MAX_PAGES_NUM = 10;

    private int maxPerPage = DEFAULT_MAX_PER_PAGE;
    private int currentPage = 1;
    private SearchFilter searchFilter;

    public int getCurrentPage() {
        return currentPage;
    }

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(final SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
    }

    public void setMaxPerPage(final int maxPerPage) {
        this.maxPerPage = maxPerPage;
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public Page getPage(int currentPage, final int total) {
        final int countPages = getCountPages(total - 1);
        final int newCurrentPage = checkCurrentPage(currentPage);
        return new Page(newCurrentPage, getPrevResult(newCurrentPage),
          getNextResult(newCurrentPage, countPages), countPages);
    }

    private int checkCurrentPage(final int currentPage) {
        return currentPage < 1 ? 0 : currentPage;
    }

    private int getCountPages(final int total) {
        return total / maxPerPage + ((total % MAX_PAGES_NUM == 0) ? 0 : 1);
    }

    private Integer getPrevResult(final int currentPage) {
        return currentPage < 2 ? null : currentPage - 1;
    }

    private Integer getNextResult(final int currentPage, final int countPages) {
        return currentPage < countPages ? currentPage + 1 : null;
    }
}
