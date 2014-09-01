package net.github.rtc.app.utils.search;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan Yatcuba on 8/31/14.
 */
public class SearchCriteria<T> {
    private int pageSize = -1;
    private String sortOrder = "ASC";
    private Map<String, Object> searchCriteria = new HashMap<>();

    private Class<T> entityClass;

    public SearchCriteria(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public String getSortOrder() {return sortOrder;}
    public void setSortOrder(String sortOrder) {this.sortOrder = sortOrder;}

    public int getPageSize() {return pageSize;}
    public void setPageSize(int pageSize) {this.pageSize = pageSize;}

    public Map<String, Object> getSearchCriteria() {return searchCriteria;}

    public void addCriteria(String name, Object object){
        try {
            entityClass.getDeclaredField(name);
            searchCriteria.put(name, object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
