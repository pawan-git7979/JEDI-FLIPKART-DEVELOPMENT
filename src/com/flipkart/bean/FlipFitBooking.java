package com.flipkart.bean;

import java.time.LocalDateTime;

/**
 * The FlipFitBooking class represents a booking made by a user
 * in the FlipFit system. It contains details about the booking,
 * such as user, center, slot, status, and timestamps.
 */
public class FlipFitBooking {
    private int bookingId;
    private int userId;
    private int centerId;
    private int slotId;
    private BookingStatus status;
    private int paymentId;
    private LocalDateTime bookingTime;
    private LocalDateTime updatedTime;

    /**
     * Default Constructor.
     * Initializes a FlipFitBooking object with default values.
     */
    public FlipFitBooking() {}

    /**
     * Parameterized Constructor.
     * Initializes a FlipFitBooking object with the given details.
     *
     * @param bookingId   The unique ID of the booking.
     * @param userId      The ID of the user making the booking.
     * @param centerId    The ID of the fitness center.
     * @param slotId      The ID of the booked time slot.
     * @param status      The current status of the booking (BOOKED, WAITLISTED, CANCELLED).
     * @param paymentId   The ID of the payment associated with the booking.
     * @param bookingTime The timestamp when the booking was created.
     * @param updatedTime The timestamp of the last update to the booking.
     */
    public FlipFitBooking(int bookingId, int userId, int centerId, int slotId, BookingStatus status, int paymentId,
                          LocalDateTime bookingTime, LocalDateTime updatedTime) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.centerId = centerId;
        this.slotId = slotId;
        this.status = status;
        this.paymentId = paymentId;
        this.bookingTime = bookingTime;
        this.updatedTime = updatedTime;
    }

    /**
     * Gets the booking ID.
     *
     * @return The unique ID of the booking.
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Sets the booking ID.
     *
     * @param bookingId The unique ID to assign to the booking.
     */
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Gets the user ID associated with the booking.
     *
     * @return The ID of the user who made the booking.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID for the booking.
     *
     * @param userId The ID of the user making the booking.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the center ID where the booking is made.
     *
     * @return The ID of the fitness center.
     */
    public int getCenterId() {
        return centerId;
    }

    /**
     * Sets the center ID for the booking.
     *
     * @param centerId The ID of the fitness center.
     */
    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    /**
     * Gets the slot ID of the booking.
     *
     * @return The ID of the booked time slot.
     */
    public int getSlotId() {
        return slotId;
    }

    /**
     * Sets the slot ID for the booking.
     *
     * @param slotId The ID of the booked time slot.
     */
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    /**
     * Gets the current booking status.
     *
     * @return The status of the booking (BOOKED, WAITLISTED, CANCELLED).
     */
    public BookingStatus getStatus() {
        return status;
    }

    /**
     * Sets the booking status.
     *
     * @param status The status to set (BOOKED, WAITLISTED, CANCELLED).
     */
    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    /**
     * Gets the payment ID associated with the booking.
     *
     * @return The ID of the payment transaction.
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the payment ID for the booking.
     *
     * @param paymentId The ID of the payment transaction.
     */
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Gets the booking creation timestamp.
     *
     * @return The timestamp when the booking was created.
     */
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    /**
     * Sets the booking creation timestamp.
     *
     * @param bookingTime The timestamp when the booking was created.
     */
    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    /**
     * Gets the last updated timestamp of the booking.
     *
     * @return The timestamp when the booking was last updated.
     */
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    /**
     * Sets the last updated timestamp for the booking.
     *
     * @param updatedTime The timestamp when the booking was last updated.
     */
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * Returns a string representation of the FlipFitBooking object.
     *
     * @return A formatted string containing booking details.
     */
    @Override
    public String toString() {
        return "FlipFitBooking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", centerId=" + centerId +
                ", slotId=" + slotId +
                ", status=" + status +
                ", paymentId=" + paymentId +
                ", bookingTime=" + bookingTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
