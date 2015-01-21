package net.github.rtc.app.utils.datatable.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResults<T> {

    private static final int PAGE_OFFSET = 1;
    private static final int MAX_OFFSET = 3;
    private List<T> results;

    private int totalResults;
    private int page;
    private int perPage;

    public Map<String, Object> getPageModel() {
        final int countPages = totalResults / perPage + ((totalResults % perPage == 0) ? 0 : 1);
        final Map<String, Object> map = new HashMap<>();
        final int begin = Math.max(1, page - PAGE_OFFSET);
        int end;
        if (countPages == MAX_OFFSET) {
            end = MAX_OFFSET;
        } else {
            end = Math.min(begin + PAGE_OFFSET, countPages);
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
