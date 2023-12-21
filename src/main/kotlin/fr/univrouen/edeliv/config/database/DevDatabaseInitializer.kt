package fr.univrouen.edeliv.config.database

import fr.univrouen.edeliv.entity.Deliverer
import fr.univrouen.edeliv.entity.Delivery
import fr.univrouen.edeliv.repository.DelivererRepository
import fr.univrouen.edeliv.repository.DeliveryRepository
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
 * The development database initializer, it initializes some useful data for testing.
 */
@Configuration
@Profile(value = [ "dev" ])
@Order(1)
class DevDatabaseInitializer(
    private val delivererRepository: DelivererRepository,
    private val deliveryRepository: DeliveryRepository,
) : ApplicationRunner {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    /**
     * Initialize the dev data.
     */
    override fun run(args: ApplicationArguments?) {
        this.initDeliverers()
        this.initDeliveries()

        logger.info("The development data has been initialized.")
    }

    fun initDeliverers() {
        this.delivererRepository.save(Deliverer(0L, "Leo Marcus", true, Instant.now()))
        this.delivererRepository.save(Deliverer(0L, "Sébastien Pedro", false, Instant.now()))

        this.delivererRepository.save(Deliverer(0L, "Kilian Lefebvre", true, Instant.now().minus(1, ChronoUnit.DAYS)))
        this.delivererRepository.save(Deliverer(0L, "Laura Delmand", false, Instant.now().minus(1, ChronoUnit.DAYS)))

        this.delivererRepository.save(Deliverer(0L, "Robin Sean", true, Instant.now().minus(7, ChronoUnit.DAYS)))
        this.delivererRepository.save(Deliverer(0L, "Roberta Miguel", false, Instant.now().minus(7, ChronoUnit.DAYS)))
    }

    fun initDeliveries() {
        this.deliveryRepository.save(Delivery(0L, "30 Rue de la république", "20 Rue de la marchande"))
        this.deliveryRepository.save(Delivery(0L, "12 Avenue Jean Sarlau", "41 Rue Jean Jaures"))
        this.deliveryRepository.save(Delivery(0L, "125 Avenue Mc Risy", "1 Rue du Plon"))
    }

}