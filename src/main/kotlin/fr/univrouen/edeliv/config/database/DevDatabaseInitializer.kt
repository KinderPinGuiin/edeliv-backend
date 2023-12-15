package fr.univrouen.edeliv.config.database

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order

/**
 * The development database initializer, it initializes some useful data for testing.
 */
@Configuration
@Profile(value = [ "dev" ])
@Order(1)
class DevDatabaseInitializer : ApplicationRunner {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    /**
     * Initialize the dev data.
     */
    override fun run(args: ApplicationArguments?) {
        logger.info("The development data has been initialized.")
    }

}