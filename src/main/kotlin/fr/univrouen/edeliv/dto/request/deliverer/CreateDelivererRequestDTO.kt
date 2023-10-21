package fr.univrouen.edeliv.dto.request.deliverer

/**
 * Request sent to create a deliverer.
 */
class CreateDelivererRequestDTO(
    /**
     * The deliverer's name.
     */
    val name: String,

    /**
     * Indicates if the deliverer is available or not.
     */
    val isAvailable: Boolean,
)