package net.github.rtc.app.dao;

import java.util.List;

/**
 * Created by Ivan Yatcuba on 8/12/14.
 */
public interface GenericDao<T> {

    T create(T t);

    void delete(long id);

    T find(long id);

    T update(T t);

    List<T> findAll();

    void deleteByCode(String code);

    T findByCode(String code);

}
