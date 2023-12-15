package fr.univrouen.edeliv.config.database

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order

/**
 * The database initializer, initialize some default data into the database.
 */
@Configuration
@Order(0) // Ensure that this DB initializer is executed before the development one
class DatabaseInitializer : ApplicationRunner {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    override fun run(args: ApplicationArguments?) {
        logger.info("The database has been initialized.")
    }

}
