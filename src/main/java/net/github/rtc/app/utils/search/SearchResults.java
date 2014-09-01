package net.github.rtc.app.utils.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Yatcuba on 8/31/14.
 */
public class SearchResults<T> {
    private int totalResults = 0;
    private int pageSize = 0;
    private List<T> results = null;

    public int getPageSize() {return pageSize;}
    public void setPageSize(int pageSize) {this.pageSize = pageSize;}

    public int getTotalResults() {return totalResults;}
    public void setTotalResults(int totalResults) {this.totalResults = totalResults;}

    public List<T> getResults() {return results;}


    public int getNumberOfPages() {
        if (this.results == null || this.results.size() == 0) {
            return 0;
        }
        return (this.totalResults / this.pageSize) + (this.totalResults % this.pageSize > 0 ? 1 : 0);
    }

    public void setResults(List<T> aRresults) {
        if (aRresults == null) {
            aRresults = new ArrayList<>();
        }
        this.results = aRresults;
    }

}