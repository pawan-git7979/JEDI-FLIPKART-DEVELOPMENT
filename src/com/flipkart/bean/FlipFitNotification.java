package com.flipkart.bean;

/**
 * The FlipFitNotification class represents a notification that is sent to a user.
 * It contains details such as the notification ID, user ID, message, and the status of the notification.
 */
public class FlipFitNotification {
    private int notificationId; // Unique identifier for the notification
    private int userId; // User ID to whom the notification is sent
    private String message; // Message content of the notification
    private String status; // Status of the notification (e.g., "read", "unread")

    /**
     * Default Constructor.
     * Initializes a new FlipFitNotification object without setting any fields.
     */
    public FlipFitNotification() {}

    /**
     * Parameterized Constructor.
     * Initializes a new FlipFitNotification object with the specified details.
     *
     * @param notificationId The unique ID of the notification.
     * @param userId         The user ID to whom the notification is sent.
     * @param message        The message content of the notification.
     * @param status         The status of the notification (e.g., "read", "unread").
     */
    public FlipFitNotification(int notificationId, int userId, String message, String status) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.status = status;
    }

    /**
     * Gets the unique notification ID.
     *
     * @return The notification ID.
     */
    public int getNotificationId() { return notificationId; }

    /**
     * Sets the unique notification ID.
     *
     * @param notificationId The notification ID.
     */
    public void setNotificationId(int notificationId) { this.notificationId = notificationId; }

    /**
     * Gets the user ID to whom the notification is sent.
     *
     * @return The user ID.
     */
    public int getUserId() { return userId; }

    /**
     * Sets the user ID to whom the notification is sent.
     *
     * @param userId The user ID.
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Gets the message content of the notification.
     *
     * @return The message of the notification.
     */
    public String getMessage() { return message; }

    /**
     * Sets the message content of the notification.
     *
     * @param message The message of the notification.
     */
    public void setMessage(String message) { this.message = message; }

    /**
     * Gets the status of the notification (e.g., "read", "unread").
     *
     * @return The status of the notification.
     */
    public String getStatus() { return status; }

    /**
     * Sets the status of the notification (e.g., "read", "unread").
     *
     * @param status The status of the notification.
     */
    public void setStatus(String status) { this.status = status; }
}
