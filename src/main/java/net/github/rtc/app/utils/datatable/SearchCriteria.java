package net.github.rtc.app.utils.datatable;

import liquibase.util.StringUtils;
import org.hibernate.criterion.*;
import org.hibernate.persister.collection.CollectionPropertyNames;
import sun.misc.Sort;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Ivan Yatcuba on 8/31/14.
 */
public class SearchCriteria {
    public enum RestrictionStrategy{EQ, GT, GE, LT, LE, NULL, NOT_NULL, IN}
    public enum JunctionStrategy {CONJUNCTION, DISJUNCTION}
    public enum SortOrder {ASC, DSC}
    private int pageSize = -1;
    private int currentPage = 1;
    private String sortField = "id";
    private SortOrder sortOrder = SortOrder.ASC;

    private DetachedCriteria criteria;

    public SearchCriteria(Class entityClass) {
        criteria = DetachedCriteria.forClass(entityClass);
    }

    public String getSortField() {return sortField; }
    public void setSortField(String sortField) {this.sortField = sortField;}

    public SortOrder getSortOrder() {return sortOrder;}
    public void setSortOrder(SortOrder sortOrder) {this.sortOrder = sortOrder;}

    public int getPageSize() {return pageSize;}
    public void setPageSize(int pageSize) {this.pageSize = pageSize;}

    public int getCurrentPage() {return currentPage;}
    public void setCurrentPage(int currentPage) {this.currentPage = currentPage;}

    public DetachedCriteria getCriteria(){
        return  this.criteria;
    }

    public void addUnitCriteria(String name, Object object, RestrictionStrategy strategy){
        addRestriction(strategy, name, object, new CriteriaAddable(criteria));
    }

    public void setOrder(String field, SortOrder order){
        sortField = field;
        sortOrder = order;
    }

    public void addElementCollection(String name, Collection objects, JunctionStrategy junctionStrategy, RestrictionStrategy strategy){
        criteria.createAlias(name, name);
        criteria.add(Restrictions.in(name + ".elements", objects));
    }

    public void addComplexCriteria(String name, Object object, List<String> restrictionFields,
                                   JunctionStrategy junctionStrategy, RestrictionStrategy strategy){
        Junction junction = null;
        if(junctionStrategy == JunctionStrategy.CONJUNCTION){junction = Restrictions.conjunction();}
        else if(junctionStrategy == JunctionStrategy.DISJUNCTION){junction = Restrictions.disjunction();}
        criteria.createAlias(name, name);
        if(object instanceof Collection){
            for(String field : restrictionFields){
                for(Object o : (Collection)object){
                    attachCriteria(field, o, name + ".", new JunctionAddable(junction), strategy);
                }
            }
        }else {
            for(String field : restrictionFields){
                attachCriteria(field, object, name + ".", new JunctionAddable(junction), strategy);
            }
        }
        criteria.add(junction);
    }

    private void attachCriteria(String field, Object val, String alias, Addable addable, RestrictionStrategy strategy){
        try {
            Field f = val.getClass().getDeclaredField(field);
            f.setAccessible(true);
            addRestriction(strategy, alias + field, f.get(val), addable);
            f.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private interface Addable{void add(Criterion criterion);}

    private class CriteriaAddable implements Addable{
        private DetachedCriteria criteria;
        public CriteriaAddable(DetachedCriteria criteria){
            this.criteria = criteria;
        }
        @Override public void add(Criterion criterion) {criteria.add(criterion);}
    }

    private class JunctionAddable implements Addable{
        private Junction junction;
        public JunctionAddable(Junction junction){this.junction = junction;}
        @Override public void add(Criterion criterion) {junction.add(criterion);}
    }

    private void addRestriction(RestrictionStrategy strategy, String restrictionName, Object restrictionValue, Addable addable){
        switch (strategy){
            case EQ:addable.add(Restrictions.eq(restrictionName, restrictionValue));break;
            case GT:addable.add(Restrictions.gt(restrictionName, restrictionValue));break;
            case GE:addable.add(Restrictions.ge(restrictionName, restrictionValue));break;
            case LT:addable.add(Restrictions.lt(restrictionName, restrictionValue));break;
            case LE:addable.add(Restrictions.le(restrictionName, restrictionValue));break;
            case NULL:addable.add(Restrictions.isNull(restrictionName));break;
            case NOT_NULL:addable.add(Restrictions.isNotNull(restrictionName));break;
            case IN:addable.add(Restrictions.in(restrictionName, (Collection)restrictionValue));break;
        }
    }
}
