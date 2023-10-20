package fr.univrouen.edeliv.controller

import fr.univrouen.edeliv.dto.request.authentication.AuthenticationRequestDTO
import fr.univrouen.edeliv.dto.response.AuthenticationResponseDTO
import fr.univrouen.edeliv.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * The authentication controller allows a user to connect to the API.
 */
@RestController
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {

    companion object {
        const val LOGIN_ENDPOINT = "/login"
    }

    /**
     * Check the given credentials and returns a token if they are valid.
     *
     * @param  request The login request
     * @return         The authentication response DTO containing the token.
     */
    @PostMapping(LOGIN_ENDPOINT)
    @ResponseBody
    fun login(@RequestBody request: AuthenticationRequestDTO): AuthenticationResponseDTO =
        AuthenticationResponseDTO(this.authenticationService.login(request.login, request.password))

}