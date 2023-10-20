package fr.univrouen.edeliv.repository

import fr.univrouen.edeliv.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * The user repository allows us to retrieve information about an API user.
 */
@Repository
interface UserRepository : CrudRepository<User, Long> {

    /**
     * @param  username The user's username.
     * @return          The user associated to the given username, or null if the username is invalid.
     */
    fun findByUsername(username: String): User?

    /**
     * @param  token The user's token.
     * @return       The user associated to the given token, or null if the token is invalid.
     */
    fun findByToken(token: String): User?

}