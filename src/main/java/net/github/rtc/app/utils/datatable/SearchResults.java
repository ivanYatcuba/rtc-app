package net.github.rtc.app.utils.datatable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Yatcuba on 8/31/14.
 */
public class SearchResults<T> {
    private int totalResults = 0;
    private List<T> results = null;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(final int totalResults) {
        this.totalResults = totalResults;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        if (results
          == null) {
            results = new ArrayList<>();
        }
        this.results = results;
    }

}
