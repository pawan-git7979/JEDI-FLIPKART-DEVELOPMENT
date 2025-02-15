package com.flipkart.bean;

import java.util.LinkedList;
import java.util.Queue;

public class FlipFitGymSlot {
    private int slotId; // Changed to int to match DAO
    private int gymId; // Changed to int to match DAO
    private String startTime;
    private String endTime;
    private String trainer;
    private int numOfSeats;
    private int numOfSeatsBooked;
    private Queue<Integer> waitlistBookingIds; // Queue for FIFO ordering

    public FlipFitGymSlot() {
        this.waitlistBookingIds = new LinkedList<>();
    }

    public FlipFitGymSlot(int slotId, int gymId, String startTime, String endTime,
                          String trainer, int numOfSeats, int numOfSeatsBooked) {
        this.slotId = slotId;
        this.gymId = gymId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trainer = trainer;
        this.numOfSeats = numOfSeats;
        this.numOfSeatsBooked = numOfSeatsBooked;
        this.waitlistBookingIds = new LinkedList<>();
    }

    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }

    public int getGymId() { return gymId; }
    public void setGymId(int gymId) { this.gymId = gymId; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getTrainer() { return trainer; }
    public void setTrainer(String trainer) { this.trainer = trainer; }

    public int getNumOfSeats() { return numOfSeats; }
    public void setNumOfSeats(int numOfSeats) { this.numOfSeats = numOfSeats; }

    public int getNumOfSeatsBooked() { return numOfSeatsBooked; }
    public void setNumOfSeatsBooked(int numOfSeatsBooked) { this.numOfSeatsBooked = numOfSeatsBooked; }

    public Queue<Integer> getWaitlistBookingIds() { return waitlistBookingIds; }
    public void setWaitlistBookingIds(Queue<Integer> waitlistBookingIds) { this.waitlistBookingIds = waitlistBookingIds; }

    public void enqueueWaitlistBooking(int bookingId) {
        waitlistBookingIds.add(bookingId);
    }

    public Integer dequeueWaitlistBooking() {
        return waitlistBookingIds.poll();
    }

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
                '}';
    }
}
