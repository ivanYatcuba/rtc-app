package net.github.rtc.app.utils;

import net.github.rtc.app.utils.datatable.Page;
import net.github.rtc.app.utils.datatable.SearchFilter;
import org.springframework.stereotype.Component;

/**
 * @author Vladislav Pikus
 */
@Component
public class Paginator {
    private int maxPerPage = 5;
    private int currentPage = 1;
    SearchFilter searchFilter;

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
        final int countPages = getCountPages(total
          - 1);
        currentPage = checkCurrentPage(currentPage);
        return new Page(currentPage, getPrevResult(currentPage),
          getNextResult(currentPage, countPages), countPages);
    }

    private int checkCurrentPage(final int currentPage) {
        return currentPage
          < 1 ? 0 : currentPage;
    }

    private int getCountPages(final int total) {
        return total
          / maxPerPage
          + ((total
          % 10
          == 0) ? 0 : 1);
    }

    private Integer getPrevResult(final int currentPage) {
        return currentPage
          < 2 ? null : currentPage
          - 1;
    }

    private Integer getNextResult(final int currentPage, final int countPages) {
        return currentPage
          < countPages ? currentPage
          + 1 : null;
    }
}
