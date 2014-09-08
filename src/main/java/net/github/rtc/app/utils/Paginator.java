package net.github.rtc.app.utils;

import net.github.rtc.app.utils.datatable.Page;
import net.github.rtc.app.utils.datatable.FilterSettings;
import net.github.rtc.app.utils.datatable.SearchCriteria;
import org.springframework.stereotype.Component;

/**
 * @author Vladislav Pikus
 */
@Component
public class Paginator {
    private int maxPerPage = 5;
    private  int currentPage = 1;

    private Object filterTemplate;
    private FilterSettings settings;

    public void setSettings(FilterSettings settings) {this.settings = settings;}
    public void setFilterTemplate(Object filterTemplate) {this.filterTemplate = filterTemplate;}

    public SearchCriteria getSearchCriteria(){
        try {
                SearchCriteria searchCriteria = settings.buildSearchCriteria(filterTemplate);
                searchCriteria.setPageSize(maxPerPage);
                searchCriteria.setCurrentPage(currentPage);
            return searchCriteria;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setCurrentPage(int currentPage){
        this.currentPage = currentPage;
    }

    public void setMaxPerPage(int maxPerPage) {
        this.maxPerPage = maxPerPage;
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public Page getPage(int currentPage, int total) {
        int countPages = getCountPages(total-1);
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
