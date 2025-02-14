package com.flipkart.client;

import com.flipkart.io.FlipFitIO;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitGymSlot;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.business.FlipFitGymOwnerService;

import java.util.ArrayList;

public class FlipFitGymOwnerMenu {

    public static void showMenu(FlipFitGymOwner owner, FlipFitGymOwnerService ownerService) {
        int choice;
        do {
            System.out.println("\n=== FlipFit Gym Owner Menu ===");
            System.out.println("1. Add a Gym Center");
            System.out.println("2. Update Gym Information");
            System.out.println("3. Add or Update a Slot");
            System.out.println("4. View Bookings (Not Implemented)");
            System.out.println("5. Logout");
            choice = FlipFitIO.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    int centerId = FlipFitIO.getIntInput("Enter new Gym Center ID: ");
                    String centerName = FlipFitIO.getStringInput("Enter Gym Center Name: ");
                    String location = FlipFitIO.getStringInput("Enter Gym Center Location: ");
                    FlipFitGymCenter center = new FlipFitGymCenter(
                            centerId, centerName, location, owner.getUserId(), new ArrayList<>()
                    );
                    ownerService.addGymCenter(owner, center);
                    break;
                case 2:
                    centerId = FlipFitIO.getIntInput("Enter Gym Center ID to update: ");
                    String newName = FlipFitIO.getStringInput("Enter new Gym Center Name: ");
                    String newLocation = FlipFitIO.getStringInput("Enter new Gym Center Location: ");
                    ownerService.updateGymInfo(owner, centerId, newName, newLocation);
                    break;
                case 3:
                    centerId = FlipFitIO.getIntInput("Enter Gym Center ID: ");
                    String slotId = FlipFitIO.getStringInput("Enter Slot ID: ");
                    String startTime = FlipFitIO.getStringInput("Enter Slot Start Time: ");
                    String endTime = FlipFitIO.getStringInput("Enter Slot End Time: ");
                    String trainer = FlipFitIO.getStringInput("Enter Trainer Name: ");
                    int numOfSeats = FlipFitIO.getIntInput("Enter number of seats: ");
                    FlipFitGymSlot slot = new FlipFitGymSlot(
                            slotId, startTime, endTime, numOfSeats, trainer, String.valueOf(centerId)
                    );
                    ownerService.addOrUpdateSlot(owner, centerId, slot);
                    break;
                case 4:
                    System.out.println("Viewing bookings is not implemented in this demo.");
                    break;
                case 5:
                    System.out.println("Logging out from Gym Owner account...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
