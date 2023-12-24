package fr.univrouen.edeliv.dto.request.deliverytour

import java.time.Instant

/**
 * Request sent to create a delivery tour.
 */
class CreateDeliveryTourRequestDTO(
    /**
     * The delivery tour's name (ID of the tour).
     */
    val name: String,

    /**
     * The delivery tour's start date.
     */
    var startDate: Instant,

    /**
     * The delivery tour's end date.
     */
    var endDate: Instant,

    /**
     * The deliverer associated to this tour.
     */
    var delivererId: Long,
)