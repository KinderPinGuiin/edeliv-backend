package fr.univrouen.edeliv.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
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
    var creationDate: Instant,

    /**
     * The delivery tours associated to this deliverer.
     */
    @OneToMany(mappedBy = "deliverer", orphanRemoval = true)
    @JsonIgnoreProperties("deliverer")
    var deliveryTours: MutableList<DeliveryTour>,
) {

    constructor(): this(0, "", true, Instant.now(), mutableListOf())

}