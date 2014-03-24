package net.github.rtc.web.courses.resource.impl;

import net.github.rtc.web.courses.resource.CoursesResource;
import net.github.rtc.web.courses.model.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.springapp.mvc.User;

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

    /**
     * @param id course ID
     * @return null if not found or course's object if found
     * @see net.github.rtc.web.courses.resource.CoursesResource
     */
    @Override
    public Courses findById(Integer id) {
        return restTemplate.getForObject(hostUrl + "courses/{id}", Courses.class, id);
    }

    /**
     * @return collection of courses
     * @see net.github.rtc.web.courses.resource.CoursesResource
     */
    @Override
    public Collection<Courses> findAll() {
        return Arrays.asList(restTemplate.getForObject(hostUrl + "courses", Courses[].class));
    }

    /**
     * @param id course ID
     * @see net.github.rtc.web.courses.resource.CoursesResource
     */
    @Override
    public void delete(Integer id) {
        restTemplate.delete(hostUrl + "courses/{id}", id);
    }

    @Override
    public Courses create(Courses course) {
        return restTemplate.postForObject(hostUrl + "courses", course, Courses.class);
    }


    @Override
    public void update(Courses course) {
        restTemplate.put(hostUrl + "courses/{id}", course, course.getId());
    }
}
