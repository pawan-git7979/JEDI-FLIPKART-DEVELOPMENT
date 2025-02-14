package com.flipkart.business;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitUser;

public interface FlipFitBookingServiceInterface {
    FlipFitBooking createBooking(FlipFitUser user, int centerId, String slotId, int paymentId);
    void cancelBooking(FlipFitUser user, int bookingId);
}
