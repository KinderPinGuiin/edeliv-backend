package fr.univrouen.edeliv.dto.response.deliverer

import java.time.Instant

/**
 * DTO representing a deliverer.
 */
class DelivererResponseDTO(
    /**
     * The deliverer's ID.
     */
    var id: Long,

    /**
     * The deliverer's name.
     */
    var name: String,

    /**
     * Indicates if the deliverer is available or not.
     */
    var isAvailable: Boolean,

    /**
     * The deliverer's creation date.
     */
    var creationDate: Instant,
) {

    constructor(): this(0, "", true, Instant.MIN)

}