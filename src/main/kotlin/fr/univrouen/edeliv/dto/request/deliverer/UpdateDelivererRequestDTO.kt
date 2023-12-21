package fr.univrouen.edeliv.dto.request.deliverer

/**
 * Request sent to update a deliverer.
 */
class UpdateDelivererRequestDTO(
    /**
     * The ID of the deliverer to update.
     */
    var id: Long,

    /**
     * The new name of the deliverer.
     */
    var newName: String,

    /**
     * Indicates if the deliverer is available or not.
     */
    var newIsAvailable: Boolean,
)