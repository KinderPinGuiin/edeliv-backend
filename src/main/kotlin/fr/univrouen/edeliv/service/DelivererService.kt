package fr.univrouen.edeliv.service

import fr.univrouen.edeliv.entity.Deliverer
import fr.univrouen.edeliv.exception.FunctionalException
import fr.univrouen.edeliv.service.pojo.deliverer.DelivererSearchParams

/**
 * The deliverer service contains all the logic about the deliverer handling.
 */
interface DelivererService {

    /**
     * @return The amount of deliverers in the database.
     */
    fun getDelivererAmount(): Long

    /**
     * Retrieves the deliverer associated to the given ID.
     *
     * @param  id                  The ID of the deliverer to get.
     * @return                     The deliverer associated to the given ID.
     * @throws FunctionalException Exception thrown if the given ID is invalid.
     */
    @Throws(FunctionalException::class)
    fun getDelivererById(id: Long): Deliverer;

    /**
     * @param  searchParams The deliverer search parameters.
     * @return              All the deliverers that matches with the search parameters. If no search params are given,
     *                      then all the deliverers are returned.
     */
    fun getAllDeliverers(searchParams: DelivererSearchParams?): List<Deliverer>;

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
     * @param  id                  The ID of the delivery to update.
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
     * @param  id                  The ID of the deliverer to delete.
     * @return                     The deleted deliverer.
     * @throws FunctionalException Exception thrown if the given ID is invalid.
     */
    @Throws(FunctionalException::class)
    fun deleteDeliverer(id: Long): Deliverer

}