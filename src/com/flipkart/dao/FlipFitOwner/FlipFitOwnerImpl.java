package com.flipkart.dao.FlipFitOwner;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitOwnerImpl implements FlipFitOwnerInterface {

    @Override
    public boolean registerGymOwner(FlipFitGymOwner owner) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO gym_owners (user_id, aadhaar_number, pan_number, government_doc) VALUES (?, ?, ?, ?)")) {
            stmt.setInt(1, owner.getUserId());
            stmt.setString(2, owner.getAadhaarNumber());
            stmt.setString(3, owner.getPanNumber());
            stmt.setString(4, owner.getGovernmentDocument());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public FlipFitGymOwner getOwnerById(int ownerId) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM gym_owners WHERE user_id = ?")) {
            stmt.setInt(1, ownerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(rs.getInt("user_id"));
                owner.setAadhaarNumber(rs.getString("aadhaar_number"));
                owner.setPanNumber(rs.getString("pan_number"));
                owner.setGovernmentDocument(rs.getString("government_doc"));
                return owner;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<FlipFitGymOwner> getAllOwners() {
        List<FlipFitGymOwner> owners = new ArrayList<>();
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM gym_owners")) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(rs.getInt("user_id"));
                owner.setAadhaarNumber(rs.getString("aadhaar_number"));
                owner.setPanNumber(rs.getString("pan_number"));
                owner.setGovernmentDocument(rs.getString("government_doc"));
                owners.add(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owners;
    }

    @Override
    public boolean updateOwnerDetails(FlipFitGymOwner owner) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE gym_owners SET aadhaar_number = ?, pan_number = ?, government_doc = ? WHERE user_id = ?")) {
            stmt.setString(1, owner.getAadhaarNumber());
            stmt.setString(2, owner.getPanNumber());
            stmt.setString(3, owner.getGovernmentDocument());
            stmt.setInt(4, owner.getUserId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
