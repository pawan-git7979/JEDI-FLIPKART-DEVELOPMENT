package com.flipkart.bean;

public class FlipFitNotification {
    private int notificationId;
    private int userId;
    private String message;

    public FlipFitNotification() {}

    public FlipFitNotification(int notificationId, int userId, String message) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
    }

    public int getNotificationId() { return notificationId; }
    public void setNotificationId(int notificationId) { this.notificationId = notificationId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
