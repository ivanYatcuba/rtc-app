package net.github.rtc.app.resource;

import java.util.Collection;

/**
 * Created by Ivan Yatcuba on 8/12/14.
 */
public interface GenericResource<T> {

    T create(T t);

    void delete(long id);

    T find(long id);

    T update(T t);

    Collection<T> findAll();

    void deleteByCode(String code);

    T findByCode(String code);

}
