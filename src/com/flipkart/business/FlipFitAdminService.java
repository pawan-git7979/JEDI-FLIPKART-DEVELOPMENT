package com.flipkart.business;

import com.flipkart.dao.FlipFitAdmin.FlipFitAdminInterface;
import com.flipkart.dao.FlipFitAdmin.FlipFitAdminImpl;
import com.flipkart.bean.FlipFitGymOwner;
import java.util.List;

public class FlipFitAdminService {
    private FlipFitAdminInterface adminDAO = new FlipFitAdminImpl();

    public List<FlipFitGymOwner> getPendingGymOwnerRequests() {
        return adminDAO.getPendingGymOwnerRequests();
    }

    public boolean approveGymOwner(int ownerId) {
        return adminDAO.approveGymOwner(ownerId);
    }

    public boolean rejectGymOwner(int ownerId) {
        return adminDAO.rejectGymOwner(ownerId);
    }
}
