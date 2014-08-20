package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.resource.CoursesResource;
import net.github.rtc.app.utils.datatable.PageDto;
import net.github.rtc.app.utils.datatable.SearchFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

/**
 * Data Access Object Implementation
 * All queries are built using {@link RestTemplate}
 *
 * @author Vladislav Pikus
 */
@Repository
public class CoursesResourceImpl extends GenericResourceImpl<Course> implements CoursesResource {

    @Override
    public Integer getCount() {
        return ((Long) getCurrentSession().createCriteria(Course.class).setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }


    @Override
    public Collection<Course> findByCriteria(SearchFilter filter, PageDto pageDto) {
        return buildCriteria(filter).addOrder(Order.asc("id")).setFirstResult(pageDto.getFirstResult())
                .setMaxResults(pageDto.getMaxResult()).list();
    }


    @Override
    public Integer getCount(SearchFilter filter) {
        return ((Long)buildCriteria(filter).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }


    /**
     * Build search criteria by filter
     * @param filter filter course search
     * @return criteria
     */
    private Criteria buildCriteria(SearchFilter filter) {
        Criteria criteria = getCurrentSession().createCriteria(Course.class);

        final String title = filter.getTitle();
        if (title != null && !title.equals("")) {
            criteria.add(Restrictions.like("name", "%" + title + "%"));
        }

        final String author = filter.getAuthor();
        if (author != null && !author.equals("")) {
            criteria.createAlias("author", "author");
            criteria.add(Restrictions.disjunction()
                    .add(Restrictions.like("author.firstName", "%" + author + "%"))
                    .add(Restrictions.like("author.lastName", "%" + author + "%")));
        }

        final CourseStatus status = filter.getStatus();
        if (status != null && !status.equals("")) {
            final Disjunction stat = Restrictions.disjunction();
            stat.add(Restrictions.eq("status", status));
            criteria.add(stat);
        }

        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(filter.getStartDate());
        } catch (ParseException | NullPointerException e ) {
            e.printStackTrace();
        }

        if (startDate != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.DATE, -1);
            criteria.add(Restrictions.gt("startDate", cal.getTime()));
        }

        final Collection<String> categories = filter.getCategories();
        if (categories != null && categories.size() > 0) {
            final Disjunction catDis = Restrictions.disjunction();
            for (final String cat : categories) {
                catDis.add(Restrictions.eq("type", CourseType.valueOf(cat.toUpperCase())));
            }
            criteria.add(catDis);
        }

        final Collection<String> tags = filter.getTags();
        if (tags != null && tags.size() > 0) {
            criteria.createAlias("tags", "tags");
            final Disjunction tagDis = Restrictions.disjunction();
            for (final String tag : tags) {
                tagDis.add(Restrictions.eq("tags.value", tag));
            }
            criteria.add(tagDis);
        }

        return criteria;
    }



}
