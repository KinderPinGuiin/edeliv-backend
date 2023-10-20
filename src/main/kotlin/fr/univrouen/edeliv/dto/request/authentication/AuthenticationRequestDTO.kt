package fr.univrouen.edeliv.dto.request.authentication

/**
 * Represents a login request.
 */
class AuthenticationRequestDTO(
    /**
     * User login (username)
     */
    var login: String,

    /**
     * User password
     */
    var password: String
)
