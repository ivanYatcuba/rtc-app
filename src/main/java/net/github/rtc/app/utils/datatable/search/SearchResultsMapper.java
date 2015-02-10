package net.github.rtc.app.utils.datatable.search;

import java.util.List;

public interface SearchResultsMapper<T, E> {

    List<E> map(List<T> searchResults);
}
