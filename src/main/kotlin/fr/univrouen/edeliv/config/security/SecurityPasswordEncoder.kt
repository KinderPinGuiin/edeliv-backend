package fr.univrouen.edeliv.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Configure the Spring Security password encoder.
 */
@Configuration
class SecurityPasswordEncoder {

    /**
     * @return The password encoder to use inside the Spring Boot application.
     */
    @Bean
    fun getPasswordEncoder() = BCryptPasswordEncoder()

}