package net.github.rtc.web.courses.resource.impl;

import net.github.rtc.web.courses.model.Courses;
import net.github.rtc.web.courses.resource.CoursesResource;
import net.github.rtc.web.courses.utils.QueryParametersBuilder;
import net.github.rtc.web.resource.AbstractResource;
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

    /**
     * @see CoursesResource#findByCode(String)
     */
    @Override
    public Courses findByCode(String code) {
        return restTemplate.getForObject(hostUrl + "courses/{code}", Courses.class, code);
    }

    /**
     * @see CoursesResource#findAll()
     */
    @Override
    public Collection<Courses> findAll() {
        return Arrays.asList(restTemplate.getForObject(hostUrl + "courses", Courses[].class));
    }

    /**
     * @see CoursesResource#delete(String)
     */
    @Override
    public void delete(String code) {
        restTemplate.delete(hostUrl + "courses/{code}", code);
    }

    /**
     * @see CoursesResource#create(Courses)
     */
    @Override
    public Courses create(Courses course) {
        return restTemplate.postForObject(hostUrl + "courses", course, Courses.class);
    }

    /**
     * @see CoursesResource#update(Courses)
     */
    @Override
    public void update(Courses course) {
        restTemplate.put(hostUrl + "courses/{code}", course, course.getCode());
    }

    /**
     * @see CoursesResource#findByFilter(Map)
     */
    @Override
    public Collection<Courses> findByFilter(Map<String, String> filter) {
        URI targetUrl = UriComponentsBuilder.fromUriString(hostUrl)
                .path("courses/filter")
                .query(QueryParametersBuilder.fromMap(filter).build())
                .build()
                .toUri();
        return Arrays.asList(restTemplate.getForObject(targetUrl, Courses[].class));
    }

    @Override
    public int getCount() {
        return restTemplate.getForObject(hostUrl + "courses/count", Integer.class);
    }
}
