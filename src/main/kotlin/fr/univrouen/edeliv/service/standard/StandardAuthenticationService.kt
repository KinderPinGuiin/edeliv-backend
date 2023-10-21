package fr.univrouen.edeliv.service.standard

import fr.univrouen.edeliv.constant.error.ErrorMessage
import fr.univrouen.edeliv.entity.User
import fr.univrouen.edeliv.exception.FunctionalException
import fr.univrouen.edeliv.repository.UserRepository
import fr.univrouen.edeliv.service.AuthenticationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * The standard authentication service using the database.
 */
@Service("standard-authentication-service")
class StandardAuthenticationService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationService {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    override fun getUserByToken(token: String): User? = this.userRepository.findByToken(token)

    override fun getCurrentUser(): User = SecurityContextHolder.getContext().authentication.principal as User

    @Transactional(rollbackFor = [ Exception::class ])
    override fun login(login: String, password: String): String {
        // Get the user associated to the given nickname
        val user = this.userRepository.findByUsername(login)
            ?: throw FunctionalException(ErrorMessage.INVALID_CREDENTIALS, HttpStatus.FORBIDDEN)

        // Check the user password
        if (!this.passwordEncoder.matches(password, user.password)) {
            throw FunctionalException(ErrorMessage.INVALID_CREDENTIALS, HttpStatus.FORBIDDEN)
        }

        // Creates the user's token and associate it to him
        user.token = UUID.randomUUID().toString()
        this.userRepository.save(user)

        // Log the connection
        logger.info(user.username + " logged in.")

        return user.token!!
    }

    @Transactional(rollbackFor = [ Exception::class ])
    override fun loadUserByUsername(username: String?): UserDetails =
        this.userRepository.findByUsername(username ?: "") ?: throw FunctionalException(
            ErrorMessage.INVALID_USERNAME,
            HttpStatus.FORBIDDEN
        )

}