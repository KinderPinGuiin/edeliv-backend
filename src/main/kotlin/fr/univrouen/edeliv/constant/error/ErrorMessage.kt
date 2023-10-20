package fr.univrouen.edeliv.constant.error

enum class ErrorMessage(val message: String) {

    // Generic errors
    INTERNAL_SERVER_ERROR("Une erreur interne est survenue."),
    UNKNOWN_ERROR("Une erreur non identifiée est survenue."),
    ACCESS_DENIED("Accès refusé, veuillez-vous identifier."),

    // Authentication errors
    INVALID_CREDENTIALS("Nom d'utilisateur ou mot de passe invalide"),
    INVALID_USERNAME("Nom d'utilisateur invalide"),

}