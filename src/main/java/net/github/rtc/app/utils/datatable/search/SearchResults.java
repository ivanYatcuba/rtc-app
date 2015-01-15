package net.github.rtc.app.utils.datatable.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResults<T> {

    private static final int COUNT_OF_PAGES = 6;
    private List<T> results;

    private int totalResults;
    private int page;
    private int perPage;

    public Map<String, Object> getPageModel() {
        final int countPages = totalResults / perPage + ((totalResults % perPage == 0) ? 0 : 1);
        final Map<String, Object> map = new HashMap<>();
        int begin = Math.max(1, page - COUNT_OF_PAGES / 2);
        final int end = Math.min(begin + COUNT_OF_PAGES, countPages);
        if (begin + COUNT_OF_PAGES > countPages) {
            begin = begin - (begin + COUNT_OF_PAGES - countPages);
            if (begin < 1) {
                begin = 1;
            }
        }
        map.put("currentPage", page);
        map.put("lastPage", countPages);
        map.put("beginIndex", begin);
        map.put("endIndex", end);
        return map;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public void setTotalResults(final int totalResults) {
        this.totalResults = totalResults;
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
