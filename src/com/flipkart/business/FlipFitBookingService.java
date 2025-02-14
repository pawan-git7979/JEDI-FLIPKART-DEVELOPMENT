package com.flipkart.business;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitUser;
import java.util.ArrayList;
import java.util.List;

public class FlipFitBookingService implements FlipFitBookingServiceInterface {

    private List<FlipFitBooking> bookings = new ArrayList<>();
    private int bookingCounter = 1;

    @Override
    public FlipFitBooking createBooking(FlipFitUser user, int centerId, String slotId, int paymentId) {
        // Convert slotId string to int
        int slotIdInt = Integer.parseInt(slotId);
        FlipFitBooking booking = new FlipFitBooking(
                bookingCounter++, user.getUserId(), centerId,
                slotIdInt, "BOOKED", paymentId
        );
        bookings.add(booking);
        System.out.println("Booking created successfully with ID: " + booking.getId());
        return booking;
    }

    @Override
    public void cancelBooking(FlipFitUser user, int bookingId) {
        FlipFitBooking bookingToCancel = null;
        for (FlipFitBooking booking : bookings) {
            if (booking.getId() == bookingId && booking.getUserId() == user.getUserId()) {
                bookingToCancel = booking;
                break;
            }
        }
        if (bookingToCancel != null) {
            bookingToCancel.setStatus("CANCELLED");
            System.out.println("Booking ID " + bookingId + " cancelled successfully for " + user.getName());
        } else {
            System.out.println("Booking not found or unauthorized cancellation attempt.");
        }
    }

    // For usage in the menu to display all bookings
    public List<FlipFitBooking> getAllBookings() {
        return bookings;
    }
}
