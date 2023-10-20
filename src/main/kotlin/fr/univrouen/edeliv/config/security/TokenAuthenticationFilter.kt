package fr.univrouen.edeliv.config.security

import fr.univrouen.edeliv.service.AuthenticationService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class TokenAuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val authenticationService: AuthenticationService,
) : BasicAuthenticationFilter(authenticationManager)  {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    /**
     * This method will get the Authorization HTTP header's value to check if the given Bearer token is valid.
     *
     * @param request  The HTTP request sent to the server.
     * @param response The HTTP response to send to the user.
     * @param chain    The Spring Boot request filter chain (middleware).
     */
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        // Get the Authorization header
        val header = request.getHeader("Authorization")

        // If no Authorization header is found, or if the token doesn't start by Bearer, then we let the rest of the
        // chain handle the request
        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response)
            if (response.status == HttpStatus.FORBIDDEN.value()) {
                logger.info("Someone tried to reach " + request.requestURI + " without providing bearer token.")
            }

            return
        }

        // Get the token value and get the user associated to it
        val authToken: String = header.replace("Bearer", "").trim()
        val user = this.authenticationService.getUserByToken(authToken)
        if (user == null) {
            // If the user is null (invalid token) then we don't do anything
            chain.doFilter(request, response)

            return
        }

        // Authenticate the user to Spring Boot
        val authentication = UsernamePasswordAuthenticationToken(user, authToken, user.authorities)
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication

        chain.doFilter(request, response)
    }

}