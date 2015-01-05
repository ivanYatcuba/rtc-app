package net.github.rtc.app.utils.datatable.search;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Илья on 29.12.2014.
 */

@Component
public class ActivitySearchFilter extends AbstractSearchCommand {

    //private static final String STRING_PROCENT = "%";
    private String object;
    private String entity;
    private String action;
    private Date date;
    private char dateMoreLessEq;

    public char getDateMoreLessEq() {
        return dateMoreLessEq;
    }

    public Date getDate() {
        return date;
    }

    public String getAction() {
        return action;
    }

    public String getEntity() {
        return entity;
    }

    public String getObject() {
        return object;
    }

    public void setDateMoreLessEq(char dateMoreLessEq) {
        this.dateMoreLessEq = dateMoreLessEq;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public void setEntity(final String entity) {
        this.entity = entity;
    }

    public void setObject(final String object) {
        this.object = object;
    }

    @Override
    public DetachedCriteria getCriteria() {

//        if (object != null && ("").equals(object)) {
//        }

        return null;
    }
}
