package net.github.rtc.app.utils.datatable.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivan Yatcuba on 8/31/14.
 */
public class SearchResults<T> {
    private static final int MAX_PAGES_NUM = 10;

    private int totalResults = 0;
    private List<T> results = null;

    private int first = 1;
    private int last;


    public Map<String, Object> getPageModel(final int maxPerPage,
      final int currentPage) {
        final int countPages = getCountPages(totalResults, maxPerPage);
        final int newCurrentPage = checkCurrentPage(currentPage);
        final Map<String, Object> map = new HashMap<>();
        map.put("currentPage", newCurrentPage);
        map.put("prevPage", getPrevResult(newCurrentPage));
        map.put("nextPage", getNextResult(newCurrentPage, countPages));
        map.put("startPage", getFirst());
        map.put("lastPage", countPages);
        return  map;
    }


    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(final int totalResults) {
        this.totalResults = totalResults;
    }

    private int checkCurrentPage(final int currentPage) {
        return currentPage < 1 ? 0 : currentPage;
    }

    private int getCountPages(final int total, final int maxPerPage) {
        return total / maxPerPage + ((total % maxPerPage == 0) ? 0 : 1);
    }

    private Integer getPrevResult(final int currentPage) {
        return currentPage < 2 ? null : currentPage - 1;
    }

    private Integer getNextResult(final int currentPage, final int countPages) {
        return currentPage < countPages ? currentPage + 1 : null;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> newResults) {
        if (newResults == null) {
            this.results = new ArrayList<>();
        }
        this.results = newResults;
    }

}
