package fr.univrouen.edeliv.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

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

        return http.build()
    }

}