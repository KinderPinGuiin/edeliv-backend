package fr.univrouen.edeliv.service

import fr.univrouen.edeliv.entity.DeliveryTour
import fr.univrouen.edeliv.exception.FunctionalException
import fr.univrouen.edeliv.service.pojo.deliverytour.DeliveryTourSearchParam
import java.time.Instant

/**
 * The delivery tour service contains all the logic about the delivery tours handling.
 */
interface DeliveryTourService {

    /**
     * Retrieves the delivery tour associated to the given name.
     *
     * @param  name                The name of the delivery tour to retrieve.
     * @return                     The delivery tour associated to the given name.
     * @throws FunctionalException Exception thrown if the given name is invalid.
     */
    @Throws(FunctionalException::class)
    fun getDeliveryTourByName(name: String): DeliveryTour;

    /**
     * @param  searchParams The delivery tour search parameters.
     * @return              All the delivery tours that matches with the search parameters. If no search params are given,
     *                      then all the deliveries are returned.
     */
    fun getAllDeliveryTours(searchParams: DeliveryTourSearchParam?): List<DeliveryTour>;

    /**
     * Creates the given delivery tour.
     *
     * @param  name                The name of the delivery tour.
     * @param  startDate           The start date of the delivery tour.
     * @param  endDate             The end date of the delivery tour.
     * @return                     The created delivery tour.
     * @throws FunctionalException Exception thrown if the name of the delivery tour already exists, if the given
     *                             deliverer already have a tour on the given dates or if the given deliverer is invalid.
     */
    @Throws(FunctionalException::class)
    fun createDeliveryTour(name: String, startDate: Instant, endDate: Instant, delivererId: Long): DeliveryTour

    /**
     * Updates the given delivery tour with the given new values.
     *
     * @param  name                The name of the delivery to update.
     * @param  startDate           The new start date of the delivery tour.
     * @param  endDate             The new end date of the delivery tour.
     * @param  deliveries          The IDs of the deliveries associated to this tour.
     * @return                     The updated delivery tour.
     * @throws FunctionalException Exception thrown if the given name is invalid.
     */
    @Throws(FunctionalException::class)
    fun updateDeliveryTour(name: String, startDate: Instant, endDate: Instant, deliveries: List<Long>): DeliveryTour

    /**
     * Deletes the delivery tour associated to the given name.
     *
     * @param  name                The name of the delivery tour to delete.
     * @return                     The deleted delivery tour.
     * @throws FunctionalException Exception thrown if the given name is invalid.
     */
    @Throws(FunctionalException::class)
    fun deleteDeliveryTour(name: String): DeliveryTour

}