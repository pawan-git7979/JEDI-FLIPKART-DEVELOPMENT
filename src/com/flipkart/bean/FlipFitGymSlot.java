package com.flipkart.bean;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The FlipFitGymSlot class represents a gym slot available for booking.
 * Each slot has a specific time, trainer, available seats, waitlist management, and a price.
 */
public class FlipFitGymSlot {
    private int slotId; // Unique identifier for the slot
    private int gymId; // Gym center ID associated with this slot
    private String startTime; // Start time of the slot
    private String endTime; // End time of the slot
    private String trainer; // Trainer assigned to the slot
    private int numOfSeats; // Total number of seats available
    private int numOfSeatsBooked; // Number of seats already booked
    private Queue<Integer> waitlistBookingIds; // Queue for FIFO ordering of waitlisted bookings
    private final int price; // Price of the slot

    /**
     * Parameterized Constructor.
     * Initializes a FlipFitGymSlot object with the given details.
     *
     * @param slotId            The unique ID of the gym slot.
     * @param gymId             The ID of the gym center associated with this slot.
     * @param startTime         The start time of the gym slot.
     * @param endTime           The end time of the gym slot.
     * @param numOfSeats        The total number of seats available in the slot.
     * @param numOfSeatsBooked  The number of seats already booked.
     * @param price             The price of booking this slot.
     */
    public FlipFitGymSlot(int slotId, int gymId, String startTime, String endTime,
                          int numOfSeats, int numOfSeatsBooked, int price) {
        this.slotId = slotId;
        this.gymId = gymId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numOfSeats = numOfSeats;
        this.numOfSeatsBooked = numOfSeatsBooked;
        this.waitlistBookingIds = new LinkedList<>();
        this.price = price;
    }

    /**
     * Gets the unique slot ID.
     * @return The slot ID.
     */
    public int getSlotId() { return slotId; }

    /**
     * Sets the unique slot ID.
     * @param slotId The slot ID.
     */
    public void setSlotId(int slotId) { this.slotId = slotId; }

    /**
     * Gets the gym center ID associated with this slot.
     * @return The gym ID.
     */
    public int getGymId() { return gymId; }

    /**
     * Sets the gym center ID associated with this slot.
     * @param gymId The gym ID.
     */
    public void setGymId(int gymId) { this.gymId = gymId; }

    /**
     * Gets the start time of the slot.
     * @return The start time.
     */
    public String getStartTime() { return startTime; }

    /**
     * Sets the start time of the slot.
     * @param startTime The start time.
     */
    public void setStartTime(String startTime) { this.startTime = startTime; }

    /**
     * Gets the end time of the slot.
     * @return The end time.
     */
    public String getEndTime() { return endTime; }

    /**
     * Sets the end time of the slot.
     * @param endTime The end time.
     */
    public void setEndTime(String endTime) { this.endTime = endTime; }

    /**
     * Gets the trainer assigned to the slot.
     * @return The trainer's name.
     */
    public String getTrainer() { return trainer; }

    /**
     * Sets the trainer assigned to the slot.
     * @param trainer The trainer's name.
     */
    public void setTrainer(String trainer) { this.trainer = trainer; }

    /**
     * Gets the total number of seats in the slot.
     * @return The total number of seats.
     */
    public int getNumOfSeats() { return numOfSeats; }

    /**
     * Sets the total number of seats in the slot.
     * @param numOfSeats The total number of seats.
     */
    public void setNumOfSeats(int numOfSeats) { this.numOfSeats = numOfSeats; }

    /**
     * Gets the number of seats booked in the slot.
     * @return The number of seats booked.
     */
    public int getNumOfSeatsBooked() { return numOfSeatsBooked; }

    /**
     * Sets the number of seats booked in the slot.
     * @param numOfSeatsBooked The number of seats booked.
     */
    public void setNumOfSeatsBooked(int numOfSeatsBooked) { this.numOfSeatsBooked = numOfSeatsBooked; }

    /**
     * Gets the price of booking this slot.
     * @return The price of the slot.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gets the waitlist queue of booking IDs.
     * @return A queue of waitlisted booking IDs.
     */
    public Queue<Integer> getWaitlistBookingIds() { return waitlistBookingIds; }

    /**
     * Sets the waitlist queue of booking IDs.
     * @param waitlistBookingIds A queue of waitlisted booking IDs.
     */
    public void setWaitlistBookingIds(Queue<Integer> waitlistBookingIds) { this.waitlistBookingIds = waitlistBookingIds; }

    /**
     * Adds a booking ID to the waitlist queue.
     * @param bookingId The booking ID to be waitlisted.
     */
    public void enqueueWaitlistBooking(int bookingId) {
        waitlistBookingIds.add(bookingId);
    }

    /**
     * Removes and returns the next waitlisted booking ID from the queue.
     * @return The booking ID that was waitlisted first, or null if empty.
     */
    public Integer dequeueWaitlistBooking() {
        return waitlistBookingIds.poll();
    }

    /**
     * Returns a string representation of the FlipFitGymSlot object.
     * @return A formatted string containing slot details.
     */
    @Override
    public String toString() {
        return "FlipFitGymSlot{" +
                "slotId=" + slotId +
                ", gymId=" + gymId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", trainer='" + trainer + '\'' +
                ", numOfSeats=" + numOfSeats +
                ", numOfSeatsBooked=" + numOfSeatsBooked +
                ", waitlistBookingIds=" + waitlistBookingIds +
                ", price=" + price +
                '}';
    }
}
