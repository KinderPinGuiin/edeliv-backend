package fr.univrouen.edeliv.constant.error

enum class ErrorMessage(val message: String) {

    // Generic errors
    INTERNAL_SERVER_ERROR("Une erreur interne est survenue."),
    UNKNOWN_ERROR("Une erreur non identifiée est survenue."),

    // Deliverer errors
    INVALID_DELIVERER_ID("L'identifiant du livreur est invalide."),

    // Delivery errors
    INVALID_DELIVERY_ID("L'identifiant de la livraison est invalide."),

    // Delivery tour errors
    INVALID_DELIVERY_TOUR_ID("L'identifiant de la tournée est invalide."),
    TOUR_ALREADY_EXISTS("La tournée demandée existe déjà."),
    INVALID_TOUR_END_DATE("La date de fin de la tournée est invalide."),
    TOUR_ALREADY_EXISTS_AT_DATE("La date donnée est indisponible pour ce livreur (tournée déjà affectée)."),

}