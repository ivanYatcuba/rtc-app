package net.github.rtc.app.utils.datatable;

import javax.persistence.ElementCollection;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Ivan Yatcuba on 9/4/14.
 */
public class FilterSettings {
    private Map<String, SearchCriteria.RestrictionStrategy> primitiveOptions = new HashMap<>();
    private List<ComplexOption> complexOptions = new LinkedList<>();
    private Class type;
    private String sortField = "id";
    private SearchCriteria.SortOrder sortOrder = SearchCriteria.SortOrder.ASC;

    private class ComplexOption{
        String filterOption;
        SearchCriteria.RestrictionStrategy strategy;
        List<String> restrictionFields;
        SearchCriteria.JunctionStrategy junctionStrategy;

        private ComplexOption(String filterOption, SearchCriteria.RestrictionStrategy strategy,
                              List<String> restrictionFields, SearchCriteria.JunctionStrategy junctionStrategy) {
            this.filterOption = filterOption;
            this.strategy = strategy;
            this.restrictionFields = restrictionFields;
            this.junctionStrategy = junctionStrategy;
        }
    }

    public FilterSettings(Class type){this.type = type;}

    public void addOption(String filterOption, SearchCriteria.RestrictionStrategy strategy){
        primitiveOptions.put(filterOption, strategy);
    }

    public void setOrder(String field, SearchCriteria.SortOrder order){
        sortField = field;
        sortOrder = order;
    }

    public void addOption(String filterOption, SearchCriteria.RestrictionStrategy strategy,
                                 List<String> restrictionFields, SearchCriteria.JunctionStrategy junctionStrategy){
        complexOptions.add(new ComplexOption(filterOption, strategy, restrictionFields, junctionStrategy));
    }

    public SearchCriteria buildSearchCriteria(Object sampleObject) throws Exception {
        SearchCriteria searchCriteria = new SearchCriteria(type);
        if(sampleObject.getClass() == type){

            for(String simpleField : primitiveOptions.keySet()){
                Field f = sampleObject.getClass().getDeclaredField(simpleField);
                f.setAccessible(true);
                Object val = f.get(sampleObject);
                if(val != null){
                    searchCriteria.addUnitCriteria(simpleField, val, primitiveOptions.get(simpleField));
                }
                f.setAccessible(false);
            }

            for(ComplexOption option: complexOptions){
                Field f = sampleObject.getClass().getDeclaredField(option.filterOption);
                f.setAccessible(true);
                Object val = f.get(sampleObject);
                if(val != null){
                    if(f.isAnnotationPresent(ElementCollection.class)){
                        searchCriteria.addElementCollection(option.filterOption, (Collection)val,
                                option.junctionStrategy, option.strategy);
                    }else {
                        searchCriteria.addComplexCriteria(option.filterOption, val, option.restrictionFields,
                                option.junctionStrategy, option.strategy);
                    }
                }
                f.setAccessible(false);
            }
            searchCriteria.setOrder(sortField, sortOrder);
        }else {
            throw new Exception("Wrong argument type");
        }
        return  searchCriteria;
    }
}
