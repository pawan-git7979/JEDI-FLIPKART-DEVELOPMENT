package com.flipkart.business;

import java.util.List;
import java.util.Scanner;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.dao.FlipFitGymOwner.FlipFitGymOwnerInterface;
import com.flipkart.dao.FlipFitGymOwner.FlipFitGymOwnerImpl;
import com.flipkart.utils.FlipFitIOUtils;

public class FlipFitGymOwnerServiceImpl implements FlipFitGymOwnerServiceInterface {
    private FlipFitGymOwnerInterface ownerDAO = new FlipFitGymOwnerImpl();

    @Override
    public boolean addGymCenter(Scanner scanner, int ownerId) {
        System.out.println("\n=== Add a Gym Center ===");
        String gymName = FlipFitIOUtils.getStringInput("Enter Gym Name: ", scanner);
        String location = FlipFitIOUtils.getStringInput("Enter Location: ", scanner);

        FlipFitGymCenter gym = new FlipFitGymCenter();
        gym.setName(gymName);
        gym.setLocation(location);
        gym.setOwnerId(ownerId);
        gym.setAdminId(1); // Default Admin ID for approval process

        return ownerDAO.addGymCenter(gym);
    }

    @Override
    public boolean updateGymInfo(Scanner scanner, int ownerId) {
        System.out.println("\n=== Update Gym Information ===");
        int gymId = FlipFitIOUtils.getIntInput("Enter Gym ID: ", scanner);
        String newName = FlipFitIOUtils.getStringInput("Enter New Gym Name: ", scanner);
        String newLocation = FlipFitIOUtils.getStringInput("Enter New Location: ", scanner);

        FlipFitGymCenter gym = new FlipFitGymCenter();
        gym.setId(gymId);
        gym.setName(newName);
        gym.setLocation(newLocation);
        gym.setOwnerId(ownerId);

        return ownerDAO.updateGymInfo(gym);
    }

    @Override
    public boolean addOrUpdateSlot(Scanner scanner, int ownerId) {
        System.out.println("\n=== Add or Update a Slot ===");
        int gymId = FlipFitIOUtils.getIntInput("Enter Gym ID: ", scanner);
        String startTime = FlipFitIOUtils.getStringInput("Enter Slot Start Time (HH:MM): ", scanner);
        String endTime = FlipFitIOUtils.getStringInput("Enter Slot End Time (HH:MM): ", scanner);
        int seats = FlipFitIOUtils.getIntInput("Enter Number of Seats: ", scanner);
        int price = FlipFitIOUtils.getIntInput("Enter Price: ", scanner);

        return ownerDAO.addOrUpdateSlot(gymId, startTime, endTime, seats, price);
    }

    @Override
    public List<String> viewBookings(int ownerId) {
        return ownerDAO.viewBookings(ownerId);
    }
}
