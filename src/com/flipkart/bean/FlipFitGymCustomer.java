package com.flipkart.bean;

import java.util.List;

/**
 * The FlipFitGymCustomer class represents a customer in the FlipFit system.
 * It extends FlipFitUser and includes additional details such as booking slot IDs
 * and a government-issued document number for verification.
 */
public class FlipFitGymCustomer extends FlipFitUser {
    private List<Integer> bookingSlotIds;
    private String governmentDocumentNumber;

    /**
     * Default Constructor.
     * Initializes a FlipFitGymCustomer object with default values.
     */
    public FlipFitGymCustomer() {
        super();
    }

    /**
     * Parameterized Constructor.
     * Initializes a FlipFitGymCustomer object with the given details.
     *
     * @param userId                   The unique ID of the customer.
     * @param name                     The name of the customer.
     * @param email                    The email address of the customer.
     * @param password                 The password of the customer.
     * @param address                  The address of the customer.
     * @param governmentDocumentNumber  The government-issued document number for verification.
     */
    public FlipFitGymCustomer(int userId, String name, String email, String password, String address,
                              String governmentDocumentNumber) {
        super(userId, name, email, password, FlipFitUser.ROLE_CUSTOMER, address);
        this.governmentDocumentNumber = governmentDocumentNumber;
    }

    /**
     * Gets the list of booking slot IDs associated with the customer.
     *
     * @return A list of integers representing booked slot IDs.
     */
    public List<Integer> getBookingSlotIds() {
        return bookingSlotIds;
    }

    /**
     * Sets the list of booking slot IDs for the customer.
     *
     * @param bookingSlotIds A list of integers representing booked slot IDs.
     */
    public void setBookingSlotIds(List<Integer> bookingSlotIds) {
        this.bookingSlotIds = bookingSlotIds;
    }

    /**
     * Gets the government-issued document number of the customer.
     *
     * @return The government-issued document number.
     */
    public String getGovernmentDocumentNumber() {
        return governmentDocumentNumber;
    }

    /**
     * Sets the government-issued document number of the customer.
     *
     * @param governmentDocumentNumber The government-issued document number.
     */
    public void setGovernmentDocumentNumber(String governmentDocumentNumber) {
        this.governmentDocumentNumber = governmentDocumentNumber;
    }

    /**
     * Returns a string representation of the FlipFitGymCustomer object.
     *
     * @return A formatted string containing customer details.
     */
    @Override
    public String toString() {
        return "FlipFitGymCustomer { " + super.toString() +
                ", bookingSlotIds=" + bookingSlotIds +
                ", governmentDocumentNumber='" + governmentDocumentNumber + "' }";
    }
}
