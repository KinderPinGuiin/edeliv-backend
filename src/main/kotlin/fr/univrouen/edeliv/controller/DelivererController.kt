package fr.univrouen.edeliv.controller

import fr.univrouen.edeliv.dto.request.deliverer.CreateDelivererRequestDTO
import fr.univrouen.edeliv.dto.request.deliverer.UpdateDelivererRequestDTO
import fr.univrouen.edeliv.dto.response.deliverer.DelivererResponseDTO
import fr.univrouen.edeliv.service.DelivererService
import fr.univrouen.edeliv.service.pojo.deliverer.DelivererSearchParams
import org.modelmapper.ModelMapper
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

/**
 * The deliverer controller allows a user to manage the deliverers.
 */
@RestController
class DelivererController(
    private val delivererService: DelivererService,
    private val modelMapper: ModelMapper,
) {

    companion object {
        const val DELIVERER_ROOT = "/deliverer"
        const val GET_ALL_DELIVERERS = "$DELIVERER_ROOT/get-all"
        const val GET_DELIVERER = "$DELIVERER_ROOT/get/{id}"
        const val CREATE_DELIVERER = "$DELIVERER_ROOT/create"
        const val UPDATE_DELIVERER = "$DELIVERER_ROOT/update"
        const val DELETE_DELIVERER = "$DELIVERER_ROOT/delete/{id}"
    }

    @GetMapping(GET_ALL_DELIVERERS)
    fun getAllDeliverers(
        @RequestParam("page") page: Int,
        @RequestParam("pageSize") pageSize: Int,
        @RequestParam("minDate") minDate: Instant?,
        @RequestParam("maxDate") maxDate: Instant?,
        @RequestParam("isDelivererAvailable") isDelivererAvailable: Boolean?,
        @RequestParam("nameSort") nameSort: Byte?,
        @RequestParam("creationDateSort") creationDateSort: Byte?,
        @RequestParam("nameFilter") nameFilter: String?,
    ) =
        this.delivererService.getAllDeliverers(
            DelivererSearchParams(
                page, pageSize, minDate, maxDate, isDelivererAvailable, nameSort, creationDateSort, nameFilter
            )
        ).map { deliverer -> this.modelMapper.map(deliverer, DelivererResponseDTO::class.java) }

    /**
     * @param  id The ID of the deliverer to retrieve.
     * @return    The deliverer associated to the given ID.
     */
    @GetMapping(GET_DELIVERER)
    fun getDeliverer(@PathVariable("id") id: Long) =
        this.modelMapper.map(this.delivererService.getDelivererById(id), DelivererResponseDTO::class.java)

    /**
     * Creates a deliverer with the given information.
     *
     * @param  creationRequest The information of the deliverer to create.
     * @return                 The created deliverer.
     */
    @PostMapping(CREATE_DELIVERER)
    fun createDeliverer(creationRequest: CreateDelivererRequestDTO) =
        this.modelMapper.map(
            this.delivererService.createDeliverer(creationRequest.name, creationRequest.isAvailable),
            DelivererResponseDTO::class.java
        )

    /**
     * Updates a deliverer with the given information.
     *
     * @param  updateRequest The new information of the deliverer.
     * @return               The updated deliverer.
     */
    @PostMapping(UPDATE_DELIVERER)
    fun updateDeliverer(updateRequest: UpdateDelivererRequestDTO) =
        this.modelMapper.map(
            this.delivererService.updateDeliverer(updateRequest.id, updateRequest.newName, updateRequest.newIsAvailable),
            DelivererResponseDTO::class.java
        )

    /**
     * Removes the given deliverer.
     *
     * @param  id The ID of the deliverer to remove.
     * @return    The removed deliverer.
     */
    @DeleteMapping(DELETE_DELIVERER)
    fun deleteDeliverer(@PathVariable("id") id: Long) =
        this.modelMapper.map(this.delivererService.deleteDeliverer(id), DelivererResponseDTO::class.java)

}