package com.flipkart.bean;

import java.time.LocalDateTime;

public class FlipFitNotification {
    private int notificationId;
    private int userId;
    private String message;
    private String status; // "SENT" or "ERROR"
    private LocalDateTime timestamp; // New field for storing timestamp

    public FlipFitNotification() {
        this.status = "SENT"; // Default status
        this.timestamp = LocalDateTime.now(); // Default timestamp
    }

    public FlipFitNotification(int notificationId, int userId, String message, String status, LocalDateTime timestamp) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public int getNotificationId() { return notificationId; }
    public void setNotificationId(int notificationId) { this.notificationId = notificationId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "FlipFitNotification{" +
                "notificationId=" + notificationId +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
