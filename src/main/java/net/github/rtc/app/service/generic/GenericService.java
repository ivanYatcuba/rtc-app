package net.github.rtc.app.service.generic;

import net.github.rtc.app.utils.datatable.search.filter.AbstractSearchCommand;
import net.github.rtc.app.utils.datatable.search.SearchResults;

import java.util.List;

public interface GenericService<T> {

    void deleteByCode(String code);

    T findByCode(String code);

    T create(T type);

    T update(T type);

    List<T> findAll();

    SearchResults<T> search(AbstractSearchCommand searchCommand);

}