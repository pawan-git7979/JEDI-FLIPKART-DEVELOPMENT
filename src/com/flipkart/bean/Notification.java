package com.flipkart.beans;

public class Notification {
    private String id;
    private String notificationTemplate;

    public Notification(String id, String notificationTemplate) {
        this.id = id;
        this.notificationTemplate = notificationTemplate;
    }

    public void sendNotification() {
        // Logic to send notifications
    }

    // Getters and Setters
}
