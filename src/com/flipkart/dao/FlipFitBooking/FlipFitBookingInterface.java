package com.flipkart.dao.FlipFitBooking;

import com.flipkart.bean.FlipFitBooking;
import java.util.List;

/**
 * Interface defining the operations for managing gym bookings in the FlipFit application.
 */
public interface FlipFitBookingInterface {

    /**
     * Retrieves a booking by its unique booking ID.
     *
     * @param bookingId The ID of the booking to retrieve.
     * @return The FlipFitBooking object corresponding to the provided booking ID.
     */
    FlipFitBooking getBookingById(int bookingId);

    /**
     * Retrieves a list of bookings made by a particular user.
     *
     * @param userId The ID of the user whose bookings are to be retrieved.
     * @return A list of FlipFitBooking objects for the specified user.
     */
    List<FlipFitBooking> getBookingsByUser(int userId);

    /**
     * Cancels a booking by its unique booking ID.
     *
     * @param bookingId The ID of the booking to cancel.
     * @return true if the booking was successfully canceled, false otherwise.
     */
    boolean cancelBooking(int bookingId);
}
