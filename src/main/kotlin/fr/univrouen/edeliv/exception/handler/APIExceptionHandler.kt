package fr.univrouen.edeliv.exception.handler

import fr.univrouen.edeliv.constant.error.ErrorMessage
import fr.univrouen.edeliv.dto.error.APIErrorDTO
import fr.univrouen.edeliv.dto.error.SimpleAPIErrorDTO
import fr.univrouen.edeliv.exception.FunctionalException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * This class handles all the specified exceptions that are not caught inside the application.
 */
@RestControllerAdvice
class APIExceptionHandler : ResponseEntityExceptionHandler() {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    /**
     * Handles a FunctionalException.
     *
     * @param  exception The exception that occurred.
     * @param  request   The user HTTP request.
     * @return           The response to send to the user.
     */
    @ExceptionHandler(value = [ FunctionalException::class ])
    fun functionalError(exception: FunctionalException, request: WebRequest): ResponseEntity<APIErrorDTO> {
        logger.error(exception)
        logger.trace(exception.printStackTrace())
        return ResponseEntity
            .status(exception.httpStatus)
            .body(exception.error ?: SimpleAPIErrorDTO(ErrorMessage.UNKNOWN_ERROR.message))
    }

    /**
     * Handles the AccessDeniedException.
     *
     * @param  exception The exception that occurred.
     * @param  request   The user HTTP request.
     * @return           The response to send to the user.
     */
    @ExceptionHandler(value = [ AccessDeniedException::class ])
    fun accessDeniedException(exception: AccessDeniedException, request: WebRequest): ResponseEntity<APIErrorDTO> {
        logger.error(exception)
        logger.trace(exception.printStackTrace())
        logger.debug(exception.printStackTrace())
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(SimpleAPIErrorDTO(ErrorMessage.ACCESS_DENIED.message))
    }

    /**
     * Handles the other exceptions.
     *
     * @param  exception The exception that occurred.
     * @return           The response to send to the user.
     */
    @ExceptionHandler(value = [ Exception::class ])
    fun defaultException(exception: Exception): ResponseEntity<APIErrorDTO> {
        logger.error(exception.stackTraceToString())
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(SimpleAPIErrorDTO(ErrorMessage.INTERNAL_SERVER_ERROR.message))
    }

}