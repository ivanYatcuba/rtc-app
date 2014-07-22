package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.*;
import net.github.rtc.app.resource.CoursesResource;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Data Access Object Implementation
 * All queries are built using {@link RestTemplate}
 *
 * @author Vladislav Pikus
 */
@Repository
public class CoursesResourceImpl implements CoursesResource {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Course findByCode(String code) {

        Course course = null;
        try{
            String query = "select course from Course course where course.code = :code";
            course = (Course)sessionFactory.getCurrentSession().
                    createQuery(query).setString("code", code).uniqueResult();

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return course;
    }

    /**
     * @see CoursesResource#delete(String)
     */
    @Override
    public void delete(String code) {
        String query = "select course from Course course where course.code = :code";
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().
                createQuery(query).setString("code", code).uniqueResult());
    }

    /**
     * @see CoursesResource#create(net.github.rtc.app.model.Course)
     */
    @Override
    public Course create(Course course) {
        course.setCode(UUID.randomUUID().toString());
        sessionFactory.getCurrentSession().save(course);
        return course;
    }

    /**
     * @see CoursesResource#update(net.github.rtc.app.model.Course)
     */
    @Override
    public void update(Course course) {
        sessionFactory.getCurrentSession().update(course);
       }



    @Override
    public Integer getCount() {
        return ((Long) sessionFactory.getCurrentSession().createCriteria(Course.class).setProjection(Projections.rowCount())
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
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Course.class);
        criteria.setFetchMode("tags", FetchMode.SELECT);
        criteria.setFetchMode("author", FetchMode.SELECT);
        final String title = filter.getTitle();
        if (title != null && !title.equals("")) {
            criteria.add(Restrictions.like("name", "%" + title + "%"));
        }
        final String status = filter.getStatus();
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
            criteria.add(Restrictions.gt("startDate", startDate));
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
