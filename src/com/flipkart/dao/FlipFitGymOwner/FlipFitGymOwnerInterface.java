package com.flipkart.dao.FlipFitGymOwner;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitGymCenter;
import java.util.List;

public interface FlipFitGymOwnerInterface {

    boolean registerGymOwner(FlipFitGymOwner owner);

    FlipFitGymOwner getOwnerById(int ownerId);

    List<FlipFitGymOwner> getAllOwners();

    boolean updateOwnerDetails(FlipFitGymOwner owner);

    boolean addGymCenter(FlipFitGymCenter gym);

    boolean updateGymInfo(FlipFitGymCenter gym);

    boolean addOrUpdateSlot(int gymId, String startTime, String endTime, int seats);

    List<String> viewBookings(int ownerId);
}
