package fr.univrouen.edeliv.service

import fr.univrouen.edeliv.entity.Delivery
import fr.univrouen.edeliv.exception.FunctionalException
import fr.univrouen.edeliv.service.pojo.delivery.DeliverySearchParams

/**
 * The delivery service contains all the logic about the deliveries handling.
 */
interface DeliveryService {

    /**
     * @return The amount of deliveries in the database.
     */
    fun getDeliveryAmount(): Long

    /**
     * Retrieves the delivery associated to the given ID.
     *
     * @param  id                  The ID of the delivery to get.
     * @return                     The delivery associated to the given ID.
     * @throws FunctionalException Exception thrown if the given ID is invalid.
     */
    @Throws(FunctionalException::class)
    fun getDeliveryById(id: Long): Delivery;

    /**
     * @param  searchParams The delivery search parameters.
     * @return              All the deliveries that matches with the search parameters. If no search params are given,
     *                      then all the deliveries are returned.
     */
    fun getAllDeliveries(searchParams: DeliverySearchParams?): List<Delivery>;

    /**
     * Creates the given delivery.
     *
     * @param  startAddress The delivery's start location.
     * @param  endAddress   The location where the delivery should arrive.
     * @return              The created delivery.
     */
    fun createDelivery(startAddress: String, endAddress: String): Delivery

    /**
     * Updates the given delivery with the given new values.
     *
     * @param  id                  The ID of the delivery to update.
     * @param  startAddress        The new location of the beginning of the delivery.
     * @param  endAddress          The new location of where the delivery should arrive.
     * @param  deliveryTourId      The delivery tour associated to the delivery.
     * @return                     The updated delivery.
     * @throws FunctionalException Exception thrown if the given ID is invalid, or if the deliverer doesn't exist.
     */
    @Throws(FunctionalException::class)
    fun updateDelivery(id: Long, startAddress: String, endAddress: String, deliveryTourId: String?): Delivery

    /**
     * Deletes the delivery associated to the given ID.
     *
     * @param  id                  The ID of the delivery to delete.
     * @return                     The deleted delivery.
     * @throws FunctionalException Exception thrown if the given ID is invalid.
     */
    @Throws(FunctionalException::class)
    fun deleteDelivery(id: Long): Delivery

}