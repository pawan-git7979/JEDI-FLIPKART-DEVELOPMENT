package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;

public interface FlipFitNotificationServiceInterface {
    void sendNotification(FlipFitUser user, String message);
}
