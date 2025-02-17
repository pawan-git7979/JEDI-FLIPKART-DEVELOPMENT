package com.flipkart.bean;

public class FlipFitNotification {
    private int notificationId;
    private int userId;
    private String message;
    private String status;

    public FlipFitNotification(){}
    public FlipFitNotification(int notificationId, int userId, String message, String status) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.status = status;
    }

    // Getters
    public int getNotificationId() { return notificationId; }
    public int getUserId() { return userId; }
    public String getMessage() { return message; }
    public String getStatus() { return status; }

    // Setters
    public void setNotificationId(int notificationId) { this.notificationId = notificationId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setMessage(String message) { this.message = message; }
    public void setStatus(String status) { this.status = status; }
}
