package net.github.rtc.app.service.impl;

import net.github.rtc.app.resource.CategoryResource;
import net.github.rtc.app.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger LOG = LoggerFactory.getLogger(CoursesServiceImpl.class.getName());


    /**
     * @see CategoryService#findAll()
     */
    //todo: see todos in CategoryResource
    @Override
    public Collection<String> findAll() {
        LOG.info("Getting all courses category...");
        return resource.findAll();
    }
}
