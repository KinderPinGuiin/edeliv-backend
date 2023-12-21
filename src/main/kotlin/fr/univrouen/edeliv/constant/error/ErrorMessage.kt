package fr.univrouen.edeliv.constant.error

enum class ErrorMessage(val message: String) {

    // Generic errors
    INTERNAL_SERVER_ERROR("Une erreur interne est survenue."),
    UNKNOWN_ERROR("Une erreur non identifiée est survenue."),

    // Deliverer errors
    INVALID_DELIVERER_ID("L'identifiant du livreur est invalide."),

}