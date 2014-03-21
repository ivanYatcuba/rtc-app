package net.github.rtc.web.courses.resource.impl;

import net.github.rtc.web.courses.resource.CategoryResource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
@Component("categoryResource")
public class CategoryResourceImpl extends AbstractResource implements CategoryResource {
    @Override
    public Collection<String> findAll() {
        return Arrays.asList(restTemplate.getForObject(hostUrl + "course_type", String[].class));
    }
}
