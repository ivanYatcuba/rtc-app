package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.course.Tag;
import org.hibernate.criterion.*;
import org.hibernate.type.StringType;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class CourseSearchFilter extends AbstractSearchCommand {

    private static final String STRING_PROCENT = "%";
    private static final String STRING_TAGS = "tags";
    private static final String STRING_START_DATE = "startDate";
    private static final String EXPERTS = "experts";
    private String name;
    private char dateMoreLessEq;
    private Set<CourseType> types;
    private Date startDate;
    private CourseStatus status;
    private List<Tag> tags;
    private String expertCode;

    public char getDateMoreLessEq() {

        return dateMoreLessEq;
    }

    public void setDateMoreLessEq(char dateMoreLessEq) {
        this.dateMoreLessEq = dateMoreLessEq;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<CourseType> getTypes() {
        return types;
    }

    public void setTypes(final Set<CourseType> types) {
        this.types = types;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(final CourseStatus status) {
        this.status = status;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }

    public String getExpertCode() {
        return expertCode;
    }

    public void setExpertCode(final String expertCode) {
        this.expertCode = expertCode;
    }

    @Override
    public Order order() {
        return Order.asc(STRING_START_DATE);
    }

    public DetachedCriteria getCriteria() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);

        if (name != null && !("").equals(name)) {
            criteria.add(Restrictions.like("name", STRING_PROCENT + name + STRING_PROCENT));
        }
        if (status != null) {
            criteria.add(Restrictions.eq("status", status));
        }
        if (startDate != null) {
            switch (dateMoreLessEq) {
            case '>':
                criteria.add(Restrictions.gt(STRING_START_DATE, startDate));
                break;
            case '=':
                criteria.add(Restrictions.eq(STRING_START_DATE, startDate));
                break;
            case '<':
                criteria.add(Restrictions.lt(STRING_START_DATE, startDate));
                break;
            default:
                break;
            }
        }
        if (tags != null && tags.size() > 0) {
            criteria.createAlias(STRING_TAGS, STRING_TAGS);
            final Disjunction tagDis = Restrictions.disjunction();
            for (final Tag tag : tags) {
                tagDis.add(Restrictions.eq("tags.value", tag.getValue()));
            }
            criteria.add(tagDis);
        }
        if (types != null && types.size() > 0) {
            final Conjunction typesCon = Restrictions.conjunction();
            for (final CourseType type : types) {
                typesCon.add(Restrictions.sqlRestriction(
                  "exists (select t.course_id from CourseTypes t where t.course_id = {alias}.id and t.types = ?)",
                  type.name(), new StringType()));
            }
            criteria.add(typesCon);
        }
        if (expertCode != null && !("").equals(expertCode)) {
            criteria.createAlias(EXPERTS, EXPERTS);
            final Conjunction experts = Restrictions.conjunction();
            experts.add(Restrictions.eq("experts.code", expertCode));
            criteria.add(experts);
        }
        return criteria;
    }
}
