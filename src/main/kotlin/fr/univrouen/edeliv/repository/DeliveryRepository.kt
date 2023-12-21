package fr.univrouen.edeliv.repository

import fr.univrouen.edeliv.entity.Delivery
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * The delivery repository allows us to retrieve information about the deliveries.
 */
interface DeliveryRepository : JpaRepository<Delivery, Long> {

    // TODO : Add delivery tour filter
    @Query("""
        SELECT d 
        FROM Delivery AS d
    """)
    fun findAllWithSearchParams(page: Pageable): List<Delivery>

}