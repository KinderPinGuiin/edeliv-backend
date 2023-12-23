package fr.univrouen.edeliv.repository

import fr.univrouen.edeliv.entity.Deliverer
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.time.Instant

/**
 * The deliverer repository allows us to retrieve information about the deliverers.
 */
interface DelivererRepository : JpaRepository<Deliverer, Long> {

    @Query("""
        SELECT d 
        FROM Deliverer AS d
        WHERE d.creationDate >= :minDate 
            AND d.creationDate < :maxDate 
            AND (:isDelivererAvailable IS NULL OR (:isDelivererAvailable = true AND isAvailable = true) OR (:isDelivererAvailable = false AND isAvailable = false))
            AND (:nameFilter IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :nameFilter, '%')))
    """)
    fun findAllWithSearchParams(
        minDate: Instant,
        maxDate: Instant,
        isDelivererAvailable: Boolean?,
        nameFilter: String?,
        page: Pageable,
    ): List<Deliverer>

}