package fr.univrouen.edeliv.controller

import fr.univrouen.edeliv.dto.request.deliverytour.CreateDeliveryTourRequestDTO
import fr.univrouen.edeliv.dto.request.deliverytour.UpdateDeliveryTourRequestDTO
import fr.univrouen.edeliv.dto.response.deliverytour.DeliveryTourResponseDTO
import fr.univrouen.edeliv.service.DeliveryTourService
import fr.univrouen.edeliv.service.pojo.deliverytour.DeliveryTourSearchParam
import org.modelmapper.ModelMapper
import org.springframework.web.bind.annotation.*
import java.time.Instant

/**
 * The delivery tour controller allows a user to manage the delivery tours.
 */
@RestController
class DeliveryTourController(
    private val deliveryTourService: DeliveryTourService,
    private val modelMapper: ModelMapper,
) {

    companion object {
        const val DELIVERY_TOUR_ROOT = "/delivery-tour"
        const val GET_ALL_DELIVERY_TOURS = "$DELIVERY_TOUR_ROOT/get-all"
        const val GET_DELIVERY_TOUR = "$DELIVERY_TOUR_ROOT/get/{name}"
        const val CREATE_DELIVERY_TOUR = "$DELIVERY_TOUR_ROOT/create"
        const val UPDATE_DELIVERY_TOUR = "$DELIVERY_TOUR_ROOT/update"
        const val DELETE_DELIVERY_TOUR = "$DELIVERY_TOUR_ROOT/delete/{name}"
    }

    /**
     * Retrieves all the delivery tours that matches the given criteria.
     */
    @GetMapping(GET_ALL_DELIVERY_TOURS)
    fun getAllDeliveryTours(
        @RequestParam("page") page: Int,
        @RequestParam("pageSize") pageSize: Int,
        @RequestParam("tourDate") tourDate: Instant?,
    ) =
        this.deliveryTourService.getAllDeliveryTours(DeliveryTourSearchParam(page, pageSize, tourDate))
            .map { deliveryTour -> this.modelMapper.map(deliveryTour, DeliveryTourResponseDTO::class.java) }

    /**
     * @param  name The name of the delivery tour to retrieve.
     * @return      The delivery tour associated to the given name.
     */
    @GetMapping(GET_DELIVERY_TOUR)
    fun getDeliveryTour(@PathVariable("name") name: String) =
        this.modelMapper.map(this.deliveryTourService.getDeliveryTourByName(name), DeliveryTourResponseDTO::class.java)

    /**
     * Creates a delivery tour with the given information.
     *
     * @param  creationRequest The information of the delivery to create.
     * @return                 The created delivery.
     */
    @PostMapping(CREATE_DELIVERY_TOUR)
    fun createDeliveryTour(creationRequest: CreateDeliveryTourRequestDTO) =
        this.modelMapper.map(
            this.deliveryTourService.createDeliveryTour(
                creationRequest.name,
                creationRequest.startDate,
                creationRequest.endDate,
                creationRequest.delivererId,
            ),
            DeliveryTourResponseDTO::class.java
        )

    /**
     * Updates a delivery tour with the given information.
     *
     * @param  updateRequest The new information of the delivery tour.
     * @return               The updated delivery tour.
     */
    @PostMapping(UPDATE_DELIVERY_TOUR)
    fun updateDeliveryTour(updateRequest: UpdateDeliveryTourRequestDTO) =
        this.modelMapper.map(
            this.deliveryTourService.updateDeliveryTour(
                updateRequest.name, updateRequest.newStartDate, updateRequest.newEndDate, updateRequest.deliveries
            ),
            DeliveryTourResponseDTO::class.java
        )

    /**
     * Removes the given delivery tour.
     *
     * @param  name The name of the delivery tour to remove.
     * @return      The removed delivery tour.
     */
    @DeleteMapping(DELETE_DELIVERY_TOUR)
    fun deleteDeliveryTour(@PathVariable("name") name: String) =
        this.modelMapper.map(this.deliveryTourService.deleteDeliveryTour(name), DeliveryTourResponseDTO::class.java)

}