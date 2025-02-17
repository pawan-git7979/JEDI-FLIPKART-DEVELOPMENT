package com.flipkart.dao.FlipFitGymCenter;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.GymNotFoundException;
import com.flipkart.utils.FlipFitDBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitGymCenterImpl implements FlipFitGymCenterInterface {

    @Override
    public boolean addGym(FlipFitGymCenter gym) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLQueries.ADD_GYM)) {
            stmt.setString(1, gym.getName());
            stmt.setString(2, gym.getLocation());
            stmt.setInt(3, gym.getOwnerId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error while adding gym: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateGymDetails(FlipFitGymCenter gym) throws GymNotFoundException {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLQueries.UPDATE_GYM_DETAILS)) {
            stmt.setString(1, gym.getName());
            stmt.setString(2, gym.getLocation());
            stmt.setInt(3, gym.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new GymNotFoundException("Gym with ID " + gym.getId() + " not found.");
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error while updating gym details: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<FlipFitGymCenter> getAllGyms() throws GymNotFoundException {
        List<FlipFitGymCenter> gyms = new ArrayList<>();
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLQueries.GET_ALL_GYMS_FOR_GYM_CENTER);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FlipFitGymCenter gym = new FlipFitGymCenter();
                gym.setId(rs.getInt("id"));
                gym.setName(rs.getString("name"));
                gym.setLocation(rs.getString("location"));
                gyms.add(gym);
            }

            if (gyms.isEmpty()) {
                throw new GymNotFoundException("No gyms found in the database.");
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving gyms: " + e.getMessage());
        }
        return gyms;
    }
}
