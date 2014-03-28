package net.github.rtc.web.courses.service;

import java.util.Collection;

/**
 * Service Interface
 *
 * @author Vladislav Pikus
 */
public interface CategoryService {

    /**
     * Find collection course categories
     *
     * @return collection course categories
     */
    Collection<String> findAll();
}
