package fr.univrouen.edeliv.dto.request.delivery

/**
 * Request sent to update a delivery.
 */
class UpdateDeliveryRequestDTO(
    /**
     * The ID of the delivery to update.
     */
    var id: Long,

    /**
     * The new location of the beginning of the delivery.
     */
    var newStartAddress: String,

    /**
     * The new location of where the delivery should arrive.
     */
    var newEndAddress: String,
)