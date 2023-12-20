package fr.univrouen.edeliv.adapter.deliverer

import fr.univrouen.edeliv.dto.response.deliverer.DelivererResponseDTO
import fr.univrouen.edeliv.entity.Deliverer

/**
 * Adapter converting Deliverer entity to a DTO and vice-cersa.
 */
class DelivererAdapter {

    companion object {

        /**
         * Convert a Deliverer to a DelivererResponseDTO.
         *
         * @param  deliverer The deliverer to convert.
         * @return           The resulting DelivererResponseDTO.
         */
        fun fromDeliverer(deliverer: Deliverer): DelivererResponseDTO =
            DelivererResponseDTO(deliverer.id, deliverer.name, deliverer.isAvailable, deliverer.creationDate)

    }

}