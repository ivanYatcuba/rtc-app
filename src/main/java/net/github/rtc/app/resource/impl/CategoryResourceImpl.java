package net.github.rtc.app.resource.impl;

import net.github.rtc.app.resource.CategoryResource;
import net.github.rtc.app.resource.AbstractResource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * Data Access Object Implementation
 * All queries are built using {@link org.springframework.web.client.RestTemplate}
 *
 * @author Vladislav Pikus
 */
@Component("categoryResource")
public class CategoryResourceImpl extends AbstractResource implements CategoryResource {
    /**
     * @see CategoryResource#findAll()
     */
    @Override
    public Collection<String> findAll() {
        return Arrays.asList(restTemplate.getForObject(hostUrl + "course_type", String[].class));
    }
}
