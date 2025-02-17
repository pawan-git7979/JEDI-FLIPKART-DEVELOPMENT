package com.flipkart.dao.FlipFitGymCenter;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.exception.GymNotFoundException;
import java.util.List;

public interface FlipFitGymCenterInterface {
    boolean addGym(FlipFitGymCenter gym);
    boolean updateGymDetails(FlipFitGymCenter gym) throws GymNotFoundException;
    List<FlipFitGymCenter> getAllGyms() throws GymNotFoundException;
}
