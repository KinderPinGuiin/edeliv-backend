package fr.univrouen.edeliv.service

import fr.univrouen.edeliv.entity.Deliverer
import fr.univrouen.edeliv.exception.FunctionalException

/**
 * The deliverer service contains all the logic about the deliverer handling.
 */
interface DelivererService {

    /**
     * Retrieves all the deliverers and apply the given filters to the result.
     */
    fun getAllDeliverers(): List<Deliverer>;

    /**
     * Retrieves the deliverer associated to the given ID.
     *
     * @param  id                  The deliverer to get ID.
     * @return                     The deliverer associated to the given ID.
     * @throws FunctionalException Exception thrown if the given ID is invalid.
     */
    @Throws(FunctionalException::class)
    fun getDelivererById(id: Long): Deliverer;

    /**
     * Creates the given deliverer.
     *
     * @param  name        The deliverer's name.
     * @param  isAvailable Indicates if the deliverer is available or not.
     * @return             The created deliverer.
     */
    fun createDeliverer(name: String, isAvailable: Boolean): Deliverer

    /**
     * Updates the given deliverer with the given new values.
     *
     * @param  id                  The deliverer to update ID.
     * @param  newName             The new name of the deliverer.
     * @param  newIsAvailable      Indicates if the deliverer is available or not.
     * @return                     The updated deliverer.
     * @throws FunctionalException Exception thrown if the given ID is invalid.
     */
    @Throws(FunctionalException::class)
    fun updateDeliverer(id: Long, newName: String, newIsAvailable: Boolean): Deliverer

    /**
     * Deletes the deliverer associated to the given ID.
     *
     * @param  id                  The deliverer to delete ID.
     * @return                     The deleted deliverer.
     * @throws FunctionalException Exception thrown if the given ID is invalid.
     */
    @Throws(FunctionalException::class)
    fun deleteDeliverer(id: Long): Deliverer

}