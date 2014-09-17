package net.github.rtc.app.dao.impl;

import net.github.rtc.app.dao.CoursesDao;
import net.github.rtc.app.model.course.Course;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Data Access Object Implementation
 * All queries are built using {@link RestTemplate}
 *
 * @author Vladislav Pikus
 */
@Repository
public class CoursesDaoImpl extends GenericDaoImpl<Course> implements
  CoursesDao {

}
