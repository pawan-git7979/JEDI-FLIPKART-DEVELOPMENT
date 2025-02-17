package com.flipkart.dao.FlipFitGymCenter;

import com.flipkart.bean.FlipFitGymCenter;
import java.util.List;

public interface FlipFitGymCenterInterface {

    /**
     * Adds a new gym to the system.
     *
     * @param gym The FlipFitGymCenter object containing gym details to be added.
     * @return true if the gym was successfully added, false otherwise.
     */
    boolean addGym(FlipFitGymCenter gym);

    /**
     * Updates the details of an existing gym.
     *
     * @param gym The FlipFitGymCenter object containing updated gym details.
     * @return true if the gym details were successfully updated, false otherwise.
     */
    boolean updateGymDetails(FlipFitGymCenter gym);

    /**
     * Retrieves a list of all gyms in the system.
     *
     * @return A list of FlipFitGymCenter objects representing all gyms.
     */
    List<FlipFitGymCenter> getAllGyms();
}
