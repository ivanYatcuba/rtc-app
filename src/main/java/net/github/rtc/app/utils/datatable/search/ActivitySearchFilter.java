package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.activity.Activity;
import net.github.rtc.app.model.activity.ActivityAction;
import net.github.rtc.app.model.activity.ActivityEntity;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.course.Tag;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

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
        return date;
    }

    public String getUser() {
        return user;
    }

    public void setDateMoreLessEq(char dateMoreLessEq) {
        this.dateMoreLessEq = dateMoreLessEq;
    }

    public void setDate(final Date date) {
        this.date = date;
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
            final Conjunction entityCon = Restrictions.conjunction();
            for (final ActivityEntity en : entity) {
                entityCon.add(Restrictions.sqlRestriction(
                        "exists (select t.id from Activity t where t.id = {alias}.id and t.entity = ?)",
                        en.name(), new StringType()));
            }
            criteria.add(entityCon);
        }

        if (action != null && action.size() > 0) {
            final Conjunction actionCon = Restrictions.conjunction();
            for (final ActivityAction ac : action) {
                actionCon.add(Restrictions.sqlRestriction(
                        "exists (select t.id from Activity t where t.id = {alias}.id and t.action = ?)",
                        ac.name(), new StringType()));
            }
            criteria.add(actionCon);
        }
        return criteria;
    }
}
