package com.flipkart.dao.FlipFitUser;

import com.flipkart.bean.FlipFitUser;
import java.util.List;

public interface FlipFitUserInterface {
    boolean addUser(FlipFitUser user);
    FlipFitUser getUserById(int userId);
    FlipFitUser getUserByEmail(String email); // Added this method
    List<FlipFitUser> getAllUsers();
    void updateUser(FlipFitUser user);
    void deleteUser(int userId);
}
