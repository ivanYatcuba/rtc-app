package net.github.rtc.web.courses.service.impl;

import net.github.rtc.web.courses.resource.CategoryResource;
import net.github.rtc.web.courses.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Service Implementation
 * This's a wrapper for {@link CategoryResource}
 *
 * @author Vladislav Pikus
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    private CategoryResource resource;

    @Autowired
    public void setResource(CategoryResource resource) {
        this.resource = resource;
    }

    /**
     * @see CategoryService#findAll()
     */
    @Override
    public Collection<String> findAll() {
        return resource.findAll();
    }
}
