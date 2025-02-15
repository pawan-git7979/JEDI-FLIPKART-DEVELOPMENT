package com.flipkart.dao.FlipFitAdmin;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminImpl implements FlipFitAdminInterface {

    @Override
    public List<FlipFitGymOwner> getPendingGymOwnerRequests() {
        List<FlipFitGymOwner> owners = new ArrayList<>();
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            String query = "SELECT * FROM gym_owners WHERE status = 'PENDING'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                owners.add(new FlipFitGymOwner(
                        rs.getInt("userId"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        "OWNER",
                        rs.getString("address"),
                        rs.getString("aadhaarNumber"),
                        rs.getString("panNumber"),
                        rs.getString("governmentDocument")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return owners;
    }

    @Override
    public boolean approveGymOwner(int ownerId) {
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            String query = "UPDATE gym_owners SET status = 'APPROVED' WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, ownerId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean rejectGymOwner(int ownerId) {
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            String query = "DELETE FROM gym_owners WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, ownerId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
