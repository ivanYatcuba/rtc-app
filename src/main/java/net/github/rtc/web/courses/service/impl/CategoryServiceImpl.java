package net.github.rtc.web.courses.service.impl;

import net.github.rtc.web.courses.resource.CategoryResource;
import net.github.rtc.web.courses.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    private CategoryResource resource;

    @Autowired
    public void setResource(CategoryResource resource) {
        this.resource = resource;
    }

    @Override
    public Collection<String> findAll() {
        return resource.findAll();
    }
}
