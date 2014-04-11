package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.Course;
import net.github.rtc.app.model.CourseDto;
import net.github.rtc.app.resource.CoursesResource;
import net.github.rtc.app.resource.AbstractResource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Data Access Object Implementation
 * All queries are built using {@link RestTemplate}
 *
 * @author Vladislav Pikus
 */
@Component("coursesDao")
public class CoursesResourceImpl extends AbstractResource implements CoursesResource {

    /**
     * @see CoursesResource#findByCode(String)
     */
    @Override
    public Course findByCode(String code) {
        return restTemplate.getForObject(hostUrl + "courses/{code}", Course.class, code);
    }

    /**
     * @see CoursesResource#delete(String)
     */
    @Override
    public void delete(String code) {
        restTemplate.delete(hostUrl + "courses/{code}", code);
    }

    /**
     * @see CoursesResource#create(net.github.rtc.app.model.Course)
     */
    @Override
    public Course create(Course course) {
        return restTemplate.postForObject(hostUrl + "courses", course, Course.class);
    }

    /**
     * @see CoursesResource#update(net.github.rtc.app.model.Course)
     */
    @Override
    public void update(Course course) {
        restTemplate.put(hostUrl + "courses/{code}", course, course.getCode());
    }

    /**
     * @see CoursesResource#findByFilter(String)
     */
    @Override
    public CourseDto findByFilter(String query) {
        URI targetUrl = UriComponentsBuilder.fromUriString(hostUrl)
                .path("courses")
                .query(query)
                .build()
                .toUri();
        return restTemplate.getForObject(targetUrl, CourseDto.class);
    }
}
