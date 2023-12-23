package fr.univrouen.edeliv.dto.response.delivery

import fr.univrouen.edeliv.dto.response.deliverytour.DeliveryTourResponseDTO

/**
 * Represents a delivery.
 */
class DeliveryResponseDTO(
    /**
     * The delivery's ID.
     */
    var id: Long,

    /**
     * The start location of the delivery.
     */
    var startAddress: String,

    /**
     * The location where the delivery arrives.
     */
    var endAddress: String,

    /**
     * The delivery tour associated to this delivery.
     */
    var tour: DeliveryTourResponseDTO?
) {

    constructor(): this(0L, "", "", null)

}