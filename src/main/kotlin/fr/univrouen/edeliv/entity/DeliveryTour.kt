package fr.univrouen.edeliv.entity

import jakarta.persistence.*
import java.time.Instant

/**
 * Represents a delivery tour (a set of delivery).
 */
@Entity
class DeliveryTour(
    /**
     * The delivery tour's name (ID of the tour).
     */
    @Id
    val name: String,

    /**
     * The delivery tour's start date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    var startDate: Instant,

    /**
     * The delivery tour's end date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    var endDate: Instant,

    /**
     * The deliverer associated to this tour.
     */
    @ManyToOne
    @JoinColumn(name = "deliverer_id", nullable = false)
    var deliverer: Deliverer,

    /**
     * The deliveries associated to this tour.
     */
    @OneToMany(mappedBy = "tour")
    var deliveries: MutableList<Delivery>
) {

    constructor(): this("", Instant.EPOCH, Instant.EPOCH, Deliverer(), mutableListOf())

}