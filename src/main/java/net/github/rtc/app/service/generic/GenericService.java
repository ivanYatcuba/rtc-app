package net.github.rtc.app.service.generic;

import net.github.rtc.app.model.dto.filter.AbstractSearchCommand;
import net.github.rtc.app.model.dto.SearchResults;

import java.util.List;

public interface GenericService<T> {

    void deleteByCode(String code);

    T findByCode(String code);

    T create(T type);

    T update(T type);

    List<T> findAll();

    SearchResults<T> search(AbstractSearchCommand searchCommand);

}
