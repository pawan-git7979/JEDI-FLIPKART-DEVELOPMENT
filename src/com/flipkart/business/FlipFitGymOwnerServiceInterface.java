package com.flipkart.business;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitGymSlot;
import com.flipkart.bean.FlipFitGymCenter;

public interface FlipFitGymOwnerServiceInterface {
    void addGymCenter(FlipFitGymOwner owner, FlipFitGymCenter center);
    void updateGymInfo(FlipFitGymOwner owner, int centerId, String newName, String newLocation);
    void addOrUpdateSlot(FlipFitGymOwner owner, int centerId, FlipFitGymSlot slot);
}
