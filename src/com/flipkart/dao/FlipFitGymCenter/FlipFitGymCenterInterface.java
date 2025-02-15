package com.flipkart.dao.FlipFitGymCenter;

import com.flipkart.bean.FlipFitGymCenter;
import java.util.List;

public interface FlipFitGymCenterInterface {
    boolean addGym(FlipFitGymCenter gym);
    boolean updateGymDetails(FlipFitGymCenter gym);
    List<FlipFitGymCenter> getAllGyms();
}
