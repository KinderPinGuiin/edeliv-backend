package fr.univrouen.edeliv.config.database

import fr.univrouen.edeliv.entity.Deliverer
import fr.univrouen.edeliv.entity.Delivery
import fr.univrouen.edeliv.entity.DeliveryTour
import fr.univrouen.edeliv.repository.DelivererRepository
import fr.univrouen.edeliv.repository.DeliveryRepository
import fr.univrouen.edeliv.repository.DeliveryTourRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * The development database initializer, it initializes some useful data for testing purpose.
 */
@Configuration
@Profile(value = [ "dev" ])
@Order(1)
class DevDatabaseInitializer(
    private val delivererRepository: DelivererRepository,
    private val deliveryRepository: DeliveryRepository,
    private val deliveryTourRepository: DeliveryTourRepository,
) : ApplicationRunner {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    /**
     * Initialize the dev data.
     */
    override fun run(args: ApplicationArguments?) {
        this.initDeliverers()
        this.initDeliveriesAndTours()

        logger.info("The development data has been initialized.")
    }

    /**
     * Initialize the development deliverers.
     */
    fun initDeliverers() {
        this.delivererRepository.save(Deliverer(0L, "Leo Marcus", true, Instant.now()))
        this.delivererRepository.save(Deliverer(0L, "Sébastien Pedro", false, Instant.now()))

        this.delivererRepository.save(Deliverer(0L, "Kilian Lefebvre", true, Instant.now().minus(1, ChronoUnit.DAYS)))
        this.delivererRepository.save(Deliverer(0L, "Laura Delmand", false, Instant.now().minus(1, ChronoUnit.DAYS)))

        this.delivererRepository.save(Deliverer(0L, "Robin Sean", true, Instant.now().minus(7, ChronoUnit.DAYS)))
        this.delivererRepository.save(Deliverer(0L, "Roberta Miguel", false, Instant.now().minus(7, ChronoUnit.DAYS)))
    }

    /**
     * Initialize the development deliveries and deliveries tours.
     */
    fun initDeliveriesAndTours() {
        // Delivery tours
        val deliveryTour1 = this.deliveryTourRepository.save(DeliveryTour(
            "La tournée de Marco",
            Instant.now().minus(1, ChronoUnit.HOURS),
            Instant.now().plus(1, ChronoUnit.HOURS),
            this.delivererRepository.findById(1L).get(),
            mutableListOf()
        ))
        val deliveryTour2 = this.deliveryTourRepository.save(DeliveryTour(
            "La tournée de Gégé",
            Instant.now().minus(2, ChronoUnit.HOURS),
            Instant.now().plus(30, ChronoUnit.MINUTES),
            this.delivererRepository.findById(2L).get(),
            mutableListOf()
        ))

        // Deliveries
        this.deliveryRepository.save(Delivery(0L, "30 Rue de la république", "20 Rue de la marchande", deliveryTour1))
        this.deliveryRepository.save(Delivery(0L, "12 Avenue Jean Sarlau", "41 Rue Jean Jaures", deliveryTour1))
        this.deliveryRepository.save(Delivery(0L, "125 Avenue Mc Risy", "1 Rue du Plon", deliveryTour2))
        this.deliveryRepository.save(Delivery(0L, "14 Rue Julian Stanford", "1 Avenue Saint Saens", null))
    }

}