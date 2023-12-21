package fr.univrouen.edeliv.dto.response.delivery

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
) {

    constructor(): this(0L, "", "")

}