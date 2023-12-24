package fr.univrouen.edeliv.dto.request.deliverytour

import java.time.Instant

/**
 * Request sent to update a delivery tour
 */
class UpdateDeliveryTourRequestDTO(
    /**
     * The name of the tour to update.
     */
    val name: String,

    /**
     * The new delivery tour's start date.
     */
    var newStartDate: Instant,

    /**
     * The new delivery tour's end date.
     */
    var newEndDate: Instant,

    /**
     * The deliverer associated to this tour.
     */
    var delivererId: Long,

    /**
     * The deliveries (IDs) associated to this tour.
     */
    var deliveries: List<Long>,
)