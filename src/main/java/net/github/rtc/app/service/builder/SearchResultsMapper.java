package net.github.rtc.app.service.builder;

import java.util.List;

public interface SearchResultsMapper<T, E> {

    List<E> map(List<T> searchResults);
}
