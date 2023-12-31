package fr.univrouen.edeliv.service.standard

import fr.univrouen.edeliv.constant.error.ErrorMessage
import fr.univrouen.edeliv.entity.DeliveryTour
import fr.univrouen.edeliv.exception.FunctionalException
import fr.univrouen.edeliv.repository.DeliveryRepository
import fr.univrouen.edeliv.repository.DeliveryTourRepository
import fr.univrouen.edeliv.service.DelivererService
import fr.univrouen.edeliv.service.DeliveryTourService
import fr.univrouen.edeliv.service.pojo.deliverytour.DeliveryTourSearchParam
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

/**
 * The standard delivery tour service using the database.
 */
@Service("standard-delivery-tour-service")
class StandardDeliveryTourService(
    private val deliveryTourRepository: DeliveryTourRepository,
    private val delivererService: DelivererService,
    private val deliveryRepository: DeliveryRepository,
) : DeliveryTourService {

    override fun getDeliveryTourAmount(): Long = this.deliveryTourRepository.count()

    override fun getDeliveryTourByName(name: String): DeliveryTour {
        // Get the delivery tour and throw an exception if it is null
        return this.deliveryTourRepository.findById(name).orElse(null)
            ?: throw FunctionalException(ErrorMessage.INVALID_DELIVERY_TOUR_ID, HttpStatus.NOT_FOUND)
    }

    override fun getAllDeliveryTours(searchParams: DeliveryTourSearchParam?): List<DeliveryTour> {
        if (searchParams == null) {
            return this.deliveryTourRepository.findAll();
        }

        return this.deliveryTourRepository.findAllWithSearchParams(
            PageRequest.of(searchParams.page ?: 0, searchParams.pageSize ?: 10), searchParams.tourDate
        )
    }

    @Transactional(rollbackFor = [ Exception::class ])
    override fun createDeliveryTour(name: String, startDate: Instant, endDate: Instant, delivererId: Long): DeliveryTour {
        // Checks that the given name doesn't already exist
        if (this.deliveryTourRepository.findById(name).isPresent) {
            throw FunctionalException(ErrorMessage.TOUR_ALREADY_EXISTS, HttpStatus.BAD_REQUEST)
        }

        // Checks that the start date is lower than the end date
        if (startDate >= endDate) {
            throw FunctionalException(ErrorMessage.INVALID_TOUR_END_DATE, HttpStatus.BAD_REQUEST)
        }

        // Checks that the deliverer doesn't already have a tour at the given dates
        val deliverer = this.delivererService.getDelivererById(delivererId)
        if (this.deliveryTourRepository.findTourByDate(deliverer, startDate).isPresent) {
            throw FunctionalException(ErrorMessage.TOUR_ALREADY_EXISTS_AT_DATE, HttpStatus.BAD_REQUEST)
        }

        // Persist the tour and returns it
        return this.deliveryTourRepository.save(DeliveryTour(name, startDate, endDate, deliverer, mutableListOf()));
    }

    @Transactional(rollbackFor = [ Exception::class ])
    override fun updateDeliveryTour(
        name: String,
        startDate: Instant,
        endDate: Instant,
        delivererId: Long,
        deliveriesIds: List<Long>
    ): DeliveryTour {
        // Retrieve the given delivery tour
        val deliveryTour = this.getDeliveryTourByName(name)

        // Check that the given parameters are valid
        if (startDate >= endDate) {
            throw FunctionalException(ErrorMessage.INVALID_TOUR_END_DATE, HttpStatus.BAD_REQUEST)
        }

        val deliverer = this.delivererService.getDelivererById(delivererId)
        val existingDeliveryTourAtDate = this.deliveryTourRepository.findTourByDate(deliverer, startDate)
        if (existingDeliveryTourAtDate.isPresent && existingDeliveryTourAtDate.get().name != name) {
            throw FunctionalException(ErrorMessage.TOUR_ALREADY_EXISTS_AT_DATE, HttpStatus.BAD_REQUEST)
        }

        val deliveries = deliveriesIds.map {
            deliveryId -> this.deliveryRepository.findById(deliveryId).orElse(null)
                ?: throw FunctionalException(ErrorMessage.INVALID_DELIVERY_ID, HttpStatus.NOT_FOUND)
        }.toMutableList()

        deliveryTour.name = name
        deliveryTour.startDate = startDate
        deliveryTour.endDate = endDate
        deliveryTour.deliverer = deliverer
        deliveryTour.deliveries = deliveries

        return this.deliveryTourRepository.save(deliveryTour)
    }

    @Transactional(rollbackFor = [ Exception::class ])
    override fun deleteDeliveryTour(name: String): DeliveryTour {
        val deliveryTour = this.getDeliveryTourByName(name)
        this.deliveryTourRepository.delete(deliveryTour)

        return deliveryTour
    }

}