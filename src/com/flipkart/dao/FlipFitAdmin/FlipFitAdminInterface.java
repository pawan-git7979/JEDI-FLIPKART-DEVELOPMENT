package com.flipkart.dao.FlipFitAdmin;

import com.flipkart.bean.FlipFitGymOwner;
import java.util.List;

public interface FlipFitAdminInterface {
    List<FlipFitGymOwner> getPendingGymOwnerRequests();
    boolean approveGymOwner(int ownerId);
    boolean rejectGymOwner(int ownerId);
}
