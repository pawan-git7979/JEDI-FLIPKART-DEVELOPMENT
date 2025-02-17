package com.flipkart.business;

import java.util.List;
import java.util.Scanner;

public interface FlipFitGymOwnerServiceInterface {
    boolean addGymCenter(Scanner scanner, int ownerId);
    boolean updateGymInfo(Scanner scanner, int ownerId);
    boolean addOrUpdateSlot(Scanner scanner, int ownerId);
    List<String> viewBookings(int ownerId);
}
