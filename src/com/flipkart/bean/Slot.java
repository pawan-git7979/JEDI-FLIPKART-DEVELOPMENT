package com.flipkart.bean;

import java.util.LinkedList;
import java.util.Queue;

public class Slot {
    private int id;
    private String date;           
    private String timeRange;     
    private int availableSeats;
      private Queue<Integer> waitingList = new LinkedList<>();

    public Slot() {}
    public Slot(int id, String date, String timeRange, int availableSeats, Queue<Integer> waitingList) {
        this.id = id;
        this.date = date;
        this.timeRange = timeRange;
        this.availableSeats = availableSeats;
        this.waitingList = waitingList;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTimeRange() { return timeRange; }
    public void setTimeRange(String timeRange) { this.timeRange = timeRange; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public Queue<Integer> getWaitingList() { return waitingList; }
    public void setWaitingList(Queue<Integer> waitingList) { this.waitingList = waitingList; }
}