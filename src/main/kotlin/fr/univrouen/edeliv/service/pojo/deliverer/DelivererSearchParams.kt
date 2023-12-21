package fr.univrouen.edeliv.service.pojo.deliverer

import java.time.Instant

/**
 * Search parameters that can be passed to the {#link DelivererService@getAllDeliverers} method.
 */
class DelivererSearchParams(
    /**
     * The page of the search (start at 0).
     */
    val page: Int?,

    /**
     * The number element per page (default : 10).
     */
    val pageSize: Int?,

    /**
     * The minimum creation date of the deliverer to retrieve (default : Instant.EPOCH).
     */
    val minDate: Instant?,

    /**
     * The maximum creation date of the deliverer to retrieve (default: now).
     */
    val maxDate: Instant?,

    /**
     * Should the deliverer only be available ? (default : false)
     */
    val isDelivererAvailable: Boolean?,

    /**
     * The deliverer name sort order (0 -> Descending, 1 -> Ascending, null -> None).
     */
    val nameSort: Byte?,

    /**
     * The deliverer creation date sort order (0 -> Descending, 1 -> Ascending, null -> None).
     */
    val creationDateSort: Byte?,

    /**
     * The deliverer's name filter (contains filter).
     */
    val nameFilter: String?,
)