package fr.univrouen.edeliv.service.standard

import fr.univrouen.edeliv.constant.error.ErrorMessage
import fr.univrouen.edeliv.entity.Deliverer
import fr.univrouen.edeliv.exception.FunctionalException
import fr.univrouen.edeliv.repository.DelivererRepository
import fr.univrouen.edeliv.service.DelivererService
import fr.univrouen.edeliv.service.pojo.deliverer.DelivererSearchParams
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

/**
 * The standard deliverer service using the database.
 */
@Service("standard-deliverer-service")
class StandardDelivererService(
    private val delivererRepository: DelivererRepository,
) : DelivererService {

    override fun getDelivererById(id: Long): Deliverer {
        // Get the deliverer and throw an exception if it is null
        return this.delivererRepository.findById(id).orElse(null)
            ?: throw FunctionalException(ErrorMessage.INVALID_DELIVERER_ID, HttpStatus.NOT_FOUND)
    }

    override fun getAllDeliverers(searchParams: DelivererSearchParams?): List<Deliverer> {
        if (searchParams == null)  {
            return this.delivererRepository.findAll().toList();
        }

        // Define the sort
        val orders = mutableListOf<Sort.Order>();
        if (searchParams.nameSort != null && searchParams.nameSort == 0.toByte()) {
            orders.add(Sort.Order.desc("name"))
        } else if (searchParams.nameSort != null && searchParams.nameSort == 1.toByte()) {
            orders.add(Sort.Order.asc("name"))
        }
        if (searchParams.creationDateSort != null && searchParams.creationDateSort == 0.toByte()) {
            orders.add(Sort.Order.desc("creationDate"))
        } else if (searchParams.creationDateSort != null && searchParams.creationDateSort == 1.toByte()) {
            orders.add(Sort.Order.asc("creationDate"))
        }

        return this.delivererRepository.findAllWithSearchParams(
            searchParams.minDate ?: Instant.EPOCH,
            searchParams.maxDate ?: Instant.MAX,
            searchParams.isDelivererAvailable ?: false,
            searchParams.nameFilter,
            PageRequest.of(searchParams.page ?: 0, searchParams.pageSize ?: 10, Sort.by(orders)),
        )
    }

    @Transactional(rollbackFor = [ Exception::class ])
    override fun createDeliverer(name: String, isAvailable: Boolean): Deliverer {
        // Save the deliverer created with the given information
        return this.delivererRepository.save(Deliverer(0, name, isAvailable, Instant.now()))
    }

    @Transactional(rollbackFor = [ Exception::class ])
    override fun updateDeliverer(id: Long, newName: String, newIsAvailable: Boolean): Deliverer {
        // Get the deliverer and update it
        val deliverer = this.getDelivererById(id)
        deliverer.name = newName
        deliverer.isAvailable = newIsAvailable

        // Return the updated deliverer
        return this.delivererRepository.save(deliverer)
    }

    @Transactional(rollbackFor = [ Exception::class ])
    override fun deleteDeliverer(id: Long): Deliverer {
        // Get the deliverer and remove it
        val deliverer = this.getDelivererById(id)
        this.delivererRepository.delete(deliverer)

        return deliverer
    }

}