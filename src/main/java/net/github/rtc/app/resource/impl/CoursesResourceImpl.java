package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.resource.CoursesResource;
import net.github.rtc.app.utils.datatable.PageDto;
import net.github.rtc.app.utils.datatable.SearchFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Data Access Object Implementation
 * All queries are built using {@link RestTemplate}
 *
 * @author Vladislav Pikus
 */
@Repository
public class CoursesResourceImpl extends GenericResourceImpl<Course> implements CoursesResource {
    private static Logger LOG = LoggerFactory.getLogger(CoursesResourceImpl.class.getName());

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
        return ((Long) buildCriteria(filter).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }


    /**
     * Build search criteria by filter
     *
     * @param filter filter course search
     * @return criteria
     */
    private Criteria buildCriteria(SearchFilter filter) {
        LOG.debug(filter.toString());
        Criteria criteria = getCurrentSession().createCriteria(Course.class);

        final String title = filter.getTitle();
        if (title != null && !title.equals("")) {
            criteria.add(Restrictions.like("name", "%" + title + "%"));
        }

        final String author = filter.getAuthor();
        if (author != null && !author.equals("")) {
            List<String> firstLastName = new ArrayList<>();
            for (String string : author.split(" "))firstLastName.add(string);
            criteria.createAlias("author", "author");

            if(firstLastName.size()!=1) {
                Conjunction and = Restrictions.conjunction();
                and.add(Restrictions.in("author.firstName", firstLastName));
                and.add(Restrictions.in("author.lastName", firstLastName));
                criteria.add(and);
            }else {
                Disjunction or = Restrictions.disjunction();
                or.add(Restrictions.like("author.firstName", "%"+ author+"%"));
                or.add(Restrictions.like("author.lastName", "%" + author + "%"));
                criteria.add(or);
            }
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
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
        }

        if (startDate != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.DATE, -1);
            criteria.add(Restrictions.gt("startDate", cal.getTime()));
        }

        final Collection<CourseType> categories = filter.getCategories();
        if (categories != null && categories.size() > 0) {
            criteria.add(Restrictions.in("type",categories));
            /*for ( CourseType cat : categories) {
                catDis.add(Restrictions.eq("type", cat));
            }*/
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
