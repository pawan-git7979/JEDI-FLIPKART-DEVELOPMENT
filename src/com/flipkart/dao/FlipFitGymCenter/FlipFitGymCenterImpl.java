package com.flipkart.dao.FlipFitGymCenter;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitGymCenterImpl implements FlipFitGymCenterInterface {
    @Override
    public boolean addGym(FlipFitGymCenter gym) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO FlipFitGymCenter (name, location, owner_id) VALUES (?, ?, ?)")) {
            stmt.setString(1, gym.getName());
            stmt.setString(2, gym.getLocation());
            stmt.setInt(3, gym.getOwnerId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateGymDetails(FlipFitGymCenter gym) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE FlipFitGymCenter SET name = ?, location = ? WHERE id = ?")) {
            stmt.setString(1, gym.getName());
            stmt.setString(2, gym.getLocation());
            stmt.setInt(3, gym.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<FlipFitGymCenter> getAllGyms() {
        List<FlipFitGymCenter> gyms = new ArrayList<>();
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FlipFitGymCenter")) {
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
        return gyms;
    }
}
