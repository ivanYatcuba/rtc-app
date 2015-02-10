package net.github.rtc.app.utils.datatable.search;

public class SearchResultsBuilder<T, E> {

    private SearchResults<T> searchResultsToTransform;
    private SearchResultsMapper<T, E> searchResultsMapper;

    public SearchResultsBuilder<T, E> setSearchResultsToTransform(SearchResults<T> searchResultsToTransform) {
        this.searchResultsToTransform = searchResultsToTransform;
        return this;
    }

    public SearchResultsBuilder<T, E> setSearchResultsMapper(SearchResultsMapper<T, E> searchResultsMapper) {
        this.searchResultsMapper = searchResultsMapper;
        return this;
    }

    public SearchResults<E> build() {
        final SearchResults<E> transformedSearchResults = new SearchResults<>();
        transformedSearchResults.setResults(searchResultsMapper.map(searchResultsToTransform.getResults()));
        transformedSearchResults.setPageModel(searchResultsToTransform.getPageModel());
        return transformedSearchResults;
    }
}
