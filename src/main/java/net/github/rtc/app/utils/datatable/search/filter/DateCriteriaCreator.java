package net.github.rtc.app.utils.datatable.search.filter;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import java.util.Date;

public class DateCriteriaCreator {

    private String fieldName;
    private Date date;

    public DateCriteriaCreator(String fieldName, Date date) {
        this.fieldName = fieldName;
        if (date != null) {
            this.date = new Date(date.getTime());
        }
    }

    public Criterion getDateCriteria(char dateMoreLessEq) {
        final DateTime today = new DateTime(date).withTimeAtStartOfDay();
        switch (dateMoreLessEq) {
            case '>': return Restrictions.gt(fieldName, today.toDate());
            case '=':
                final DateTime tomorrow = today.plusDays(1).withTimeAtStartOfDay();
                return Restrictions.between(fieldName, today.toDate(), tomorrow.toDate());
            case '<': return Restrictions.lt(fieldName, today.toDate());
            default: throw new IllegalArgumentException();
        }
    }
}
