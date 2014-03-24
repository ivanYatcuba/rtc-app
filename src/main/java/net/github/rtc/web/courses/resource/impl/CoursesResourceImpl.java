package net.github.rtc.web.courses.resource.impl;

import net.github.rtc.web.courses.model.Courses;
import net.github.rtc.web.courses.resource.CoursesResource;
import net.github.rtc.web.courses.utils.QueryParametersBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Data Access Object Implementation
 * All queries are built using {@link RestTemplate}
 *
 * @author Vladislav Pikus
 */
@Component("coursesDao")
public class CoursesResourceImpl extends AbstractResource implements CoursesResource {

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

    @Override
    public Collection<Courses> findByFilter(Map<String, String> filter) {
        URI targetUrl = UriComponentsBuilder.fromUriString(hostUrl)
                .path("courses/filter")
                .query(QueryParametersBuilder.fromMap(filter).build())
                .build()
                .toUri();
        return Arrays.asList(restTemplate.getForObject(targetUrl, Courses[].class));
    }
}
