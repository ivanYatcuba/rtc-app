package net.github.rtc.app.utils.datatable;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.course.Tag;
import net.github.rtc.app.model.user.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class CourseSearchFilter implements SearchFilter {

    private String name;

    private CourseType type;

    private Date startDate;

    private CourseStatus status;

    private List<Tag> tags;

    private Set<User> experts;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public CourseType getType() {return type;}
    public void setType(CourseType type) {this.type = type;}

    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    public CourseStatus getStatus() {return status;}
    public void setStatus(CourseStatus status) {this.status = status;}

    public List<Tag> getTags() {return tags;}
    public void setTags(List<Tag> tags) {this.tags = tags;}

    public Set<User> getExperts() {return experts;}
    public void setExperts(Set<User> experts) {this.experts = experts;}

    @Override
    public DetachedCriteria getCriteria() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);

        if(name != null && !name.equals("")) {
            criteria.add(Restrictions.like("name", "%" + name + "%"));
        }
        if (status != null && !status.equals("")) {
            criteria.add(Restrictions.eq("status", status));
        }
        if (startDate != null) {
            criteria.add(Restrictions.ge("startDate", startDate));
        }
        if (tags != null && tags.size() > 0) {
            criteria.createAlias("tags", "tags");
            final Disjunction tagDis = Restrictions.disjunction();
            for (final Tag tag : tags) {
                tagDis.add(Restrictions.eq("tags.value", tag.getValue()));
            }
            criteria.add(tagDis);
        }
        return criteria;
    }
}
