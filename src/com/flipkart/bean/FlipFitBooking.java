package com.flipkart.bean;

import java.time.LocalDateTime;

public class FlipFitBooking {
    private int bookingId; // Renamed `id` to `bookingId`
    private int userId;
    private int centerId;
    private int slotId;
    private BookingStatus status; // ENUM type for status
    private int paymentId;
    private LocalDateTime bookingTime;
    private LocalDateTime updatedTime;

    // Default Constructor
    public FlipFitBooking() {}

    // Parameterized Constructor
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

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getCenterId() { return centerId; }
    public void setCenterId(int centerId) { this.centerId = centerId; }

    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }

    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }

    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }

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
