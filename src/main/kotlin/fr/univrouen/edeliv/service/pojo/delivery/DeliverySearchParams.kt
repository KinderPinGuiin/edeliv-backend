package fr.univrouen.edeliv.service.pojo.delivery

/**
 * Search parameters that can be passed to the getAllDeliveries method.
 */
class DeliverySearchParams(
    /**
     * The page of the search (start at 0).
     */
    val page: Int?,

    /**
     * The number of element per page (default : 10).
     */
    val pageSize: Int?,

    /**
     * If defined, only the deliveries that belongs to a delivery tour that contains the given string will be
     * returned (default: null).
     */
    val deliveryTour: String?
)