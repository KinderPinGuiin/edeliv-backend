package fr.univrouen.edeliv.config.database

import fr.univrouen.edeliv.constant.role.RoleID
import fr.univrouen.edeliv.entity.Role
import fr.univrouen.edeliv.repository.RoleRepository
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
class DatabaseInitializer(
    private val roleRepository: RoleRepository,
) : ApplicationRunner {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    override fun run(args: ApplicationArguments?) {
        this.initRoles()

        logger.info("The database has been initialized.")
    }

    /**
     * Initialize the default roles.
     */
    private fun initRoles() {
        if (this.roleRepository.findAll().count() == 0) {
            RoleID.ALL.forEach { role -> roleRepository.save(Role(role)) }
        }
    }

}
