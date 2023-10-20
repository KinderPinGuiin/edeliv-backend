package fr.univrouen.edeliv.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

/**
 * A role that indicates a user permissions on the API.
 */
@Entity
class Role(
    /**
     * The role's name (ID).
     */
    @Id
    val name: String,
) {

    /**
     * Role's default constructor.
     */
    constructor(): this("")

}