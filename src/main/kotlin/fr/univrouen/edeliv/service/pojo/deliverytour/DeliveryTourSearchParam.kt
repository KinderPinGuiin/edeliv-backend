package fr.univrouen.edeliv.service.pojo.deliverytour

import java.time.Instant

/**
 * Search parameters that can be passed to the getAllDeliveryTours method.
 */
class DeliveryTourSearchParam(
    /**
     * The page of the search (start at 0).
     */
    val page: Int?,

    /**
     * The number of element per page (default : 10).
     */
    val pageSize: Int?,

    /**
     * If defined, only the tours that contains the given date will be returned.
     */
    val tourDate: Instant?,
)