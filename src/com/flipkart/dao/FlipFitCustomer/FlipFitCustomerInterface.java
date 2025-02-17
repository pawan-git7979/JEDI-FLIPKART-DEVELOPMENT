package com.flipkart.dao.FlipFitCustomer;

import com.flipkart.bean.*;
import java.util.List;

public interface FlipFitCustomerInterface {

    /**
     * Registers a new gym customer in the system.
     *
     * @param customer The FlipFitGymCustomer object containing the customer's details.
     * @return true if the registration is successful, false otherwise.
     */
    boolean registerGymCustomer(FlipFitGymCustomer customer);

    /**
     * Retrieves a list of available cities where gyms are located.
     *
     * @return A list of city names where gyms are available.
     */
    List<String> getAvailableCities();

    /**
     * Retrieves a list of gyms located in a specific city.
     *
     * @param city The name of the city where gyms need to be fetched.
     * @return A list of FlipFitGymCenter objects representing the gyms in the specified city.
     */
    List<FlipFitGymCenter> getGymsByCity(String city);

    /**
     * Retrieves a list of available slots for a specific gym.
     *
     * @param gymId The ID of the gym for which the available slots are to be fetched.
     * @return A list of FlipFitGymSlot objects representing the available slots.
     */
    List<FlipFitGymSlot> getAvailableSlots(int gymId);

    /**
     * Adds a user to the waitlist for a specific gym slot.
     *
     * @param userId The ID of the user to be added to the waitlist.
     * @param slotId The ID of the gym slot for which the user is being added to the waitlist.
     */
    void addToWaitlist(int userId, int slotId);

    /**
     * Books a gym slot for a user.
     *
     * @param userId The ID of the user making the booking.
     * @param gymId The ID of the gym where the booking is being made.
     * @param slotId The ID of the slot to be booked (null if waitlisted).
     * @param booked 1 if the slot is booked, 0 if the user is waitlisted.
     * @return A FlipFitBooking object representing the booking made.
     */
    FlipFitBooking bookSlot(int userId, int gymId, Integer slotId, int booked);

    /**
     * Retrieves the list of bookings made by a specific user.
     *
     * @param userId The ID of the user whose bookings are to be fetched.
     * @return A list of FlipFitBooking objects representing the user's bookings.
     */
    List<FlipFitBooking> getUserBookings(int userId);

    /**
     * Retrieves the list of payments made by a specific user.
     *
     * @param userId The ID of the user whose payments are to be fetched.
     * @return A list of FlipFitPayment objects representing the user's payments.
     */
    List<FlipFitPayment> getUserPayments(int userId);

    /**
     * Retrieves the list of notifications for a specific user.
     *
     * @param userId The ID of the user whose notifications are to be fetched.
     * @return A list of FlipFitNotification objects representing the user's notifications.
     */
    List<FlipFitNotification> getUserNotifications(int userId);
    boolean deleteBooking(int bookingId);
}
