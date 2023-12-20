package fr.univrouen.edeliv.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import java.time.Instant

/**
 * CLass representing a deliverer.
 */
@Entity
class Deliverer(
    /**
     * The deliverer's ID.
     */
    @Id
    @GeneratedValue
    val id: Long,

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
    @Temporal(TemporalType.TIMESTAMP)
    var creationDate: Instant
) {

    constructor(): this(0, "", true, Instant.now())

}