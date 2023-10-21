package fr.univrouen.edeliv.dto.request.deliverer

/**
 * Request sent to update a deliverer.
 */
class UpdateDelivererRequestDTO(
    /**
     * The ID of the deliverer to update.
     */
    val id: Long,

    /**
     * The new name of the deliverer.
     */
    val newName: String,

    /**
     * Indicates if the deliverer is available or not.
     */
    val newIsAvailable: Boolean,
)