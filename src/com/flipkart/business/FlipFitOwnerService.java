package com.flipkart.business;

import com.flipkart.dao.FlipFitOwnerDAO;
import com.flipkart.bean.FlipFitGymCenter;

public class FlipFitOwnerService {
    private FlipFitOwnerDAO ownerDAO = new FlipFitOwnerDAO();

    public boolean addGymCenter(FlipFitGymCenter gym) {
        return ownerDAO.addGymCenter(gym);
    }

    public boolean updateGymInfo(FlipFitGymCenter gym) {
        return ownerDAO.updateGymInfo(gym);
    }
}
