package fr.univrouen.edeliv.service

import fr.univrouen.edeliv.entity.User
import fr.univrouen.edeliv.exception.FunctionalException
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * The authentication service contains all the logic about the API authentication.
 */
interface AuthenticationService : UserDetailsService {

    /**
     * @return The current authenticated user.
     */
    fun getCurrentUser(): User

    /**
     * Get the user associated to the given token and returns it. If the token is invalid then null is returned.
     *
     * @param  token The user token.
     * @return       The user associated to the given token.
     */
    fun getUserByToken(token: String): User?

    /**
     * Login the user associated to the given login information and returns the user's token.
     *
     * @param  login                The user's nickname.
     * @param  password             The user's password.
     * @return                      The user's generated token.
     * @throws FunctionalException  Exception thrown if the given credentials are invalid.
     */
    @Throws(FunctionalException::class)
    fun login(login: String, password: String): String

}