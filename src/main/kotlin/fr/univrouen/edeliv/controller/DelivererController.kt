package fr.univrouen.edeliv.controller

import fr.univrouen.edeliv.adapter.deliverer.DelivererAdapter
import fr.univrouen.edeliv.dto.request.deliverer.CreateDelivererRequestDTO
import fr.univrouen.edeliv.dto.request.deliverer.UpdateDelivererRequestDTO
import fr.univrouen.edeliv.service.DelivererService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

/**
 * The deliverer controller allows a user to manage the deliverers.
 */
@RestController
class DelivererController(
    private val delivererService: DelivererService,
) {

    companion object {
        const val DELIVERER_ROOT = "/deliverer"
        const val GET_DELIVERER = "$DELIVERER_ROOT/get/{id}"
        const val CREATE_DELIVERER = "$DELIVERER_ROOT/create"
        const val UPDATE_DELIVERER = "$DELIVERER_ROOT/update"
        const val DELETE_DELIVERER = "$DELIVERER_ROOT/delete/{id}"
    }

    /**
     * @param  id The ID of the deliverer to retrieve.
     * @return    The deliverer associated to the given ID.
     */
    @GetMapping(GET_DELIVERER)
    fun getDeliverer(@PathVariable("id") id: Long) =
        DelivererAdapter.fromDeliverer(this.delivererService.getDelivererById(id))

    /**
     * Creates a deliverer with the given information.
     *
     * @param  creationRequest The information of the deliverer to create.
     * @return                 The created deliverer.
     */
    @PostMapping(CREATE_DELIVERER)
    fun createDeliverer(creationRequest: CreateDelivererRequestDTO) =
        DelivererAdapter.fromDeliverer(
            this.delivererService.createDeliverer(creationRequest.name, creationRequest.isAvailable)
        )

    /**
     * Updates a deliverer with the given information.
     *
     * @param  updateRequest The new information of the deliverer.
     * @return               The updated deliverer.
     */
    @PostMapping(UPDATE_DELIVERER)
    fun updateDeliverer(updateRequest: UpdateDelivererRequestDTO) =
        DelivererAdapter.fromDeliverer(
            this.delivererService.updateDeliverer(updateRequest.id, updateRequest.newName, updateRequest.newIsAvailable)
        )

    /**
     * Removes the given deliverer.
     *
     * @param  id The ID of the deliverer to remove.
     * @return    The removed deliverer.
     */
    @DeleteMapping(DELETE_DELIVERER)
    fun deleteDeliverer(@PathVariable("id") id: Long) =
        DelivererAdapter.fromDeliverer(this.delivererService.deleteDeliverer(id))

}