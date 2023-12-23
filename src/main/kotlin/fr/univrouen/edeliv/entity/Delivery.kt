package fr.univrouen.edeliv.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

/**
 * CLass representing a delivery.
 */
@Entity
class Delivery(
    /**
     * The delivery's ID.
     */
    @Id
    @GeneratedValue
    val id: Long,

    /**
     * The start location of the delivery.
     */
    var startAddress: String,

    /**
     * The location where the delivery arrives.
     */
    var endAddress: String,

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = true)
    @JsonBackReference
    var tour: DeliveryTour?,
) {

    constructor(): this(0, "", "", null)

}