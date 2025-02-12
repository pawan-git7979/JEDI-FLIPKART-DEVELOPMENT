package com.flipkart.bean;

public class Booking {
    private int id;
    private int userId;
    private int centerId;
    private int slotId;
    private String status;   // Could be an enum (BOOKED, WAITLISTED, CANCELLED, etc.)
    private int paymentId;

    public Booking() {}
    public Booking(int id, int userId, int centerId, int slotId, String status, int paymentId) {
        this.id = id;
        this.userId = userId;
        this.centerId = centerId;
        this.slotId = slotId;
        this.status = status;
        this.paymentId = paymentId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getCenterId() { return centerId; }
    public void setCenterId(int centerId) { this.centerId = centerId; }

    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
}