package fr.univrouen.edeliv.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Test controller.
 */
@RestController
class HelloController {

    companion object {
        const val DEFAULT_HELLO = "/hello"
        const val AUTHENTICATED_HELLO = "/authenticated-hello"
    }

    /**
     * Return hello world.
     */
    @GetMapping(DEFAULT_HELLO)
    fun hello() = "Hello world"

    /**
     * Return hello world to authenticated users.
     */
    @GetMapping(AUTHENTICATED_HELLO)
    fun authenticatedHello() = "Hello authenticated user"

}