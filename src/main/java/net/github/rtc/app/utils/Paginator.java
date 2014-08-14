package net.github.rtc.app.utils;

import net.github.rtc.app.utils.datatable.Page;
import org.springframework.stereotype.Component;

/**
 * @author Vladislav Pikus
 */
@Component
public class Paginator {
    private int maxPerPage = 5;

    public void setMaxPerPage(int maxPerPage) {
        this.maxPerPage = maxPerPage;
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public Page getPage(int currentPage, int total) {
        int countPages = getCountPages(total);
        currentPage = checkCurrentPage(currentPage);
        return new Page(currentPage,
                getPrevResult(currentPage),
                getNextResult(currentPage, countPages),
                countPages);
    }

    private int checkCurrentPage(int currentPage) {
        return (currentPage < 1) ? 0 : currentPage;
    }

    private int getCountPages(int total) {
        return  total / maxPerPage + ((total % 10 == 0) ? 0 : 1);
    }

    private Integer getPrevResult(int currentPage) {
        return (currentPage < 2) ? null : currentPage - 1;
    }

    private Integer getNextResult(int currentPage, int countPages) {
        return (currentPage < countPages) ? currentPage + 1 : null;
    }
}
