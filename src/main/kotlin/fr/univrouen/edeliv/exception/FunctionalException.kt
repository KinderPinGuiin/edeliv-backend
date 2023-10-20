package fr.univrouen.edeliv.exception

import fr.univrouen.edeliv.constant.error.ErrorMessage
import fr.univrouen.edeliv.dto.error.APIErrorDTO
import fr.univrouen.edeliv.dto.error.SimpleAPIErrorDTO
import org.springframework.http.HttpStatus

/**
 * A functional exception is an exception thrown inside the services. They contain a DTO that will automatically be sent
 * to the user thanks to the exception handler.
 */
class FunctionalException(
    /**
     * The error DTO associated to the error.
     */
    val error: APIErrorDTO? = null,

    /**
     * The HTTP status to send.
     */
    val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
) : Exception(error?.getMessage()) {

    /**
     * FunctionalException constructor taking a string as message.
     *
     * @param message The exception message.
     */
    constructor(message: String): this(SimpleAPIErrorDTO(message))

    /**
     * FunctionalException constructor taking a string as message and the http status associated to the error.
     *
     * @param message    The exception message.
     * @param httpStatus The error's http status.
     */
    constructor(message: String, httpStatus: HttpStatus): this(SimpleAPIErrorDTO(message), httpStatus)

    /**
     * FunctionalException constructor taking an ErrorMessage and the http status associated to the error.
     *
     * @param message    The exception message as an ErrorMessage type.
     * @param httpStatus The error's http status.
     */
    constructor(message: ErrorMessage, httpStatus: HttpStatus): this(SimpleAPIErrorDTO(message.message), httpStatus)

}