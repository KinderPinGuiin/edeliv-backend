package fr.univrouen.edeliv.dto.request.delivery

/**
 * Request sent to create a delivery.
 */
class CreateDeliveryRequestDTO(
    /**
     * The start location of the delivery.
     */
    var startAddress: String,

    /**
     * The location where the delivery arrives.
     */
    var endAddress: String,
)