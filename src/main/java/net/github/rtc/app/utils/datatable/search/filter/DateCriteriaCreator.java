package net.github.rtc.app.utils.datatable.search.filter;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.Date;

public class DateCriteriaCreator {

    private String fieldName;
    private Date date;

    public DateCriteriaCreator(String fieldName, Date date) {
        this.fieldName = fieldName;
        this.date = date;
    }

    public SimpleExpression getDateCriteria(char dateMoreLessEq) {
        switch (dateMoreLessEq) {
            case '>': return Restrictions.gt(fieldName, date);
            case '=': return Restrictions.eq(fieldName, date);
            case '<': return Restrictions.lt(fieldName, date);
            default: throw new IllegalArgumentException();
        }
    }
}
