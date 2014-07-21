package net.github.rtc.app.resource.impl;

import net.github.rtc.app.resource.CategoryResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Data Access Object Implementation
 * All queries are built using {@link org.springframework.web.client.RestTemplate}
 *
 * @author Vladislav Pikus
 */
@Repository
public class CategoryResourceImpl implements CategoryResource {
    /**
     * @see CategoryResource#findAll()
     */
    @Override
    public Collection<String> findAll() {
        return null;
    }
}
