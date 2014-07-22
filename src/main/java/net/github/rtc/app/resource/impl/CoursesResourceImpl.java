package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.Course;
import net.github.rtc.app.model.CourseDto;
import net.github.rtc.app.resource.CoursesResource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

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

    /**
     * @see CoursesResource#findByFilter(String)
     */
    @Override
    public CourseDto findByFilter(String query) {
        return null;
    }
}
