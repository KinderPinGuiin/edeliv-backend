package fr.univrouen.edeliv.dto.response.deliverer

import java.time.Instant

/**
 * DTO representing a deliverer.
 */
class DelivererResponseDTO(
    /**
     * The deliverer's ID.
     */
    val id: Long,

    /**
     * The deliverer's name.
     */
    val name: String,

    /**
     * Indicates if the deliverer is available or not.
     */
    val isAvailable: Boolean,

    /**
     * The deliverer's creation date.
     */
    val creationDate: Instant,
)