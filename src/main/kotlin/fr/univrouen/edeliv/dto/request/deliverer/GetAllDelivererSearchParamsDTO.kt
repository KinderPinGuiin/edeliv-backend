package fr.univrouen.edeliv.dto.request.deliverer

import java.time.Instant

/**
 * Request params that will be sent to sort or filter the deliverers.
 */
class GetAllDeliverersSearchParamsDTO(
    /**
     * The page of the search.
     */
    val page: Int,

    /**
     * The number element per page.
     */
    val pageSize: Int,

    /**
     * The minimum creation date of the deliverer to retrieve.
     */
    val minDate: Instant?,

    /**
     * The maximum creation date of the deliverer to retrieve.
     */
    val maxDate: Instant?,

    /**
     * Should the deliverer be available ?
     */
    val isDelivererAvailable: Boolean?,

    /**
     * The deliverer name sort order (0 -> Descending, 1 -> Ascending).
     */
    val nameSort: Byte?,

    /**
     * The deliverer creation date sort order (0 -> Descending, 1 -> Ascending).
     */
    val creationDateSort: Byte?,
)