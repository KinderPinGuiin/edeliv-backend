package fr.univrouen.edeliv.config.webmvc

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

/**
 * Spring Boot web configuration file.
 */
@Configuration
@EnableWebMvc
class WebMVCConfig : WebMvcConfigurer {

    /**
     * Enables the cross-origin on the API.
     *
     * @param registry The CORS URL registry.
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE")
    }

}