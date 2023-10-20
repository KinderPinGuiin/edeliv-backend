package fr.univrouen.edeliv.dto.error

/**
 * A simple API error only containing an error message.
 */
class SimpleAPIErrorDTO(
    /**
     * The error message.
     */
    private var message: String
) : APIErrorDTO {

    override fun getMessage(): String = this.message

}