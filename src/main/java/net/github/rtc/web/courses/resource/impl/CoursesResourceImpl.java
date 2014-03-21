package net.github.rtc.web.courses.resource.impl;

import net.github.rtc.web.courses.resource.CoursesResource;
import net.github.rtc.web.courses.model.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

/**
 * Data Access Object Implementation
 * All queries are built using {@link RestTemplate}
 *
 * @author Vladislav Pikus
 */
@Component("coursesDao")
public class CoursesResourceImpl implements CoursesResource {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String hostUrl;

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    @Override
    public Courses findByCode(String code) {
        return restTemplate.getForObject(hostUrl + "courses/{code}", Courses.class, code);
    }

    /**
     * @return collection of courses
     * @see net.github.rtc.web.courses.resource.CoursesResource
     */
    @Override
    public Collection<Courses> findAll() {
        return Arrays.asList(restTemplate.getForObject(hostUrl + "courses", Courses[].class));
    }

    @Override
    public void delete(String code) {
        restTemplate.delete(hostUrl + "courses/{code}", code);
    }

    @Override
    public Courses create(Courses course) {
        return restTemplate.postForObject(hostUrl + "courses", course, Courses.class);
    }

    @Override
    public void update(Courses course) {
        restTemplate.put(hostUrl + "courses/{code}", course, course.getCode());
    }
}
