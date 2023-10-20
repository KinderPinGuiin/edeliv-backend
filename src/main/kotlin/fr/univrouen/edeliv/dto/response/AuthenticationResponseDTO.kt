package fr.univrouen.edeliv.dto.response

/**
 * Represents the response sent to an authenticated user. It contains the token to use to make requests to the API.
 */
class AuthenticationResponseDTO(
    /**
     * User authentication token.
     */
    var token: String
)