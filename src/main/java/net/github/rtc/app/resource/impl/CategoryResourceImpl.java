package net.github.rtc.app.resource.impl;

import net.github.rtc.app.model.Course;
import net.github.rtc.app.resource.CategoryResource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
        List<CourseType> types = Arrays.asList(CourseType.values());
        Collection<String> res = new ArrayList<>();
        for(CourseType type : types){
            res.add(type.name());
        }
        return res;
    }
}
