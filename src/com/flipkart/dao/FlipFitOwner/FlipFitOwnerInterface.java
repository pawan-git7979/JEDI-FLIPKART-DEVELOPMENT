package com.flipkart.dao.FlipFitOwner;

import com.flipkart.bean.FlipFitGymOwner;
import java.util.List;

public interface FlipFitOwnerInterface {
    boolean registerGymOwner(FlipFitGymOwner owner);
    FlipFitGymOwner getOwnerById(int ownerId);
    List<FlipFitGymOwner> getAllOwners();
    boolean updateOwnerDetails(FlipFitGymOwner owner);
}
