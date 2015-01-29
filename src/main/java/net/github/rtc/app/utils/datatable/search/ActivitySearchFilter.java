package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.activity.Activity;
import net.github.rtc.app.model.activity.ActivityAction;
import net.github.rtc.app.model.activity.ActivityEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

/**
 * Created by Илья on 29.12.2014.
 */

@Component
public class ActivitySearchFilter extends AbstractSearchCommand {

    private static final String STRING_DATE = "actionDate";
    private String user;
    private Set<ActivityEntity> entity;
    private Set<ActivityAction> action;
    private Date date;
    private char dateMoreLessEq;

    public char getDateMoreLessEq() {
        return dateMoreLessEq;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public String getUser() {
        return user;
    }

    public void setDateMoreLessEq(char dateMoreLessEq) {
        this.dateMoreLessEq = dateMoreLessEq;
    }

    public void setDate(final Date date) {
        this.date = new Date(date.getTime());
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public Set<ActivityEntity> getEntity() {
        return entity;
    }

    public void setEntity(Set<ActivityEntity> entity) {
        this.entity = entity;
    }

    public Set<ActivityAction> getAction() {
        return action;
    }

    public void setAction(Set<ActivityAction> action) {
        this.action = action;
    }

    @Override
    public Order order() {
        return Order.desc(STRING_DATE);
    }

    @Override
    public DetachedCriteria getCriteria() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(Activity.class);

        if (user != null && !("").equals(user)) {
            criteria.add(Restrictions.like("username", user));
        }
        if (date != null) {
            switch (dateMoreLessEq) {
                case '>':
                    criteria.add(Restrictions.gt(STRING_DATE, date));
                    break;
                case '=':
                    criteria.add(Restrictions.eq(STRING_DATE, date));
                    break;
                case '<':
                    criteria.add(Restrictions.lt(STRING_DATE, date));
                    break;
                default:
                    break;
            }
        }
        if (entity != null && entity.size() > 0) {
            final Disjunction entityDis = Restrictions.disjunction();
            for (final ActivityEntity en : entity) {
                entityDis.add(Restrictions.eq("entity", en));
            }
            criteria.add(entityDis);
        }

        if (action != null && action.size() > 0) {
            final Disjunction actionDis = Restrictions.disjunction();
            for (final ActivityAction ac : action) {
                actionDis.add(Restrictions.eq("action", ac));
            }
            criteria.add(actionDis);
        }
        return criteria;
    }
}
