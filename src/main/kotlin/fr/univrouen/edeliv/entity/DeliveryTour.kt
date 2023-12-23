package fr.univrouen.edeliv.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
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
    @JsonIgnoreProperties("deliveryTours")
    var deliverer: Deliverer,

    /**
     * The deliveries associated to this tour.
     */
    @OneToMany(mappedBy = "tour")
    @JsonIgnoreProperties("tour")
    var deliveries: MutableList<Delivery>
) {

    constructor(): this("", Instant.EPOCH, Instant.EPOCH, Deliverer(), mutableListOf())

    @PreRemove
    fun preRemove() {
        deliveries.forEach { delivery -> delivery.tour = null }
    }

}