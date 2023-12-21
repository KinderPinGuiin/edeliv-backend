package fr.univrouen.edeliv.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

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
) {

    constructor(): this(0, "", "")

}