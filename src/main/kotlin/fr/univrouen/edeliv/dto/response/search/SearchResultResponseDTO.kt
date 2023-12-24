package fr.univrouen.edeliv.dto.response.search

/**
 * A generic DTO that contains the result of research.
 */
class SearchResultResponseDTO<T>(
    /**
     * The current page of the research.
     */
    var page: Int,

    /**
     * The size of the page.
     */
    var pageSize: Int,

    /**
     * The maximum amount of elements.
     */
    var maxElements: Long,

    /**
     * The result of the search.
     */
    var elements: List<T>,
)