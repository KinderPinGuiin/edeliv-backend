package fr.univrouen.edeliv.service.standard

import fr.univrouen.edeliv.constant.error.ErrorMessage
import fr.univrouen.edeliv.entity.Deliverer
import fr.univrouen.edeliv.exception.FunctionalException
import fr.univrouen.edeliv.repository.DelivererRepository
import fr.univrouen.edeliv.service.DelivererService
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

    @Transactional(rollbackFor = [ Exception::class ])
    override fun createDeliverer(name: String, isAvailable: Boolean): Deliverer {
        // Save the deliverer created with the given information
        return this.delivererRepository.save(Deliverer(0, name, isAvailable, Instant.now()))
    }

    override fun updateDeliverer(id: Long, newName: String, newIsAvailable: Boolean): Deliverer {
        // Get the deliverer and update it
        val deliverer = this.getDelivererById(id)
        deliverer.name = newName
        deliverer.isAvailable = newIsAvailable

        // Return the updated deliverer
        return this.delivererRepository.save(deliverer)
    }

    override fun deleteDeliverer(id: Long): Deliverer {
        // Get the deliverer and remove it
        val deliverer = this.getDelivererById(id)
        this.delivererRepository.delete(deliverer)

        return deliverer
    }

}