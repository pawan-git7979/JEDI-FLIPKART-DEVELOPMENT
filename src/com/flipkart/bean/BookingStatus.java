package com.flipkart.bean;

/**
 * Enum representing the different statuses of a booking in the FlipFit system.
 */
public enum BookingStatus {

    /** The booking is confirmed. */
    BOOKED,

    /** The booking is on the waitlist due to unavailability. */
    WAITLISTED,

    /** The booking has been cancelled. */
    CANCELLED
}
