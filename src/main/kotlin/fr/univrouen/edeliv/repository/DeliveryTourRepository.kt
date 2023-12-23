package fr.univrouen.edeliv.repository

import fr.univrouen.edeliv.entity.Deliverer
import fr.univrouen.edeliv.entity.DeliveryTour
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.*

/**
 * The delivery tour repository allows us to retrieve information about the delivery tours.
 */
@Repository
interface DeliveryTourRepository : JpaRepository<DeliveryTour, String> {

    /**
     * Returns the delivery tour that matches the given date (startDate <= date <= endDate) and deliverer.
     */
    @Query("""
        SELECT dt
        FROM DeliveryTour AS dt
        WHERE dt.deliverer = :deliverer AND startDate <= :date AND endDate >= :date 
    """)
    fun findTourByDate(deliverer: Deliverer, date: Instant): Optional<DeliveryTour>

    @Query("""
        SELECT dt
        FROM DeliveryTour AS dt
        WHERE (:date IS NULL OR (dt.startDate <= :date AND dt.endDate >= :date))
    """)
    fun findAllWithSearchParams(page: Pageable, date: Instant?): List<DeliveryTour>

}