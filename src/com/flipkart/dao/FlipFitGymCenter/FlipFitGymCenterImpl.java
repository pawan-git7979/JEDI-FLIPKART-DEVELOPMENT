package com.flipkart.dao.FlipFitGymCenter;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.constant.SQLQueries;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlipFitGymCenterImpl implements FlipFitGymCenterInterface {

    /**
     * Adds a new gym to the system.
     *
     * @param gym The FlipFitGymCenter object containing gym details.
     * @return true if the gym was added successfully, false otherwise.
     */
    @Override
    public boolean addGym(FlipFitGymCenter gym) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLQueries.ADD_GYM)) {
            stmt.setString(1, gym.getName());
            stmt.setString(2, gym.getLocation());
            stmt.setInt(3, gym.getOwnerId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates the details of an existing gym.
     *
     * @param gym The FlipFitGymCenter object containing updated gym details.
     * @return true if the gym details were updated successfully, false otherwise.
     */
    @Override
    public boolean updateGymDetails(FlipFitGymCenter gym) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLQueries.UPDATE_GYM_DETAILS)) {
            stmt.setString(1, gym.getName());
            stmt.setString(2, gym.getLocation());
            stmt.setInt(3, gym.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves a list of all gyms in the system.
     *
     * @return A list of FlipFitGymCenter objects representing all gyms.
     */
    @Override
    public List<FlipFitGymCenter> getAllGyms() {
        List<FlipFitGymCenter> gyms = new ArrayList<>();
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLQueries.GET_ALL_GYMS_FOR_GYM_CENTER)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FlipFitGymCenter gym = new FlipFitGymCenter();
                gym.setId(rs.getInt("id"));
                gym.setName(rs.getString("name"));
                gym.setLocation(rs.getString("location"));
                gyms.add(gym);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Sorting the gyms by their name
        return gyms.stream()
                .sorted(Comparator.comparing(FlipFitGymCenter::getName))
                .collect(Collectors.toList());
    }
}
