package fr.univrouen.edeliv.dto.response.deliverytour

import fr.univrouen.edeliv.dto.response.delivery.DeliveryResponseDTO
import java.time.Instant

/**
 * Represents a delivery tour.
 */
class DeliveryTourResponseDTO(
    /**
     * The delivery tour's name (ID of the tour).
     */
    var name: String,

    /**
     * The delivery tour's start date.
     */
    var startDate: Instant,

    /**
     * The delivery tour's end date.
     */
    var endDate: Instant,

    /**
     * The deliverer associated to this delivery tour.
     */
    var deliverer: DeliveryResponseDTO,

    /**
     * The deliveries associated to this tour.
     */
    var deliveries: MutableList<DeliveryResponseDTO>
) {

    constructor(): this("", Instant.EPOCH, Instant.EPOCH, DeliveryResponseDTO(), mutableListOf())

}