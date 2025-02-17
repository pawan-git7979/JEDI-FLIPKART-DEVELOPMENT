package com.flipkart.dao.FlipFitBooking;

import com.flipkart.bean.FlipFitBooking;
import java.util.List;

public interface FlipFitBookingInterface {
    FlipFitBooking getBookingById(int bookingId);
    List<FlipFitBooking> getBookingsByUser(int userId);
    boolean cancelBooking(int bookingId);
}
