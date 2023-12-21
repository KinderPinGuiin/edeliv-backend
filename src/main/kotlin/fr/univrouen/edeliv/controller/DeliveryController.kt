package fr.univrouen.edeliv.controller

import fr.univrouen.edeliv.dto.request.delivery.CreateDeliveryRequestDTO
import fr.univrouen.edeliv.dto.request.delivery.UpdateDeliveryRequestDTO
import fr.univrouen.edeliv.dto.response.delivery.DeliveryResponseDTO
import fr.univrouen.edeliv.service.DeliveryService
import fr.univrouen.edeliv.service.pojo.delivery.DeliverySearchParams
import org.modelmapper.ModelMapper
import org.springframework.web.bind.annotation.*

/**
 * The delivery controller allows a user to manage the deliveries.
 */
@RestController
class DeliveryController(
    private val deliveryService: DeliveryService,
    private val modelMapper: ModelMapper,
) {

    companion object {
        const val DELIVERY_ROOT = "/delivery"
        const val GET_ALL_DELIVERIES = "$DELIVERY_ROOT/get-all"
        const val GET_DELIVERY = "$DELIVERY_ROOT/get/{id}"
        const val CREATE_DELIVERY = "$DELIVERY_ROOT/create"
        const val UPDATE_DELIVERY = "$DELIVERY_ROOT/update"
        const val DELETE_DELIVERY = "$DELIVERY_ROOT/delete/{id}"
    }

    /**
     * Retrieves all the deliveries that matches the given criteria.
     */
    @GetMapping(GET_ALL_DELIVERIES)
    fun getAllDeliveries(
        @RequestParam("page") page: Int,
        @RequestParam("pageSize") pageSize: Int,
        @RequestParam("deliveryTour") deliveryTour: String?,
    ) =
        this.deliveryService.getAllDeliveries(DeliverySearchParams(page, pageSize, deliveryTour))
            .map { delivery -> this.modelMapper.map(delivery, DeliveryResponseDTO::class.java) }

    /**
     * @param  id The ID of the delivery to retrieve.
     * @return    The delivery associated to the given ID.
     */
    @GetMapping(GET_DELIVERY)
    fun getDelivery(@PathVariable("id") id: Long) =
        this.modelMapper.map(this.deliveryService.getDeliveryById(id), DeliveryResponseDTO::class.java)

    /**
     * Creates a delivery with the given information.
     *
     * @param  creationRequest The information of the delivery to create.
     * @return                 The created delivery.
     */
    @PostMapping(CREATE_DELIVERY)
    fun createDelivery(creationRequest: CreateDeliveryRequestDTO) =
        this.modelMapper.map(
            this.deliveryService.createDelivery(creationRequest.startAddress, creationRequest.endAddress),
            DeliveryResponseDTO::class.java
        )

    /**
     * Updates a delivery with the given information.
     *
     * @param  updateRequest The new information of the delivery.
     * @return               The updated delivery.
     */
    @PostMapping(UPDATE_DELIVERY)
    fun updateDelivery(updateRequest: UpdateDeliveryRequestDTO) =
        this.modelMapper.map(
            this.deliveryService.updateDelivery(updateRequest.id, updateRequest.newStartAddress, updateRequest.newEndAddress),
            DeliveryResponseDTO::class.java
        )

    /**
     * Removes the given delivery.
     *
     * @param  id The ID of the delivery to remove.
     * @return    The removed delivery.
     */
    @DeleteMapping(DELETE_DELIVERY)
    fun deleteDelivery(@PathVariable("id") id: Long) =
        this.modelMapper.map(this.deliveryService.deleteDelivery(id), DeliveryResponseDTO::class.java)

}