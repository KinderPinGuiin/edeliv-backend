package fr.univrouen.edeliv.config.database

import fr.univrouen.edeliv.constant.role.RoleID
import fr.univrouen.edeliv.entity.User
import fr.univrouen.edeliv.repository.RoleRepository
import fr.univrouen.edeliv.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * The development database initializer, it initializes some useful data for testing.
 */
@Configuration
@Profile(value = [ "dev" ])
@Order(1)
class DevDatabaseInitializer(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,

    /**
     * The configured password encoder.
     */
    private val passwordEncoder: PasswordEncoder,
)  : ApplicationRunner {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    /**
     * Initialize the dev data.
     */
    override fun run(args: ApplicationArguments?) {
        this.initUsers()

        logger.info("The development data has been initialized.")
    }

    /**
     * Initialize the dev users.
     */
    private fun initUsers() {
        this.userRepository.save(User(
            0,
            "admin",
            this.passwordEncoder.encode("admin"),
            "36b780db-cdfc-40b6-b8b2-2f5699b5be44",
            mutableSetOf(this.roleRepository.findById(RoleID.USER).get())
        ))
    }

}