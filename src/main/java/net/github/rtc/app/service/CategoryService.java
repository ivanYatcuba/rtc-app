package net.github.rtc.app.service;

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
