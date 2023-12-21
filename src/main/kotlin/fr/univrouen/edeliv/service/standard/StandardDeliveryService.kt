package fr.univrouen.edeliv.service.standard

import fr.univrouen.edeliv.constant.error.ErrorMessage
import fr.univrouen.edeliv.entity.Delivery
import fr.univrouen.edeliv.exception.FunctionalException
import fr.univrouen.edeliv.repository.DeliveryRepository
import fr.univrouen.edeliv.service.DeliveryService
import fr.univrouen.edeliv.service.pojo.delivery.DeliverySearchParams
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * The standard delivery service using the database.
 */
@Service("standard-delivery-service")
class StandardDeliveryService(
    private val deliveryRepository: DeliveryRepository,
) : DeliveryService {

    override fun getDeliveryById(id: Long): Delivery {
        // Get the delivery and throw an exception if it is null
        return this.deliveryRepository.findById(id).orElse(null)
            ?: throw FunctionalException(ErrorMessage.INVALID_DELIVERER_ID, HttpStatus.NOT_FOUND)
    }

    override fun getAllDeliveries(searchParams: DeliverySearchParams?): List<Delivery> {
        if (searchParams == null)  {
            return this.deliveryRepository.findAll().toList();
        }

        // Retrieve the asked deliverers sorted and filtered
        return this.deliveryRepository.findAllWithSearchParams(
            PageRequest.of(searchParams.page ?: 0, searchParams.pageSize ?: 10),
        )
    }

    @Transactional(rollbackFor = [ Exception::class ])
    override fun createDelivery(startAddress: String, endAddress: String): Delivery {
        // Save the delivery created with the given information
        return this.deliveryRepository.save(Delivery(0L, startAddress, endAddress))
    }

    @Transactional(rollbackFor = [ Exception::class ])
    override fun updateDelivery(id: Long, startAddress: String, endAddress: String): Delivery {
        // Get the delivery and update it
        val delivery = this.getDeliveryById(id)
        delivery.startAddress = startAddress
        delivery.endAddress = endAddress

        // Return the updated deliverer
        return this.deliveryRepository.save(delivery)
    }

    @Transactional(rollbackFor = [ Exception::class ])
    override fun deleteDelivery(id: Long): Delivery {
        // Get the delivery and remove it
        val delivery = this.getDeliveryById(id)
        this.deliveryRepository.delete(delivery)

        return delivery
    }

}