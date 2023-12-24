package fr.univrouen.edeliv.repository

import fr.univrouen.edeliv.entity.Delivery
import fr.univrouen.edeliv.entity.DeliveryTour
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * The delivery repository allows us to retrieve information about the deliveries.
 */
@Repository
interface DeliveryRepository : JpaRepository<Delivery, Long> {

    @Query("""
        SELECT d 
        FROM Delivery AS d
        WHERE (:deliveryTour IS NULL OR LOWER(d.tour) LIKE LOWER(CONCAT('%', :deliveryTour, '%')))
    """)
    fun findAllWithSearchParams(page: Pageable, deliveryTour: String?): List<Delivery>

}