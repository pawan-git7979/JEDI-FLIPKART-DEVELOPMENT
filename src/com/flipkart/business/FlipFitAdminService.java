package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;
import java.util.List;
import java.util.ArrayList;

public class FlipFitAdminService implements FlipFitAdminServiceInterface {

    // Simulated local storage for gym requests and users.
    private List<String> gymRequests = new ArrayList<>();
    private List<FlipFitUser> users = new ArrayList<>();

    public FlipFitAdminService() {
        // Add a dummy request for demonstration
        gymRequests.add("Request: Add new Center - Elite Fitness");
    }

    @Override
    public void approveGymRequests() {
        if (gymRequests.isEmpty()) {
            System.out.println("No gym requests to approve.");
        } else {
            System.out.println("Approving the following gym requests:");
            for (String req : gymRequests) {
                System.out.println("Approved: " + req);
            }
            gymRequests.clear();
        }
    }

    @Override
    public void generateReports() {
        System.out.println("Generating Reports...");
        System.out.println("Total Users: " + users.size());
        System.out.println("Pending Gym Requests: " + gymRequests.size());
    }

    @Override
    public void manageUsers() {
        System.out.println("Listing registered users:");
        for (FlipFitUser user : users) {
            System.out.println(user);
        }
    }
    
    // For testing: add user
    public void addUser(FlipFitUser user) {
        users.add(user);
    }
}
