package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;

public class FlipFitNotificationService implements FlipFitNotificationServiceInterface {

    @Override
    public void sendNotification(FlipFitUser user, String message) {
        System.out.println("Notification sent to " + user.getName() + ": " + message);
    }
}
