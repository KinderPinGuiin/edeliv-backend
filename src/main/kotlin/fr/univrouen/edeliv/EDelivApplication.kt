package fr.univrouen.edeliv

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@OpenAPIDefinition
@SpringBootApplication(exclude = [ UserDetailsServiceAutoConfiguration::class ])
class EDelivApplication

fun main(args: Array<String>) {
	runApplication<EDelivApplication>(*args)
}
