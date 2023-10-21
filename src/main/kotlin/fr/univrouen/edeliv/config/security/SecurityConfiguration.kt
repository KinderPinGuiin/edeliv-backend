package fr.univrouen.edeliv.config.security

import fr.univrouen.edeliv.controller.AuthenticationController
import fr.univrouen.edeliv.service.AuthenticationService
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

/**
 * Configure the Spring security component.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Enable PreAuthorize annotation and more
class SecurityConfiguration(
    /**
     * The Spring boot environment component.
     */
    private val environment: Environment,

    /**
     * The configured password encoder.
     */
    private val securityPasswordEncoder: SecurityPasswordEncoder,

    /**
     * The authentication service.
     */
    private val authenticationService: AuthenticationService,
) {

    /**
     * Method called to configure the Spring security component inside the configuration chain.
     *
     * @param  http The HTTP security configuration.
     * @return      The customized HTTP configuration as the rest of the configuration chain.
     */
    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        // Set the session management as stateless
        http.sessionManagement {
            sessionManagementCustomizer -> sessionManagementCustomizer
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        // Disable CORS and CSRF
        http.cors { corsCustomizer -> corsCustomizer.disable() }
        http.csrf { csrfCustomizer -> csrfCustomizer.disable() }

        // Enables the iFrames in dev mode (H2 console)
        if (this.environment.activeProfiles.any { profile -> profile == "dev" }) {
            http.headers {
                headersCustomizer -> headersCustomizer.frameOptions {
                    frameOptionsCustomizer -> frameOptionsCustomizer.sameOrigin()
                }
            }
        }

        // Indicates that any endpoint needs an authentication except some of them
        http.authorizeHttpRequests(fun (authorizeHttpRequestsCustomizer) {
            authorizeHttpRequestsCustomizer
                // Authorize any requests on the login endpoint
                .requestMatchers(AntPathRequestMatcher(AuthenticationController.LOGIN_ENDPOINT)).permitAll()
                // Authorize any requests on the swagger
                .requestMatchers(
                    AntPathRequestMatcher("/swagger-ui/**"),
                    AntPathRequestMatcher("/swagger-ui.html"),
                    AntPathRequestMatcher("/v3/api-docs/**"),
                ).permitAll()
                .requestMatchers(AntPathRequestMatcher("/error")).permitAll()

            // Authorize any requests on the H2 database if the environment is dev
            if (this.environment.activeProfiles.asList().contains("dev")) {
                authorizeHttpRequestsCustomizer.requestMatchers(PathRequest.toH2Console()).permitAll()
            }

            // Require the authentication on any other endpoints
            authorizeHttpRequestsCustomizer.anyRequest().authenticated()
        })

        // Add the TokenAuthenticationFilter to the security filter list
        http.addFilter(TokenAuthenticationFilter(this.authenticationManager()!!, this.authenticationService))

        return http.build()
    }

    /**
     * @return The authentication manager set up with the AuthenticationService and the good password encoder.
     */
    @Bean
    fun authenticationManager(): AuthenticationManager? {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(this.authenticationService)
        authProvider.setPasswordEncoder(this.securityPasswordEncoder.getPasswordEncoder())
        return ProviderManager(authProvider)
    }

}