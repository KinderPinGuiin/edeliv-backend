package fr.univrouen.edeliv.repository

import fr.univrouen.edeliv.entity.Deliverer
import org.springframework.data.repository.CrudRepository

/**
 * The deliverer repository allows us to retrieve information about the deliverers.
 */
interface DelivererRepository : CrudRepository<Deliverer, Long>