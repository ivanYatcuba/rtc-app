package net.github.rtc.app.dao;

import net.github.rtc.app.utils.datatable.search.AbstractSearchCommand;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

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

    List<T> findAll(DetachedCriteria criteria);

    SearchResults<T> search(DetachedCriteria criteria, int start, int max, Order order);

    SearchResults<T> search(AbstractSearchCommand searchCommand);

}
